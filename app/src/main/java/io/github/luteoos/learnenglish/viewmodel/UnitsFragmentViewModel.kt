package io.github.luteoos.learnenglish.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel
import io.github.luteoos.gent.network.RestApi
import io.github.luteoos.learnenglish.network.LEngApi
import io.github.luteoos.learnenglish.network.response.ResponseUnit
import io.github.luteoos.learnenglish.utils.Parameters
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UnitsFragmentViewModel: BaseViewModel() {

    val list: MutableLiveData<MutableList<ResponseUnit>> = MutableLiveData()

    fun getUnitsList(){
        val client = RestApi.createService(LEngApi::class.java)
        disposable.add(client.getUntList()
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
}