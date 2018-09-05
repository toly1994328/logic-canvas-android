package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;

/**
 * 作者：张风捷特烈
 * 时间：2018/7/6:10:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class ShapeText extends Shape {
    /**
     * 文字
     */
    public String mStr;
    /**
     * 文字大小
     */
    public int mSize;
    /**
     * 对齐方式 ">"
     */
    public String mAl="=";



    public ShapeText str(String str) {
        this.mStr = str;
        return this;
    }

    public ShapeText size(int size) {
        this.mSize = size;
        return this;
    }
    public ShapeText al(String al) {
        this.mAl = al;
        return this;
    }

    @Override
    public Path formPath() {
        return null;
    }
}
