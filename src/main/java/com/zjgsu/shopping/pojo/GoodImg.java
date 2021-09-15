package com.zjgsu.shopping.pojo;

import java.util.List;

public class GoodImg {
    private int GoodId;
    private List<String> Img;

    public GoodImg(List<String> img) {
        Img = img;
    }

    public List<String> getImg() {
        return Img;
    }

}
