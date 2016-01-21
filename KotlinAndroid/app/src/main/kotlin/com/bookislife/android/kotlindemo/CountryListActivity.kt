package com.bookislife.android.kotlindemo

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import org.json.JSONArray
import java.net.URL
import java.util.concurrent.Executors

/**
 * Created by SidneyXu on 2016/01/21.
 */
public class CountryListActivity : AppCompatActivity() {

    val service = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = ListView(this)
        setContentView(listView)
        val progressDialog = ProgressDialog(this)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)
        progressDialog.show()

        findCountries { list, e ->
            runOnUiThread {
                progressDialog.dismiss()
                if (e != null) {
                    toast(e.message)
                    return@runOnUiThread
                }
                val apt = Apt(this, list!!)
                listView.adapter = apt
                listView.onItemClickListener = AdapterView.OnItemClickListener() { adapterView, view, i, l ->
                    toast("${list[i]}")
                }
            }
        }
    }

    fun findCountries(callback: (List<String>?, Exception?) -> Unit) {
        service.execute {
            try {
                URL("https://restcountries.eu/rest/v1/all").openStream().use {
                    it.bufferedReader("UTF-8").use {
                        val countries = it.readText()
                        val json = JSONArray(countries)
                        val names = linkedListOf<String>()

                        val length = json.length()
                        var i = 0
                        while (i < length) {
                            with(json.getJSONObject(i)) {
                                names.add(getString("name"))
                            }
                            i++
                        }
                        callback(names, null)
                    }
                }
            } catch(e: Exception) {
                callback(null, e)
            }
        }
    }

    public fun Activity.toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        service.shutdown()
    }
}