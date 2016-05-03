package com.bookislife.android.scalademo

import java.util.concurrent.Executors

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.{AdapterView, ListView, Toast}
import org.json.JSONArray

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

/**
  * Created by SidneyXu on 2016/01/20.
  */
class CountryListActivity extends AppCompatActivity {

  implicit val service = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    val listView = new ListView(this)
    setContentView(listView)

    import AndroidContext._

    val progressDialog = this.progress()
    progressDialog.show()

    findCountries((names: ArrayBuffer[String], e: Exception) =>

      runOnUiThread(new Runnable {
        override def run(): Unit = {
          progressDialog.dismiss()
          if (e != null) {
            CountryListActivity.this.toast(e.getMessage)
            return
          }
          val apt = new Apt(CountryListActivity.this, names)
          listView.setAdapter(apt)
          listView.setOnItemClickListener(new OnItemClickListener {
            override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long): Unit = {
              CountryListActivity.this.toast(names(position))
            }
          })

        }
      })
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

  object AndroidContext {

    implicit class RichContext(ctx: Context) {
      def toast(msg: String) = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()

      def progress(): ProgressDialog = {
        val progressDialog = new ProgressDialog(ctx)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)
        progressDialog
      }
    }

  }

}
