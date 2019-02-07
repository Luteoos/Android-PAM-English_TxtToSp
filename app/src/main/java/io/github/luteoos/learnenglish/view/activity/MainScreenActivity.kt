package io.github.luteoos.learnenglish.view.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.luteoos.kotlin.mvvmbaselib.BaseActivityMVVM
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.utils.Bus
import io.github.luteoos.learnenglish.utils.Parameters
import io.github.luteoos.learnenglish.view.fragment.TasksFragment
import io.github.luteoos.learnenglish.view.fragment.UnitsFragment
import io.github.luteoos.learnenglish.viewmodel.MainScreenViewModel
import kotlinx.android.synthetic.main.activity_main_screen.view.*

class MainScreenActivity : BaseActivityMVVM<MainScreenViewModel>() {

    private var isUnitShow = true

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

    override fun onBackPressed() {
        if(isUnitShow)
            super.onBackPressed()
        else
            initFirstFragment()
    }

    private fun setBindings(){
        Bus.messageWithUUID.observe(this, Observer {
            when(it!!.message){
                Parameters.SWITCH_TO_TASK -> initTaskFragment(it.uuid)
                Parameters.OPEN_PICTURES_PRACTICE -> startPicturePracticeActivity(it.uuid)
                Parameters.OPEN_PICTURES_TEST -> startPictureTestActivity(it.uuid)
            }
        })
    }

    private fun initFirstFragment(){
        isUnitShow = true
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, UnitsFragment(), Parameters.UNIT_FRAGMENT)
            .commit()
    }

    private fun initTaskFragment(id: String){
        isUnitShow = false
        val intent = TasksFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, intent)
            .commit()
        intent.setID(id)
    }

    private fun startPicturePracticeActivity(id: String){
        val intent = Intent(this, PicturePracticeActivity::class.java)
        intent.putExtra(Parameters.ID_FOR_PIC, id)
        startActivity(intent)
    }

    private fun startPictureTestActivity(id: String){
        val intent = Intent(this, PictureTestActivity::class.java)
        intent.putExtra(Parameters.ID_FOR_PIC, id)
        startActivity(intent)
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, fragment)
            .commitAllowingStateLoss()
    }
}