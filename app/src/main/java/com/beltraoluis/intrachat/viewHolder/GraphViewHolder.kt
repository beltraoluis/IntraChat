package com.beltraoluis.intrachat.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.beltraoluis.intrachat.R
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class GraphViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
    val graph = itemView?.findViewById<GraphView>(R.id.graph)
    val binary = itemView?.findViewById<TextView>(R.id.graph_binary)

    fun set(p: Pair<String,String>){
        val line = p.first
        val bin =p.second
        binary?.text = bin
        val list = mutableListOf<Double>()
        val serie = mutableListOf<DataPoint>()
        val array = line.split(";")
        if(array.size > 1){
            array.forEach {
                list.add(it.toDouble())
            }
            var i = 0.0
            list.forEach {
                serie.add(DataPoint(i,it))
                serie.add(DataPoint(i+1,it))
                i++
            }
            val series = LineGraphSeries(serie.toTypedArray())
            graph?.addSeries(series)
            graph?.getViewport()?.setXAxisBoundsManual(true);
            graph?.getViewport()?.setMinX(8.0);
            graph?.getViewport()?.setMaxX(32.0);
            graph?.viewport?.setScrollable(true)
            graph?.viewport?.setScalable(true)
        }
    }
}