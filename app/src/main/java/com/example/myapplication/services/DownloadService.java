package com.example.myapplication.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.myapplication.util.Constants;
import com.example.myapplication.util.DownLoadGFileUtil;

public class DownloadService extends Service {

    private int ID ;
    private Intent intent;

    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class DownFileBinder extends Binder{
        public String startDown(){
            ID = intent.getIntExtra("ID", -1);
            String url = Constants.DOWN_URL + String.valueOf(ID);
            String path = DownLoadGFileUtil.download(url, DownloadService.this, "picture.pgn");
            return path;
        }
        public void pauseDown(){};
        public void restartDown(){};
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.intent = intent;
        return super.onStartCommand(intent, flags, startId);
    }
}