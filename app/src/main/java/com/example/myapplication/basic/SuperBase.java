package com.example.myapplication.basic;

/**
 * @Name： SuperBase
 * @Description： CONTRACT 契约接口泛型
 * 不用用户主动去创建接口，只需要传入接口就可以自动创建了
 * @Author： Suzy.Mo
 * @Date： 2021/7/26 11:32
 */
public abstract class SuperBase<CONTRACT> {
    public abstract CONTRACT getContract();
}
