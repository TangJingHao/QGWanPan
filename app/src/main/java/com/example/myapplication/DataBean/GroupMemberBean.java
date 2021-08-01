package com.example.myapplication.DataBean;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/08/01/17:04
 * @Description:
 */
public class GroupMemberBean {
    private String memberName;
    private String groupName;
    private String permissionName;

    public GroupMemberBean() {
    }

    public GroupMemberBean(String memberName, String groupName, String permissionName) {
        this.memberName = memberName;
        this.groupName = groupName;
        this.permissionName = permissionName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
