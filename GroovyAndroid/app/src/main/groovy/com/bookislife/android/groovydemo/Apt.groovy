package com.bookislife.android.groovydemo

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import groovy.transform.CompileStatic

/**
 * Created by SidneyXu on 2016/01/20.
 */
@CompileStatic
class Apt extends ArrayAdapter<String> {

    Apt(final Context context, final List<String> data) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1, data)
    }

    @Override
    View getView(final int position, final View convertView, final ViewGroup parent) {
        def holder
        View view
        if (null == convertView) {
            holder = new Holder()
            view = super.getView(position, convertView, parent)
            holder.title = view.findViewById(android.R.id.text1) as TextView
            view.setTag(holder)
        } else {
            holder = convertView.getTag() as Holder
            view = convertView
        }
        holder.title.text = getItem(position)
        view
    }

    private class Holder {
        TextView title
    }
}