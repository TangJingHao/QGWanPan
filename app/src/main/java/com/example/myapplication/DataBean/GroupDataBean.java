package com.example.myapplication.DataBean;

import android.widget.Button;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/08/02/15:57
 * @Description:
 */
public class GroupDataBean {
    private String name;
    private Button btn;

    public GroupDataBean() {
    }

    public GroupDataBean(String name, Button btn) {
        this.name = name;
        this.btn = btn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
