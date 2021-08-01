package com.example.myapplication.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.Presenter.MyPagerPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.bean.CircleImageView;
import com.example.myapplication.contract.IMyPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyPageFragment extends BaseFragment<MyPagerPresenter, IMyPager.VP> {
    private int ID;
    private String jwt;
    private TextView mPermissionTv;
    private String mPassword;
    private Button mUpdateUserInformationBtn;//修改用户信息按钮
    private Button mMyGroupBtn;
    private Button mAddGroupBtn;
    private Button mMyMemberBtn;
    private Button mRelateUsBtn;
    private ProgressBar mProgressBar;
    private TextView mCurrentTv;//当前使用云盘大小
    private TextView mUserPermissionTv;
    private TextView mMaxTv;//云盘最大容量
    private CircleImageView mMyIcon;//头像
    private TextView mUserNickname;//用户昵称
    private int percentage=0;
    private int TAKE_PHOTO=1;

    public MyPageFragment(int ID,String jwt,String password) {
        this.ID = ID;
        this.jwt=jwt;
        this.mPassword=password;
    }

    @Override
    public IMyPager.VP getContract() {
        return new IMyPager.VP() {
            @Override
            public void requestMyData(int ID,String jwt) {
                new Thread(()->{
                    mPresenter.getContract().requestMyData(ID,jwt);
                }).start();

            }

            @Override
            public void requestMyDataResult(MyPagerBean myData) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client=new OkHttpClient();
                        FormBody formBody=new FormBody.Builder().add("uid", String.valueOf(ID)).build();
                        Request request=new Request.Builder().addHeader("Authorization",jwt).addHeader("userId", String.valueOf(ID)).url("http://39.98.41.126:31109/file/getImage").post(formBody).build();
                        try {
                            Response execute = client.newCall(request).execute();
                            byte[] bytes = execute.body().bytes();
                            createView(myData,bytes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        };
    }

    private void createView(MyPagerBean myData,byte[] bytes) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //改了一下  根据返回的flag来判断进行更改  返回为空的话会崩
                if(!myData.getFlag()){
                    Toast.makeText(getContext(),"发生未知错误，请重试!",Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("test", myData.getData().getNickname()+ myData.getData().getId()+ myData.getData().getUsername());
                    double useSpace=Double.valueOf(String.format("%.2f", myData.getData().getSpace()/(1024*1024)));//用户剩余可用空间
                    percentage= (int) ((1-useSpace)*100);
                    mUserNickname.setText(myData.getData().getNickname());
                    mCurrentTv.setText(useSpace+"GB");
                    mProgressBar.setProgress(percentage);
                    mPermissionTv.setText("userID"+"  "+ID+"");
                    Glide.with(getContext()).load(bytes).into(mMyIcon);
                }
            }
        });
    }

    @Override
    public void initView(View view) {
        mCurrentTv=view.findViewById(R.id.fragment_my_info_my_rest);
        mMaxTv=view.findViewById(R.id.fragment_my_info_my_max);
        mProgressBar=view.findViewById(R.id.fragment_my_info_my_progress);
        mUserNickname=view.findViewById(R.id.fragment_my_info_nickname_tv);
        mUserPermissionTv=view.findViewById(R.id.fragment_my_info_my_permission);
        mMyIcon=view.findViewById(R.id.fragment_my_info_my_icon);
        mUpdateUserInformationBtn=view.findViewById(R.id.edit_btn);
        mMyGroupBtn=view.findViewById(R.id.group_by_btn);
        mMyMemberBtn=view.findViewById(R.id.member_btn);
        mRelateUsBtn=view.findViewById(R.id.relate_us_btn);
        mAddGroupBtn=view.findViewById(R.id.add_group_btn);
        mProgressBar.setProgress(percentage);
        mPermissionTv=view.findViewById(R.id.fragment_my_info_my_permission);
        mMyGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),GroupActivity.class);
                intent.putExtra("ID",ID);
                intent.putExtra("jwt",jwt);
                startActivity(intent);
            }
        });
        mMyMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),GroupActivity.class);
                intent.putExtra("ID",ID);
                intent.putExtra("jwt",jwt);
                startActivity(intent);
            }
        });
        mAddGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view1=View.inflate(getContext(),R.layout.alter_dialog_add_group_background,null);
                EditText groupName=(EditText)view1.findViewById(R.id.edit_group_name_et);
                Button sureBtn=(Button)view1.findViewById(R.id.group_sure_btn);
                Button falseBtn=(Button)view1.findViewById(R.id.group_false_btn);
                builder.setView(view1);
                AlertDialog dialog=builder.create();
                falseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                sureBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String newGroupName=groupName.getText().toString().trim();
                                OkHttpClient client=new OkHttpClient();
                                FormBody formBody=new FormBody.Builder().add("userid", String.valueOf(ID)).add("groupName",newGroupName).build();
                                Request request=new Request.Builder().addHeader("Authorization",jwt).addHeader("userId", String.valueOf(ID)).url("http://39.98.41.126:31109/group/creatGroup").post(formBody).build();
                                try {
                                    Response execute = client.newCall(request).execute();
                                    String responseData = execute.body().string();
                                    Log.d("===========",responseData);
                                    JSONObject jsonObject=new JSONObject(responseData);
                                    Boolean flag=jsonObject.getBoolean("flag");
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(flag){
                                                Toast.makeText(getContext(),"创建成功",Toast.LENGTH_SHORT).show();

                                            }else{
                                                Toast.makeText(getContext(),"创建失败",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });
                dialog.show();
            }
        });

        mMyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               choosePhoto();
            }
        });
        mUpdateUserInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view1=View.inflate(getContext(),R.layout.alter_dialog_background,null);
                EditText nickname=(EditText)view1.findViewById(R.id.dialog_nickname_et);
                EditText password=(EditText)view1.findViewById(R.id.dialog_password_et);
                Button sureBtn=(Button)view1.findViewById(R.id.sure_btn);
                Button falseBtn=(Button)view1.findViewById(R.id.false_btn);
                builder.setView(view1);
                AlertDialog dialog=builder.create();
                sureBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!nickname.getText().toString().equals(mUserNickname.getText().toString())&&!mPassword.equals(password)
                        ){
                            RequestBody requestBody = new MultipartBody.Builder()
                                    .setType(MultipartBody.FORM).addFormDataPart("uid", String.valueOf(ID))
                                    .addFormDataPart("password",mPassword)
                                    .addFormDataPart("nickname",nickname.getText().toString())
                                    .build();
                            Request request = new Request.Builder().addHeader("Authorization",jwt).addHeader("userId", String.valueOf(ID))
                                    .url("http://39.98.41.126:31109/user/updateMsg")
                                    .post(requestBody)
                                    .build();
                            OkHttpClient okHttpClient=new OkHttpClient();
                            okHttpClient.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String responseData=response.body().string();
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                dialog.dismiss();
                                                Log.d("===========",responseData);
                                                JSONObject jsonObject=new  JSONObject(responseData);
                                                Boolean flag=jsonObject.getBoolean("flag");
                                                if(flag){
                                                    Toast.makeText(getContext(),"成功修改用户信息",Toast.LENGTH_SHORT).show();
                                                    mUserNickname.setText(nickname.getText().toString());
                                                }else {
                                                    Toast.makeText(getContext(),"出bug了",Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
                falseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getContract().requestMyData(ID,jwt);
    }

    @Override
    public void initListener() { }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_my_information;
    }

    @Override
    public MyPagerPresenter getPresenterInstance() {
        return new MyPagerPresenter();
    }

    @Override
    public void destroy() {

    }

    /**
     * 打开相册
     */
    private void choosePhoto() {
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, TAKE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TAKE_PHOTO){
            if(data!=null){
                Uri uri=data.getData();
                Glide.with(getContext()).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mMyIcon);
                String[] filePathColumn={MediaStore.Images.Media.DATA};
                Cursor cursor=getActivity().getContentResolver().query(uri,filePathColumn,null,null,null);
                cursor.moveToFirst();
                int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
                String picturePath=cursor.getString(columnIndex);
                uploadImg(picturePath);
            }
        }
    }

    private void uploadImg(String imgPath){
        File file = new File(imgPath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",file.getName(),fileBody)
                .addFormDataPart("uid", String.valueOf(ID))
                .build();
        Request request = new Request.Builder().header("Authorization",jwt).addHeader("userId", String.valueOf(ID))
                .url("http://39.98.41.126:31109/user/updateImage")
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.d("===========",responseData);
                            JSONObject jsonObject=new  JSONObject(responseData);
                            Boolean flag=jsonObject.getBoolean("flag");
                            if(flag){
                                Toast.makeText(getContext(),"成功修改用户信息",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(),"出bug了",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
