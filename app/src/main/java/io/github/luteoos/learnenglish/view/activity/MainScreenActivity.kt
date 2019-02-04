package io.github.luteoos.learnenglish.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.utils.Parameters
import io.github.luteoos.learnenglish.view.fragment.UnitsFragment
import io.github.luteoos.learnenglish.viewmodel.MainScreenViewModel
import kotlinx.android.synthetic.main.activity_main_screen.view.*

class MainScreenActivity : BaseActivityMVVM<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_main_screen

    override fun onVMMessage(msg: String?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainScreenViewModel()
        this.connectToVMMessage()
        initFirstFragment()
        setBindings()
    }

    private fun setBindings(){
    }

    private fun initFirstFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, UnitsFragment(), Parameters.UNIT_FRAGMENT)
            .addToBackStack(Parameters.UNIT_FRAGMENT)
            .commit()
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, fragment)
            .commitAllowingStateLoss()
    }
}