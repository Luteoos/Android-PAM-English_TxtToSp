package io.github.luteoos.learnenglish.view.activity

import android.os.Bundle
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.viewmodel.MainScreenViewModel

class MainScreenActivity : BaseActivityMVVM<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_main_screen

    override fun onVMMessage(msg: String?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainScreenViewModel()
        this.connectToVMMessage()
        setBindings()
    }

    private fun setBindings(){

    }
}