package udacity.com.guardianapi;


import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {  }

    public static List<News> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse =null;
        try{jsonResponse = makeHttpRequest(url);}
        catch (IOException e){Log.e(LOG_TAG, "Problem making the HTTP request.",e);}
        List<News> newses =extracFeatureFromJson(jsonResponse);
        return newses; }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem bulding the URL ", e);
        }
        return url;   }

    private static String makeHttpRequest(URL url) throws IOException{String jsonResponse="";
    if (url==null){return jsonResponse;}

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
         urlConnection=(HttpURLConnection)url.openConnection();
         urlConnection.setReadTimeout(10000);
         urlConnection.setConnectTimeout(15000);
         urlConnection.setRequestMethod("GET");
         urlConnection.connect();


            if(urlConnection.getResponseCode()==200){
                inputStream= urlConnection.getInputStream();
                jsonResponse=readFromStream(inputStream); }
            else{ Log.e(LOG_TAG, "Error response code: " +urlConnection.getResponseCode());}}
            catch (IOException e){Log.e(LOG_TAG, "Problem retrieving the news JSON result.",e);}
            finally {
            if (urlConnection != null){urlConnection.disconnect();}
            if (inputStream != null){inputStream.close();}
                }
        return jsonResponse;}


}
