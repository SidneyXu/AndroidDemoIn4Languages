package com.bookislife.android.groovydemo

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import groovy.transform.CompileStatic

/**
 * Created by SidneyXu on 2016/01/20.
 */
@CompileStatic
class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        contentView = R.layout.activity_main

        findViewById(android.R.id.button1).onClickListener = {
            startActivity(new Intent(this, CountryListActivity.class))
        }

    }

    @Override
    boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.menu_main, menu)
        true
    }

    @Override
    boolean onOptionsItemSelected(MenuItem item) {
        def id = item.itemId
        switch (id) {
            case R.id.action_settings:
                break
        }
        true
    }
}