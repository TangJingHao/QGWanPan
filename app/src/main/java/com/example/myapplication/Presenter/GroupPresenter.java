package com.example.myapplication.Presenter;

import com.example.myapplication.Model.GroupModel;
import com.example.myapplication.View.GroupFragment;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.IGroup;

import java.util.List;

/**
 * @Name： GroupPresenter
 * @Description：
 * @Author： Suzy.Mo
 * @Date： 2021/7/27 13:52
 */
public class GroupPresenter extends BasePresenter<GroupModel, GroupFragment, IGroup.VP> {
    @Override
    public GroupModel getModelInstance() {
        return new GroupModel(this);
    }

    @Override
    public IGroup.VP getContract() {
        return new IGroup.VP() {
            @Override
            public void requestGroupData() {

            }

            @Override
            public void requestGroupDataResult(List<String> groupData) {

            }
        };
    }
}
