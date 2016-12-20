package udacity.com.guardianapi;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = NewsLoader.class.getName();
    private String url;


    public NewsLoader(Context context, String  mURL){
        super(context);
        url=mURL;


    }
    @Override
    public List<News> loadInBackground() {
        return null;
    }
}
