package io.github.luteoos.learnenglish.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.luteoos.kotlin.mvvmbaselib.BaseViewModel
import io.github.luteoos.learnenglish.network.response.ResponseUnit

class UnitsFragmentViewModel: BaseViewModel() {

    val list: MutableLiveData<MutableList<ResponseUnit>> = MutableLiveData()
}