package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/4 0004:9:31<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ShapeArc extends Shape {

    /**
     * 角度
     */
    public Float mang = 0f;

    /**
     * 半径
     */
    public Float mr = 0f;//线与X轴夹角(角度数)

    public ShapeArc() {

    }

    /**
     * 一参构造函数生成圆
     * @param everything
     */
    public ShapeArc(Object everything) {
        mang = 360f;
    }

    @Override
    public Path formPath() {
        Path path = new Path();
        Float r = this.mr;
        Float b = this.mb;
        Float ang = this.mang;
        float deta = -r;
        RectF rectF = new RectF(deta, deta, deta + 2 * r - b, deta + 2 * r - b);
        path.addArc(rectF, 0, ang.floatValue());
        return path;
    }

    public ShapeArc ang(Float ang) {
        this.mang = ang;
        return this;
    }

    public ShapeArc r(Float r) {
        this.mr = r;
        return this;
    }
}
