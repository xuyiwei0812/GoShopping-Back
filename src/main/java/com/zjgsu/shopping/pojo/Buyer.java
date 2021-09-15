// God and me wrote this code,
// but only God know what it is now.
//                        -- K-desperate


package com.zjgsu.shopping.pojo;

public class Buyer {

    /**
    用户编号
    姓名
    住址
    联系电话
     */
    private int buyerCode;
    private String name;
    private String location;
    private String phone;

    public Buyer(String name, String location, String phone) {
        this.name = name;
        this.location = location;
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 构造函数
     */


    public int getBuyerCode(){
        return buyerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
