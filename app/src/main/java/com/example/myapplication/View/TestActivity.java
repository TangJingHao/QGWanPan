package com.example.myapplication.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.IsRegister;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.contract.IPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/27/14:28
 * @Description: 用来测试页面的
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button=this.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IPost post=BaseCreator.create(IPost.class);
                post.registerData("54321","12345","QGer").enqueue(new Callback<IsRegister>() {
                    @Override
                    public void onResponse(Call<IsRegister> call, Response<IsRegister> response) {
                        Boolean flag=response.body().getFlag();
                        String Msg=response.body().getMessage();
                        Log.d("==============",flag+"");
                        Log.d("==============",Msg);
                    }
                    @Override
                    public void onFailure(Call<IsRegister> call, Throwable t) {

                    }
                });
            }
        });
    }
}

