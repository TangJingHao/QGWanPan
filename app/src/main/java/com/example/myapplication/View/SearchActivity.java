package com.example.myapplication.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.SearchHistoryAdapter;
import com.example.myapplication.DataBean.IsDeleteHistory;
import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.Presenter.SearchPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.contract.ISearch;
import com.example.myapplication.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 18:04
 */
public class SearchActivity extends BaseActivity<SearchPresenter, ISearch.VP> {

    private int ID;
    private String jwt;
    private SearchHistoryBean mData = new SearchHistoryBean();

    private RecyclerView historyRv;
    private SearchView mSearchView;
    private ProgressBar mProcessBar;
    private TextView resultTV,historyTv;
    private ImageView deleteIv,backTv;
    private View viewBefore;

    private SearchHistoryAdapter searchHistoryAdapter;

    @Override
    public ISearch.VP getContract() {
        return new ISearch.VP() {
            @Override
            public void searchFile(String docname, int uid, String jwt) {
                if (mPresenter!=null){
                    mPresenter.getContract().searchFile(docname,uid,jwt);
                }
            }

            @Override
            public void searchFileResult(SearchResult searchFileData) {
                runOnUiThread(()->{
                    mProcessBar.setVisibility(View.INVISIBLE);
                    resultTV.setVisibility(View.VISIBLE);
                    viewBefore.setVisibility(View.INVISIBLE);
                    if (searchFileData!=null){
                        resultTV.setText(searchFileData.getMessage());
                    }else {
                        Toast.makeText(SearchActivity.this,"网络错误查询失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void searchHistory(int uid, int num, String jwt) {
                if(mPresenter!=null){
                    mPresenter.getContract().searchHistory(uid,num,jwt);
                }
            }

            @Override
            public void searchHistoryResult(SearchHistoryBean searchHistoryResult) {
                runOnUiThread(()->{
                    historyRv.setVisibility(View.VISIBLE);
                    //返回主线程更新UI
                    Log.d("SearchActivity","searchHistoryResult"+searchHistoryResult.getData().get(0)+searchHistoryResult.getData().get(1));
                    initHistoryView(searchHistoryResult);
                    //searchHistoryAdapter = new SearchHistoryAdapter(searchHistoryResult);
                    //historyRv.setAdapter(searchHistoryAdapter);
                    //searchHistoryAdapter.notifyDataSetChanged();
                    Toast.makeText(SearchActivity.this, searchHistoryResult.getData().get(1).getWord(),Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void deleteHistory(int uid,String jwt) {
                if (mPresenter!=null){
                    mPresenter.getContract().deleteHistory(uid, jwt);
                }

            }

            @Override
            public void deleteHistoryResult(IsDeleteHistory isDeleteHistory) {
                //getContract().searchHistory(ID,20,jwt);
                runOnUiThread(()->{
                    historyRv.setVisibility(View.INVISIBLE);
                    //返回主线程更新UI
                    Toast.makeText(SearchActivity.this,isDeleteHistory.getMessage(),Toast.LENGTH_SHORT).show();
                });
            }
        };
    }

    @Override
    public void initView() {
        this.ID=getIntent().getIntExtra("ID",-1);//接受用户的id
        this.jwt = getIntent().getStringExtra("jwt");
        resultTV = findViewById(R.id.search_rescult_tv);
        historyRv = findViewById(R.id.search_history_rv);
        mSearchView = findViewById(R.id.search_searchView);
        mProcessBar = findViewById(R.id.search_progress_bar);
        deleteIv = findViewById(R.id.search_history_delete_iv);
        backTv = findViewById(R.id.search_back);
        viewBefore = findViewById(R.id.search_history_before);
        historyTv = findViewById(R.id.search_history_title);
        mProcessBar.setVisibility(View.INVISIBLE);
        resultTV.setVisibility(View.INVISIBLE);

        //点击搜索按钮时的监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //提交搜索内容时
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProcessBar.setVisibility(View.VISIBLE);
                historyRv.setVisibility(View.INVISIBLE);
                resultTV.setVisibility(View.INVISIBLE);
                getContract().searchFile(query,ID,jwt);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //getContract().searchHistory(ID,Constants.SEARCH_HISTORY_NUM,jwt);
    }

    private void initHistoryView(SearchHistoryBean data) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        historyRv.setLayoutManager(gridLayoutManager);
        searchHistoryAdapter = new SearchHistoryAdapter(data);
        historyRv.setAdapter(searchHistoryAdapter);
        searchHistoryAdapter.notifyDataSetChanged();
    }

    private SearchHistoryBean initHistoryData() {
        SearchHistoryBean historyBean = new SearchHistoryBean();
        List<SearchHistoryBean.DataBean> dataBeans= new ArrayList<>();
        SearchHistoryBean.DataBean data = new SearchHistoryBean.DataBean(1,"文件1",1);
        SearchHistoryBean.DataBean data2 = new SearchHistoryBean.DataBean(1,"文件2",1);
        SearchHistoryBean.DataBean data3 = new SearchHistoryBean.DataBean(1,"文件3",1);
        dataBeans.add(data);
        dataBeans.add(data2);
        dataBeans.add(data3);
        historyBean.setData(dataBeans);
        return historyBean;
    }


    @Override
    public void initData() {
        //mPresenter.getContract().searchHistory(ID,Constants.SEARCH_HISTORY_NUM,jwt);
    }

    @Override
    public void initListener() {
        backTv.setOnClickListener(this);
        deleteIv.setOnClickListener(this);
        historyTv.setOnClickListener(this);
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public SearchPresenter getPresenterInstance() {
        return new SearchPresenter();
    }

    @Override
    public void destroy() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_back:
                finish();
                break;
            case R.id.search_history_delete_iv:
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("");
                dialog.setMessage("确认取消所以的浏览记录？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getContract().deleteHistory(ID,jwt);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            }
            case R.id.search_history_title:
                getContract().searchHistory(ID,Constants.SEARCH_HISTORY_NUM,jwt);
        }
    }
}
