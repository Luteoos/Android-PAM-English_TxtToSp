package io.github.luteoos.learnenglish.view

import android.os.Bundle
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.viewmodel.SplashScreenViewModel

class SplashScreen : BaseActivityMVVM<SplashScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = SplashScreenViewModel()
    }

    override fun onVMMessage(msg: String?) {
    }
}