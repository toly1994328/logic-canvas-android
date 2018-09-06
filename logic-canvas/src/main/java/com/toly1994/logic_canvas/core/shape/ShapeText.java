package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;

import java.io.Serializable;

/**
 * 作者：张风捷特烈
 * 时间：2018/7/6:10:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class ShapeText extends Shape implements Serializable, Cloneable {
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

    /**
     * 浅克隆
     *
     * @return 浅克隆对象
     */
    public ShapeText clone() {
        ShapeText clone = null;
        try {
            clone = (ShapeText) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * 深克隆对象
     *
     * @return 深克隆对象
     */
    public ShapeText deepClone() {
        ShapeText clone = null;
        try {
            clone = (ShapeText) super.clone();
            clone.mp = mp.clone();
            clone.ma = ma.clone();
            clone.mcoo = mcoo.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

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
