package io.github.luteoos.learnenglish.view.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.luteoos.kotlin.mvvmbaselib.BaseFragmentMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.network.response.ResponseTask
import io.github.luteoos.learnenglish.utils.Parameters
import io.github.luteoos.learnenglish.view.recyclerview.RVTasks
import io.github.luteoos.learnenglish.viewmodel.TasksFragmentViewModel
import kotlinx.android.synthetic.main.fragment_tasks.*

@SuppressLint("ValidFragment")
class TasksFragment(val id: String): BaseFragmentMVVM<TasksFragmentViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = TasksFragmentViewModel()
        connectToVMMessage()
        setBindings()
        viewModel.getTasksList(id)
    }

    override fun onVMMessage(msg: String?) {
        when(msg){
            Parameters.API_ERROR -> {
                Toasty.error(context!!, R.string.api_error).show()
                progressBarTasks.visibility = View.GONE
            }
        }
    }

    private fun setBindings(){
        viewModel.list.observe(this, Observer { list ->
            if(list != null){
                if(list.size != 0)
                    initRV(list)
                else
                    Toasty.info(context!!, R.string.list_empty).show()
            }
        })
    }

    private fun initRV(list: MutableList<ResponseTask>){
        progressBarTasks.visibility = View.GONE
        rvTasks.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = RVTasks(list, context)
        }
    }
}