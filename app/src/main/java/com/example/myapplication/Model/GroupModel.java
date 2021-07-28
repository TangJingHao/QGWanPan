package com.example.myapplication.Model;

import com.example.myapplication.Presenter.GroupPresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IGroup;

/**
 * @Name： GroupModel
 * @Description：
 * @Author： Suzy.Mo
 * @Date： 2021/7/27 13:56
 */
public class GroupModel extends BaseModel<GroupPresenter, IGroup.M> {

    /**
     * 根据传入的P层与P层绑定
     * @param mPresenter
     */
    public GroupModel(GroupPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public IGroup.M getContract() {
        return new IGroup.M() {
            @Override
            public void requestGroupData() throws Exception {

            }
        };
    }
}
