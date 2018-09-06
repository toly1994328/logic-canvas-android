package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;
import android.graphics.RectF;

import java.io.Serializable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/4 0004:9:31<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：矩形
 */
public class ShapeRect extends Shape implements Serializable, Cloneable {
    /**
     * 长
     */
    public float mx = 0f;
    /**
     * 宽
     */
    public float my = 0f;

    /**
     * 半径
     */
    public float mr = 0f;//线与X轴夹角(角度数)


    @Override
    public Path formPath() {
        Path path = new Path();
        float width = this.mx - this.mb;
        float height = this.my - this.mb;

        float r = this.mr;

        RectF rectF = new RectF(0, -height, width,0);
        path.addRoundRect(rectF, r, r, Path.Direction.CW);

        return path;
    }

    /**
     * 浅克隆
     *
     * @return 浅克隆对象
     */
    public ShapeRect clone() {
        ShapeRect clone = null;
        try {
            clone = (ShapeRect) super.clone();
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
    public ShapeRect deepClone() {
        ShapeRect clone = null;
        try {
            clone = (ShapeRect) super.clone();
            clone.mp = mp.clone();
            clone.ma = ma.clone();
            clone.mcoo = mcoo.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }


    public ShapeRect x(float mx) {
        this.mx = mx;
        return this;
    }

    public ShapeRect y(float my) {
        this.my = my;
        return this;
    }

    public ShapeRect r(float mr) {
        this.mr = mr;
        return this;
    }


}
