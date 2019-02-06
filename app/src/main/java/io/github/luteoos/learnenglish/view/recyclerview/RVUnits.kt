package io.github.luteoos.learnenglish.view.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.network.response.ResponseUnit
import io.github.luteoos.learnenglish.utils.Bus
import io.github.luteoos.learnenglish.utils.Event
import io.github.luteoos.learnenglish.utils.Parameters
import kotlinx.android.synthetic.main.rv_units.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class RVUnits(val listUnits: MutableList<ResponseUnit>,
              val context: Context) : RecyclerView.Adapter<RVUnits.RVUnitsVH>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RVUnitsVH =
        RVUnitsVH(LayoutInflater.from(context).inflate(R.layout.rv_units, p0,false))

    override fun getItemCount(): Int = listUnits.size

    override fun onBindViewHolder(view: RVUnitsVH, position: Int) {
        view.button.onClick {
            Bus.sendMessageWithUUID(Event.MessageWithUUID(listUnits[position].id, Parameters.SWITCH_TO_TASK))
        }
        view.title.text = listUnits[position].name
        view.desc.text = listUnits[position].description
    }

    class RVUnitsVH(val view: View): RecyclerView.ViewHolder(view){
        val button = view.btnTask
        val title = view.tvTitleTask
        val desc = view.tvDescUnit
    }
}