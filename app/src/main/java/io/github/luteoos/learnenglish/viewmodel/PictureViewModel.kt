package io.github.luteoos.learnenglish.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel
import io.github.luteoos.gent.network.RestApi
import io.github.luteoos.learnenglish.network.LEngApi
import io.github.luteoos.learnenglish.network.response.ResponsePicture
import io.github.luteoos.learnenglish.utils.Parameters
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class PictureViewModel : BaseViewModel() {

    val list: MutableLiveData<MutableList<ResponsePicture>> = MutableLiveData()

    fun getPictureList(id: String){
        val client = RestApi.createService(LEngApi::class.java)
        disposable.add(client.getPictureList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when {
                    it.code() == 200 -> {
                       list.value = it.body()
                    }
                    else -> message.value = Parameters.API_ERROR
                }
            }, {
                message.value = Parameters.API_ERROR
            }))
    }

    fun getRandomPictures(): MutableList<String>{
        val list: MutableList<String> = MutableList(0) {""}
        this@PictureViewModel.list.value!!.forEach { list.add(it.url) }
        val returnList: MutableList<String> = MutableList(0){""}
        while (returnList.size <3){
            returnList.add(list[Random().nextInt(list.size-1)])
        }
        return returnList
    }
}