package com.example.myapplication.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.myapplication.util.Constants;
import com.example.myapplication.util.DownLoadGFileUtil;

public class DownloadService extends Service {

    private int ID ;

    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class DownFileBinder extends Binder{
        public void startDown(){};
        public void pauseDown(){};
        public void restartDown(){};
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ID = intent.getIntExtra("ID",-1);
        if(ID!=-1){
            String url = Constants.DOWN_URL + "4";
            DownLoadGFileUtil.download(url,this,"picture.pgn");
        }
        return super.onStartCommand(intent, flags, startId);
    }
}