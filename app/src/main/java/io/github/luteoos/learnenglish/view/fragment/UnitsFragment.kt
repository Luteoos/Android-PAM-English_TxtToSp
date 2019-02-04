package io.github.luteoos.learnenglish.view.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.luteoos.kotlin.mvvmbaselib.BaseFragmentMVVM
import es.dmoral.toasty.Toasty
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.network.response.ResponseUnit
import io.github.luteoos.learnenglish.view.recyclerview.RVUnits
import io.github.luteoos.learnenglish.viewmodel.UnitsFragmentViewModel
import kotlinx.android.synthetic.main.fragment_units.*

class UnitsFragment: BaseFragmentMVVM<UnitsFragmentViewModel>() {
    override fun getLayoutID(): Int = R.layout.fragment_units

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = UnitsFragmentViewModel()
        connectToVMMessage()
        setBindings()
    }

    override fun onVMMessage(msg: String?) {

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

    private fun initRV(list: MutableList<ResponseUnit>){
        rvUnits.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = RVUnits(list, context)
        }
    }
}