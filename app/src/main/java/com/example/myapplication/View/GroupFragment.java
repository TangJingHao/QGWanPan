package com.example.myapplication.View;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Presenter.GroupPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IGroup;

import java.util.List;
import java.util.function.DoublePredicate;

public class GroupFragment extends BaseFragment<GroupPresenter, IGroup.VP> {

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

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_group;
    }

    @Override
    public GroupPresenter getPresenterInstance() {
        return new GroupPresenter();
    }

    @Override
    public void destroy() {

    }
}
