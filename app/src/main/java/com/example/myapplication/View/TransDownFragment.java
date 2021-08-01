package com.example.myapplication.View;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.DownDoingRvAdapter;
import com.example.myapplication.Adapter.DownFinishRvAdapter;
import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.DataBean.FileDownData;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.services.DownloadService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/31 21:06
 */
public class TransDownFragment extends Fragment {

    private View view;
    private Button allStartBtn;
    private RecyclerView doingRv,finishRv;
    private List<FileDownData> doingData = new ArrayList<>(),finishData = new ArrayList<>();
    private Intent downServiceIntent;
    private DownloadService.DownFileBinder downFileBinder;
    private String path;
    public TransDownFragment() {
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trans_down,container, false);
        FileDownData test = new FileDownData();
        test.setDocname("文件");
        test.setId(4);
        doingData.add(test);
        doingData.add(test);
        doingData.add(test);
        doingData.add(test);

        finishData.add(test);
        finishData.add(test);
        finishData.add(test);
        finishData.add(test);

        initView();
        reNewRecycleView();
        initDown();
        return view;
    }


    private void reNewRecycleView() {
        DownDoingRvAdapter doingRvAdapter = new DownDoingRvAdapter(doingData);
        DownFinishRvAdapter finishRvAdapter = new DownFinishRvAdapter(finishData);
        LinearLayoutManager doLinearLayoutManager,fiLinearLayoutManager;
        doLinearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        fiLinearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        doingRv.setLayoutManager(doLinearLayoutManager);
        finishRv.setLayoutManager(fiLinearLayoutManager);
        doingRv.setAdapter(doingRvAdapter);
        finishRv.setAdapter(finishRvAdapter);
        doingRvAdapter.notifyDataSetChanged();
        finishRvAdapter.notifyDataSetChanged();
    }

    private void initDown() {
        if (doingData!=null){
            downServiceIntent = new Intent(this.getContext(),DownloadService.class);
            downServiceIntent.putExtra("ID",doingData.get(1).getId());
            this.getActivity().startService(downServiceIntent);
            ServiceConnection connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    new Thread(()->{
                        downFileBinder = (DownloadService.DownFileBinder)service;
                        path = downFileBinder.startDown();
                    }).start();
                    getActivity().runOnUiThread(()->{
                        if(path!=null){
                            Toast.makeText(getActivity().getParent(),"下载成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
        }
    }


    private void initView() {
        allStartBtn = view.findViewById(R.id.down_start_bt);
        doingRv = view.findViewById(R.id.down_doing_rv);
        finishRv = view.findViewById(R.id.down_finish_rv);
    }

}
