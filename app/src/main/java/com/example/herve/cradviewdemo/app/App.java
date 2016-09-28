package com.example.herve.cradviewdemo.app;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created           :Herve on 2016/9/28.
 *
 * @ Author          :Herve
 * @ e-mail          :lijianyou.herve@gmail.com
 * @ LastEdit        :2016/9/28
 * @ projectName     :CradViewDemo
 * @ version
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init("Herve")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo() ;              // default shown
//                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
//                .methodOffset(2);             // default 0
    }
}
