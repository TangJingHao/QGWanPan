package com.example.myapplication.Model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * 用于断点上传的类
 */
public class UploadRequestBody extends RequestBody {
    private final File mFile;
    private final long mSkipSize;//记录断点的位置
    private final MediaType mMediaType;

    public UploadRequestBody(File mFile, long mSkipSize, MediaType mMediaType) {
        this.mFile = mFile;
        if(mSkipSize<0){
            throw new IllegalArgumentException("断点为负数"+mSkipSize);
        }
        if(mSkipSize>mFile.length()){
            throw new IllegalArgumentException("断点位置大于文件的位置");
        }
        this.mSkipSize = mSkipSize;
        this.mMediaType = mMediaType;
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length()-mSkipSize;

    }

    @Override
    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
        InputStream input=null;
        Source source=null;
        try {
            input=new FileInputStream(mFile);
            if(mSkipSize>0){
                input.skip(mSkipSize);
            }
            source= Okio.source(input);
            bufferedSink.writeAll(source);
        }finally {
            source.close();
            input.close();
        }
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return mMediaType;
    }
}
