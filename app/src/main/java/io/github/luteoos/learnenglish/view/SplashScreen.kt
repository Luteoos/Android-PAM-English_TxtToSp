package io.github.luteoos.learnenglish.view

import android.content.Intent
import android.os.Bundle
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.view.activity.MainScreenActivity
import io.github.luteoos.learnenglish.viewmodel.SplashScreenViewModel
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class SplashScreen : BaseActivityMVVM<SplashScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = SplashScreenViewModel()
        tvTitle.onClick{
            startMainScreenActivity()
        }
    }

    override fun onVMMessage(msg: String?) {
    }

    private fun startMainScreenActivity(){
        val intent = Intent(this, MainScreenActivity::class.java )
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}