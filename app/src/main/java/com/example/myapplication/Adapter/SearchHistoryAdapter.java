package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/29 13:57
 */
public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {


    SearchHistoryBean mDatas;

    public SearchHistoryAdapter(SearchHistoryBean mDatas) {
        this.mDatas = mDatas;
    }

    @NonNull
    @NotNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new SearchHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_history_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchHistoryViewHolder holder, int position) {
        holder.nameTv.setText(mDatas.getData().get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return mDatas.getData().size();
    }


    public static class SearchHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        public SearchHistoryViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.search_item_name_tv);
        }
    }
}
