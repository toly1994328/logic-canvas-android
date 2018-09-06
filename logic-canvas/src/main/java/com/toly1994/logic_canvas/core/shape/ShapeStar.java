package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/4 0004:9:31<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：星型
 */
public class ShapeStar extends Shape implements Serializable, Cloneable {
    /**
     * 五角星f
     **/
    public int mnum = 5;//角数
    public float mR = 100f;//外接圆半径
    public float mr = 0f;//内接圆半径

    public ShapeStar num(int num) {
        this.mnum = num;
        return this;
    }

    public ShapeStar R(float r) {
        mR = r;
        return this;
    }

    public ShapeStar r(float r) {
        mr = r;
        return this;
    }

    /**
     * 常规模式
     */
    public static final int MODE_DEFAULT = 0;
    /**
     * 正多角星模式
     */
    public static final int MODE_REGULAR = 1;
    /**
     * 正n边形
     */
    public static final int MODE_POLYGON = 2;
    private int mMode;

    /**
     * 浅克隆
     *
     * @return 浅克隆对象
     */
    public ShapeStar clone() {
        ShapeStar clone = null;
        try {
            clone = (ShapeStar) super.clone();
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


    public ShapeStar() {
        this(0);
    }

    public ShapeStar(int mode) {
        mMode = mode;
    }

    @Override
    public Path formPath() {

        switch (mMode) {
            case MODE_DEFAULT:
                return nStarPath(this);
            case MODE_REGULAR:
                return regularStarPath(this);
            case MODE_POLYGON:
                return regularPolygonPath(this);
            default:
                return null;
        }
    }


    @NonNull
    private Path nStarPath(ShapeStar shape) {
        Path path = new Path();

        int num = shape.mnum;
        float R = shape.mR;
        float r = shape.mr;

        float perDeg = 360 / num;
        float degA = perDeg / 2 / 2;
        float degB = 360 / (num - 1) / 2 - degA / 2 + degA;

        path.moveTo((float) (Math.cos((degA + perDeg * 0) / 180 * Math.PI) * R + R * Math.cos(degA / 180 * Math.PI)),
                (float) (-Math.sin((degA + perDeg * 0) / 180 * Math.PI) * R + R));

        for (int i = 0; i < num; i++) {

            path.lineTo((float) (Math.cos((degA + perDeg * i) / 180 * Math.PI) * R + R * Math.cos(degA / 180 * Math.PI)),
                    (float) (-Math.sin((degA + perDeg * i) / 180 * Math.PI) * R + R));
            path.lineTo((float) (Math.cos((degB + perDeg * i) / 180 * Math.PI) * r + R * Math.cos(degA / 180 * Math.PI)),
                    (float) (-Math.sin((degB + perDeg * i) / 180 * Math.PI) * r + R));
        }
        path.close();
        return path;
    }

    /**
     * 画正n角星的路径:画矩形路径:x:宽 y:高 r:圆角
     */
    public Path regularStarPath(ShapeStar shape) {
        int num = shape.mnum;
        float R = shape.mR;

        float degA, degB;
        if (num % 2 == 1) {
            degA = 360 / num / 2 / 2;
            degB = 180 - degA - 360 / num / 2;
        } else {
            degA = 360 / num / 2;
            degB = 180 - degA - 360 / num / 2;
        }
        shape.mr = (float) (R * Math.sin(degA / 180 * Math.PI) / Math.sin(degB / 180 * Math.PI));
        ;
        return nStarPath(shape);
    }

    /**
     * 画正n边形的路径
     */
    public Path regularPolygonPath(ShapeStar shape) {
        int num = shape.mnum;
        float R = shape.mR;
        shape.mr = (float) (R * (Math.cos(360. / num / 2 / 180 * Math.PI)));//!!一点解决
        return nStarPath(shape);
    }
}
