package com.example.myapplication.Model;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/08/02/15:09
 * @Description:
 */
public class MemberData {
    private String groupName;
    private String memberName;

    public MemberData() {
    }

    public MemberData(String groupName, String memberName) {
        this.groupName = groupName;
        this.memberName = memberName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
