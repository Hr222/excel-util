package com.hr222;

import com.hr222.annutations.CellName;

import java.util.Date;

/**
 * @author: hr222
 * @create: 2019-10-07 22:39
 * @description: 测试
 **/
public class Test {

    private Integer id;

    @CellName("手机号码")
    private String phone;

    @CellName("创建日期")
    private Date createTime;

    @CellName("次数")
    private Integer time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
