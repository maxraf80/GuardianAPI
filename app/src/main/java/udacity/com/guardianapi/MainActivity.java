package udacity.com.guardianapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    private static final String APIKEY = "&api-key=6b3d9da0-5381-4fa9-9470-6cebb228a388";
    boolean conection;
    String find;
    private TextView EmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty_list_view));
        adapter = new NewsAdapter(this, new ArrayList<News>());


        topic = (TextView) findViewById(R.id.editText);
        checkButton = (Button) findViewById(R.id.searchButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    conection = true;
                } else {
                    conection = false;
                }

                if (conection == true) {
                    find = topic.getText().toString();
                    String toSearch = "";
                    if (find.length() > 0) {
                        find = find.replace(" ", "+");
                    }
                    toSearch = api + find + APIKEY;
                }

            }


        });


    }


    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        View loadingindicator = findViewById(R.id.loading_indicator);
        loadingindicator.setVisibility(View.GONE);
        Empty

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }
}
