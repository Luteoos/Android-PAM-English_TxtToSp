package io.github.luteoos.learnenglish.view.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.network.response.ResponseTask
import io.github.luteoos.learnenglish.network.response.ResponseUnit
import io.github.luteoos.learnenglish.utils.Bus
import io.github.luteoos.learnenglish.utils.Event
import io.github.luteoos.learnenglish.utils.Parameters
import kotlinx.android.synthetic.main.rv_tasks.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class RVTasks (val list: MutableList<ResponseTask>,
               val context: Context
) : RecyclerView.Adapter<RVTasks.RVTaskVH>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RVTaskVH =
        RVTaskVH(LayoutInflater.from(context).inflate(R.layout.rv_tasks, p0,false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(view: RVTaskVH, position: Int) {
        view.run {
            title.text = list[position].name
            desc.text = list[position].description
            btnPractice.onClick {
                Bus.sendMessageWithUUID(Event.MessageWithUUID(list[position].id, Parameters.OPEN_PICTURES_PRACTICE))
            }
            btnTest.onClick {
                Bus.sendMessageWithUUID(Event.MessageWithUUID(list[position].id, Parameters.OPEN_PICTURES_TEST))
            }
        }
    }

    class RVTaskVH(val view: View): RecyclerView.ViewHolder(view){
        val title = view.tvTitleTask
        val desc = view.tvDescTask
        val btnPractice = view.btnPractice
        val btnTest = view.btnTest
    }
}