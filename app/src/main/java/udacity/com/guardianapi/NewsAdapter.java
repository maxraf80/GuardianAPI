package udacity.com.guardianapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news, parent, false);
        }

        News news = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.photo);
        Glide.with(getContext()).load(news.getPhoto()).into(imageView);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        titleTextView.setText(news.getTitle());

        TextView authorText = (TextView) listItemView.findViewById(R.id.author);
        authorText.setText(news.getJournalist());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(news.getDate());

        TextView themeTextView = (TextView) listItemView.findViewById(R.id.theme);
        themeTextView.setText(news.getCategory1());



        return listItemView;
    }
}
