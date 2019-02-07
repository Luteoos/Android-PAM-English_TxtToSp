package io.github.luteoos.learnenglish.view.activity

import android.animation.ObjectAnimator
import android.arch.lifecycle.Observer
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.utils.Parameters
import io.github.luteoos.learnenglish.viewmodel.PictureViewModel
import kotlinx.android.synthetic.main.activity_picture_test.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class PictureTestActivity: BaseActivityMVVM<PictureViewModel>() {

    private var currentIteration = 0
    private lateinit var reader: TextToSpeech
    private var id: String = ""
    private var photos: MutableList<ImageView> = mutableListOf()

    override fun getLayoutID(): Int = R.layout.activity_picture_test

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
        btnBack.onClick {
            onBackPressed()
        }
        if(isNetworkOnLine)
            viewModel.getPictureList(id)
        else
            Toasty.error(this, R.string.no_internet).show()
        initDictionary()
    }

    override fun onBackPressed() {
        reader.shutdown()
        super.onBackPressed()
    }

    override fun onVMMessage(msg: String?) {
        when(msg){
            Parameters.API_ERROR -> {
                Toasty.error(this, R.string.api_error).show()
                onBackPressed()
            }
        }
    }

    private fun setBindings(){
        btnNext.onClick {
            currentIteration++
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
        else{
            btnNext.onClick {
                onBackPressed()
            }
            btnNext.text = getString(R.string.end)
        }
    }

    private fun loadImage(url: String, word: String){
        val correct = Random().nextInt(3)
        var iterator = 0
        val randomPics = viewModel.getRandomPictures(url)
        Glide.with(this)
            .load(url)
            .into(photos[correct])
        for(i in 0..3){
            if(i != correct){
                Glide.with(this)
                    .load(randomPics[iterator])
                    .into(photos[i])
                iterator++
                photos[i].onClick {
                    screenFlash(false)
                }
            }
            else
                photos[i].onClick {
                    screenFlash(true)
                }
        }
        setButtonsVisibility()
        readString(word)
    }

    private fun readString(word: String){
        if(!reader.isSpeaking)
            reader.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun screenFlash(bool: Boolean){
        constraintLayout.foreground = ColorDrawable(getColor(
            if(bool)
                R.color.green
            else
                R.color.red
        ))
        val animator = ObjectAnimator.ofInt(this.constraintLayout.foreground,"alpha",0,255).apply {
            duration = 500
            repeatMode= ObjectAnimator.REVERSE
            repeatCount = 1
        }
        animator.start()
    }

    private fun initDictionary(){
        photos.add(ivPicture1)
        photos.add(ivPicture2)
        photos.add(ivPicture3)
        photos.add(ivPicture4)
    }
}