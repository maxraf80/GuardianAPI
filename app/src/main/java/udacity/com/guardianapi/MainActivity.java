package udacity.com.guardianapi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private NewsAdapter adapter;
    private String api = "http://content.guardianapis.com/search?show-fields=thumbnail&q=";
    TextView topic;
    Button checkButton;
    private static final String APIKEY = "&api-key=6b3d9da0-5381-4fa9-9470-6cebb228a388&show-tags=contributor";
    boolean conection;
    String find;
    TextView EmptyStateTextView;
    String toSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topic = (TextView) findViewById(R.id.editText);
        checkButton = (Button) findViewById(R.id.searchButton);
        ListView listView = (ListView) findViewById(R.id.list);
        EmptyStateTextView = (TextView) findViewById(R.id.empty_list_view);
        listView.setEmptyView(findViewById(R.id.empty_list_view));
        adapter = new NewsAdapter(this, new ArrayList<News>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNew = adapter.getItem(position);
                Uri newsURI = Uri.parse(currentNew.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsURI);
                startActivity(websiteIntent);
            }
        });


        checkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    find = topic.getText().toString();

                    if (find.length() > 0) {
                        find = find.replace(" ", "+");
                    }
                    toSearch = api + find + APIKEY;

                    getSupportLoaderManager().restartLoader(0, null, MainActivity.this);

                } else {
                    conection = false;
                }

            }
        });
        getSupportLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.d("MainActivity", ">>>>> onCreateLoader " + toSearch);

        return new NewsLoader(this, toSearch);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        View loadingindicator = findViewById(R.id.loading_indicator);
        loadingindicator.setVisibility(View.GONE);
        adapter.clear();
        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }
}
