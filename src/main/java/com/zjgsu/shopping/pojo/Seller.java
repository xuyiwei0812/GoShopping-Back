// God and me wrote this code,
// but only God know what it is now.
//                        -- K-desperate

package com.zjgsu.shopping.pojo;

public class Seller {
    /**
     * 卖家编号
     * 姓名
     * 头像(暂定)
     * 账户(暂定)
     * 密码
     * 地址
     * 电话

     */
    private int sellerId;
    private String name;
    private String profilePicture;//base64
    private String account;
    private String password;
    private String location;
    private String phone;

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }


    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Seller(String name, String profilePicture, String account, String password, String location, String phone) {
        this.name = name;
        this.profilePicture = profilePicture;
        this.account = account;
        this.password = password;
        this.location = location;
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
