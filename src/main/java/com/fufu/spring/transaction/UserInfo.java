package com.fufu.spring.transaction;

import java.io.Serializable;

/**
 * Created by zhoujunfu on 2018/8/27.
 */
public class UserInfo implements Serializable {

    Integer id;
    String Name;
    Integer balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
