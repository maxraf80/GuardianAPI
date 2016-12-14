package udacity.com.guardianapi;

public class New {

    private String photo;
    private String title;
    private String journalist;
    private String date;
    private String category1;
    private String category2;
    private String url;

    public New(String mPhoto, String mTitle, String mJournalist, String mDate, String mUrl) {

        photo = mPhoto;
        title = mTitle;
        journalist = mJournalist;
        date = mDate;
        url = mUrl;
    }

    public New(String mPhoto, String mTitle, String mJournalist, String mDate, String mCategory1, String mCategory2, String mUrl) {

        photo = mPhoto;
        title = mTitle;
        journalist = mJournalist;
        category1 = mCategory1;
        category2 = mCategory2;
        date = mDate;
        url = mUrl;
    }
    public String getPhoto(){return photo;}
    public String getTitle(){return title;}
    public String getJournalist(){return journalist;}
    public String getDate(){return date;}
    public String getCategory1(){return category1;}
    public String getCategory2(){return category2;}
    public String getUrl(){return url;}

}
