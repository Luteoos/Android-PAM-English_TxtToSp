package io.github.luteoos.learnenglish.view.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import com.bumptech.glide.Glide
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.utils.Parameters
import io.github.luteoos.learnenglish.viewmodel.PictureViewModel
import kotlinx.android.synthetic.main.activity_picture_practice.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class PicturePracticeActivity: BaseActivityMVVM<PictureViewModel>() {

    private var currentIteration = 0
    private lateinit var reader: TextToSpeech
    private var id: String = ""

    override fun getLayoutID(): Int = R.layout.activity_picture_practice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = PictureViewModel()
        id = intent.extras!!.getString(Parameters.ID_FOR_PIC)!!
        this.connectToVMMessage()
        viewModel.list.observe(this, Observer {
            if(it == null)
                Toasty.error(this, R.string.api_error).show()
            else{
                currentIteration = 0
                setBindings()
                loadImage(it[currentIteration].url, it[currentIteration].word)
            }
        })
        reader = TextToSpeech(this, TextToSpeech.OnInitListener {  })
        reader.language = Locale.US
        if(isNetworkOnLine)
            viewModel.getPictureList(id)
        else
            Toasty.error(this, R.string.no_internet).show()
    }

    override fun onBackPressed() {
        reader.shutdown()
        super.onBackPressed()
    }

    override fun onVMMessage(msg: String?) {
        when(msg){
            Parameters.API_ERROR -> Toasty.error(this, R.string.api_error).show()
        }
    }

    private fun setBindings(){
        btnBack.onClick {
            onBackPressed()
        }
        btnNext.onClick {
            currentIteration++
            if(viewModel.list.value != null)
                loadImage(viewModel.list.value!![currentIteration].url, viewModel.list.value!![currentIteration].word)
        }
        btnPrev.onClick {
            currentIteration--
            if(viewModel.list.value != null)
                loadImage(viewModel.list.value!![currentIteration].url, viewModel.list.value!![currentIteration].word)
        }
        tvReadAgain.onClick {
            if(viewModel.list.value != null)
                readString(viewModel.list.value!![currentIteration].word)
        }
    }

    private fun setButtonsVisibility(){
        if(viewModel.list.value!!.size > 1+currentIteration)
            btnNext.visibility = View.VISIBLE
        else
            btnNext.visibility = View.GONE
        if(currentIteration != 0)
            btnPrev.visibility = View.VISIBLE
        else
            btnPrev.visibility = View.GONE
    }

    private fun loadImage(url: String, word: String){
        Glide.with(this)
            .load(url)
            .into(ivPracticePicture)
        setButtonsVisibility()
        readString(word)
    }

    private fun readString(word: String){
        if(!reader.isSpeaking)
            reader.speak(word,TextToSpeech.QUEUE_FLUSH, null, null)
    }
}