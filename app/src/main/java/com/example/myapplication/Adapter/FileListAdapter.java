package com.example.myapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBean.FileDataBean;
import com.example.myapplication.Event.SelectItemEvent;
import com.example.myapplication.Event.SetBottomNavigationEvent;
import com.example.myapplication.Event.FileLongClickEvent;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.MyViewHolder> {

    private List<FileDataBean> files;
<<<<<<< Updated upstream
    /*public FileListAdapter(List <FileDataBean> files){
        this.files = files;
    }*/

    public FileListAdapter(){

=======
    private List<String> selectedItem;
    public FileListAdapter(List<FileDataBean> files){
        this.files = files;
        selectedItem = new ArrayList<String>();
>>>>>>> Stashed changes
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fileitem,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        myViewHolder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selectedItem.add(myViewHolder.getAdapterPosition() + "");
                    files.get(myViewHolder.getAdapterPosition()).setSelected(true);
                }else{
                    selectedItem.remove(myViewHolder.getAdapterPosition() + "");
                    files.get(myViewHolder.getAdapterPosition()).setSelected(false);
                }
                EventBus.getDefault().post(new SelectItemEvent(selectedItem.size() + ""));
                Log.d("Hx","已选择");
            }
        });

        myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EventBus.getDefault().post(new FileLongClickEvent("LongClick"));
                EventBus.getDefault().post(new SetBottomNavigationEvent(true));
                return false;
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
<<<<<<< Updated upstream
        holder.filename.setText(files.get(position).getId());
        holder.date.setText(files.get(position).getDate());
=======
        /*holder.filename.setText(files.get(position).getId());
        holder.date.setText(files.get(position).getDate());*/

>>>>>>> Stashed changes
    }

    @Override
    public int getItemCount() {
<<<<<<< Updated upstream
        return 10;
=======
        /*return files.size();*/
        return 20;
>>>>>>> Stashed changes
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView filename;
        TextView date;
        CheckBox select;

        MyViewHolder(View view){
            super(view);
            filename = view .findViewById(R.id.fileItem_tv_filename);
            date = view.findViewById(R.id.fileItem_tv_date);
            select = view.findViewById(R.id.fileitem_cb_select);
        }
    }
    //返回多选文件
    public List<FileDataBean> GetSelectedList(){
        return files;
    }
}
