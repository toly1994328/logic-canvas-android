package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;

import com.toly1994.logic_canvas.bean.Pos;


/**
 * 作者：张风捷特烈
 * 时间：2018/7/6:10:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public abstract class Shape {
    /**
     * 依靠数据形成路径
     *
     * @return 路径
     */
    public abstract Path formPath();

    public Float mb = 0f;//线宽
    public Pos mp = new Pos(0, 0);//图形距画布左顶点偏移量
    public Pos ma = new Pos(0, 0);//锚点
    public Pos mcoo = new Pos(0, 0);//锚点
    public Float mrot = 0f;//旋转量
    public Float msx = 1f;//X缩放量
    public Float msy = 1f;//Y缩放量
    public Integer mfs;//填充颜色
    public Integer mss;//线条颜色

    public boolean mdir = true;


    public Shape b(Float b) {
        this.mb = b;
        return this;
    }

    public Shape p(Pos p) {
        this.mp = p;
        return this;
    }

    public Shape p(Float x, Float y) {
        this.mp = new Pos(x, y);
        return this;
    }

    public Shape a(Pos a) {
        this.ma = a;
        return this;
    }

    public Shape a(Float x, Float y) {
        this.ma = new Pos(x, y);
        return this;
    }

    public Shape coo(Pos mcoo) {
        this.mcoo = mcoo;
        return this;
    }

    public Shape coo(Float x, Float y) {
        this.mcoo = new Pos(x, y);
        return this;
    }

    public Shape rot(Float rot) {
        this.mrot = rot;
        return this;
    }

    public Shape sx(Float sx) {
        this.msx = sx;
        return this;
    }

    public Shape sy(Float sy) {
        this.msy = sy;
        return this;
    }

    public Shape fs(Integer fs) {
        this.mfs = fs;
        return this;
    }

    public Shape ss(Integer ss) {
        this.mss = ss;
        return this;
    }


    public Shape dir(boolean dir) {
        this.mdir = dir;
        return this;
    }


    @Override
    public String toString() {
        return "Shape{" +
                ", mb=" + mb +
                ", mp=" + mp +
                ", ma=" + ma +
                ", mcoo=" + mcoo +
                ", mrot=" + mrot +
                ", mfs=" + mfs +
                ", mss=" + mss +
                ", mdir=" + mdir +
                '}';
    }

}
