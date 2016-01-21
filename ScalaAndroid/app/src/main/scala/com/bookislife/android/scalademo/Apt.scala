package com.bookislife.android.scalademo

import android.content.Context
import android.view.{View, ViewGroup}
import android.widget.{ArrayAdapter, TextView}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by SidneyXu on 2016/01/20.
  */
class Apt(val ctx: Context, var data: ArrayBuffer[String]) extends ArrayAdapter[String](ctx,
  android.R.layout.simple_list_item_1,
  android.R.id.text1) {

  override def getItem(position: Int): String = data(position)

  override def getCount: Int = data.length

  override def getView(position: Int, convertView: View, parent: ViewGroup): View = {
    var holder: Holder = null
    var view: View = null
    if (null == convertView) {
      holder = new Holder()
      view = super.getView(position, convertView, parent)
      holder.title = view.findViewById(android.R.id.text1).asInstanceOf[TextView]
      view.setTag(holder)
    } else {
      holder = convertView.getTag.asInstanceOf[Holder]
      view = convertView
    }
    holder.title.setText(getItem(position))
    view
  }

  private class Holder {
    var title: TextView = null
  }

}
