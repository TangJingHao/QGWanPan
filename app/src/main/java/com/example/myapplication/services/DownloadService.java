package com.example.myapplication.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownloadService extends Service {

    private int ID ;

    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class DownFile extends Binder{
        public void startDown(){};
        public void pauseDown(){};
        public void restartDown(){};
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ID = intent.getIntExtra("ID",-1);
        if(ID!=-1){

        }
        return super.onStartCommand(intent, flags, startId);
    }
}