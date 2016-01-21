package com.bookislife.android.kotlindemo

import android.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Created by SidneyXu on 2016/01/21.
 */
public class Apt(val ctx: Context, var data: List<String>) : ArrayAdapter<String>(
        ctx,
        R.layout.simple_list_item_1,
        R.id.text1,
        data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var holder: Holder? = null
        var view: View? = null
        if (null == convertView) {
            holder = Holder()
            view = super.getView(position, convertView, parent)
            holder.title = view.findViewById(R.id.text1) as TextView
            view.tag = holder
        } else {
            holder = view?.tag as Holder
            view = convertView
        }
        holder.title?.text = getItem(position)
        return view
    }

    private class Holder {
        var title: TextView? = null
    }
}