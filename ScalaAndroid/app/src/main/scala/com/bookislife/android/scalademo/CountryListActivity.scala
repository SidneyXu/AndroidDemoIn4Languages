package com.bookislife.android.scalademo

import java.util.concurrent.Executors

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.{AdapterView, ListView}
import org.json.JSONArray
import org.scaloid.common.{SActivity, _}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

/**
  * Created by SidneyXu on 2016/01/20.
  */
class CountryListActivity extends AppCompatActivity with SActivity {

  implicit val service = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    val listView = new ListView(this)
    setContentView(listView)

    val progressDialog = new ProgressDialog(this)
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
    progressDialog.setCancelable(false)
    progressDialog.show()

    findCountries((names: ArrayBuffer[String], e: Exception) =>

      runOnUiThread {
        progressDialog.dismiss()
        if (e != null) {
          toast(e.getMessage)
          return
        }
        val apt = new Apt(CountryListActivity.this, names)
        listView.setAdapter(apt)
        listView.onItemClick { (parent: AdapterView[_], view: View, position: Int, id: Long) =>
          toast(names(position))
        }
      }
    )
  }

  private def findCountries(callback: (ArrayBuffer[String], Exception) => Unit): Unit = {
    Future {
      try {
        val countries = Source.fromURL("https://restcountries.eu/rest/v1/all", "UTF-8").mkString
        val json = new JSONArray(countries)
        val names = ArrayBuffer[String]()
        val length = json.length
        var i = 0
        while (i < length) {
          names += json.getJSONObject(i).getString("name")
          i += 1
        }
        callback(names, null)
      } catch {
        case e: Exception => callback(null, e)
      }
    }
  }

}
