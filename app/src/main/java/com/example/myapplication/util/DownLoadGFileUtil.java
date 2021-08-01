package com.example.myapplication.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/8/1 19:36
 */
public class DownLoadGFileUtil {

    public static String download(String url, Context context,String fileName) {
        final String TAG = "DownLoadGFileUtil";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        File file = new File(Environment.getExternalStorageDirectory(), String.valueOf(downloadManager.enqueue(request)) + fileName);
        request.setDestinationUri(Uri.fromFile(file));
        String path = file.getAbsolutePath();
        if (downloadManager == null)
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Log.d(TAG,path);
        return path;
    }
}
