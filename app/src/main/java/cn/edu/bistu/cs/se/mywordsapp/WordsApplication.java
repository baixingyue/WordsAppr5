package cn.edu.bistu.cs.se.mywordsapp;

import android.app.Application;
import android.content.Context;

//方便得到context
public class WordsApplication extends Application{
    private static Context context;
    public static Context getContext(){
        return WordsApplication.context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        WordsApplication.context=getApplicationContext();
    }
}
