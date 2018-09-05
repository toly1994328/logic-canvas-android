package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;

import com.toly1994.logic_canvas.bean.Pos;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/4 0004:9:31<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：线
 */
public class ShapeLine extends Shape {
    /**
     * 依靠点集绘制
     */
    public Pos[] mps;


    @Override
    public Path formPath() {
        Pos[] poss = this.mps;
        Path path = new Path();
        path.moveTo(poss[0].x, -poss[0].y);
        for (Pos pos : poss) {
            pos.refY();
            path.lineTo(pos.x, pos.y);
        }
        return path;
    }


    // TODO 直线解析
    public Pos mp0 = new Pos(0, 0);
    public Float mc = 0f;//周长
    public Float mang = 0f;//线与X轴夹角(角度数)
    public Float mk;//斜率
    public Float mb0 = 0f;//直线解析式x=0时，b的值
    public Float mx = 0f;//直线解析式y时，x的值
    public Float my = 0f;//直线解析式x时，y的值


    public ShapeLine p0(Pos p0) {
        this.mp0 = p0;
        return this;
    }

    public ShapeLine p0(Float x, Float y) {
        this.mp0 = new Pos(x, y);
        return this;
    }

    public ShapeLine c(Float c) {
        this.mc = c;
        return this;
    }

    public ShapeLine ang(Float ang) {
        this.mang = ang;
        return this;
    }

    public ShapeLine k(Float k) {
        this.mk = k;
        return this;
    }

    public ShapeLine b0(Float b0) {
        this.mb0 = b0;
        return this;
    }

    public ShapeLine x(Float x) {
        this.mx = x;
        return this;
    }

    public ShapeLine y(Float y) {
        this.my = y;
        return this;
    }

    public ShapeLine ps(Pos... ps) {
        this.mps = ps;
        return this;
    }


}
