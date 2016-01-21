package com.bookislife.android.javademo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SidneyXu on 2016/01/20.
 */
public class CountryListActivity extends AppCompatActivity {

    public interface FindCallback {
        void onComplete(List<String> names, Exception e);
    }

    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ListView listView = new ListView(this);
        setContentView(listView);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
        findCountries(new FindCallback() {
            @Override
            public void onComplete(final List<String> names, final Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        if (null != e) {
                            Toast.makeText(CountryListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Apt apt = new Apt(CountryListActivity.this, names);
                        listView.setAdapter(apt);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(CountryListActivity.this, names.get(position), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    private void findCountries(final FindCallback doneCallback) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                try {
                    inputStream = new URL("https://restcountries.eu/rest/v1/all").openStream();
                    StringBuilder builder = new StringBuilder();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        builder.append(new String(buffer, 0, len));
                    }
                    JSONArray jsonArray = new JSONArray(builder.toString());
                    List<String> names = new ArrayList<>();
                    int length = jsonArray.length();
                    for (int i = 0; i < length; i++) {
                        names.add(jsonArray.getJSONObject(i).getString("name"));
                    }
                    doneCallback.onComplete(names, null);
                } catch (Exception e) {
                    doneCallback.onComplete(null, e);
                } finally {
                    if (null != inputStream)
                        try {
                            inputStream.close();
                        } catch (IOException ignored) {
                        }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        service.shutdown();
    }
}
