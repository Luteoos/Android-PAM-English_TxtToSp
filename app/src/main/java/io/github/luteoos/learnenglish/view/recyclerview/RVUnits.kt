package io.github.luteoos.learnenglish.view.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.luteoos.learnenglish.R
import io.github.luteoos.learnenglish.network.response.ResponseUnit

class RVUnits(val listUnits: MutableList<ResponseUnit>,
              val context: Context) : RecyclerView.Adapter<RVUnits.RVUnitsVH>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RVUnitsVH =
        RVUnitsVH(LayoutInflater.from(context).inflate(R.layout.rv_units, p0,false))

    override fun getItemCount(): Int = listUnits.size

    override fun onBindViewHolder(p0: RVUnitsVH, p1: Int) {
    }

    class RVUnitsVH(val view: View): RecyclerView.ViewHolder(view){

    }
}