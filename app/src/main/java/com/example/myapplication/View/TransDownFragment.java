package com.example.myapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

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
    private DownloadService downloadService;
    private DownloadService.DownFileBinder downFileBinder;
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
        if (doingData!=null){
            downServiceIntent = new Intent(this.getContext(),DownloadService.class);
            downServiceIntent.putExtra("ID",doingData.get(1).getId());
            this.getActivity().startService(downServiceIntent);
        }
    }

    private void initView() {
        allStartBtn = view.findViewById(R.id.down_start_bt);
        doingRv = view.findViewById(R.id.down_doing_rv);
        finishRv = view.findViewById(R.id.down_finish_rv);
    }

}
