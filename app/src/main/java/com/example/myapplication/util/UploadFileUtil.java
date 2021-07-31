package com.example.myapplication.util;


import androidx.annotation.Nullable;

import com.example.myapplication.Model.ToUploadTask;
import com.example.myapplication.Model.UploadRequestBody;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLConnection;
import java.security.MessageDigest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UploadFileUtil {
    public static int uploadFile(ToUploadTask task,String userID) {
        File file = new File(task.getFilepath());
        /**
         * 检验文件时候存在
         */
        if (!file.exists() && !file.isFile()) {
            return Constants.FAIL_CODE;
        }
        /**
         * 判断文件的md5值
         */
        String fileMD5 = getFileMD5(file);
        if(!fileMD5.equals(task.getMd5())){
            return Constants.FAIL_CODE;
        }
        UploadRequestBody requestBody=new UploadRequestBody(file,task.getSkipSize(),getMediaType(file.getName()));
        MultipartBody requestForDataBody = new MultipartBody.Builder().addFormDataPart("userId", userID).addFormDataPart("md5", fileMD5).
                addFormDataPart("filePath", file.getAbsolutePath()).addFormDataPart("file", file.getName(), requestBody)
                .build();
        Request request = new Request.Builder().url(Constants.ServerURL).post(requestForDataBody).build();
        OkHttpClient client=new OkHttpClient.Builder().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
        return Constants.SUCCESS_CODE;
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
    public static MediaType getMediaType(@Nullable String filename) {
        if (filename == null) return null;
        int index = filename.lastIndexOf(".") + 1;
        String fileSuffix = filename.substring(index);
        String contentType = URLConnection.guessContentTypeFromName(fileSuffix);
        return contentType != null ? MediaType.parse(contentType) : null;
    }

}
