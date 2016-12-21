package udacity.com.guardianapi;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = NewsLoader.class.getName();
    private String url;


    public NewsLoader(Context context, String  mURL){
        super(context);
        url=mURL;  }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() { if(url==null){
        return null;
    }

    List<News> newses = QueryUtils.fetchNewsData(url);
    return newses ;

}}
