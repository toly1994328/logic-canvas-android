package com.toly1994.logic_canvas.core.shape;

import android.graphics.DashPathEffect;
import android.graphics.Path;

import com.toly1994.logic_canvas.base.Pos;


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

    public float mb = 0f;//线宽

    public Pos mp = new Pos(0, 0);//图形距画布左顶点偏移量
    public Pos ma = mp.clone(0, 0);//锚点
    public Pos mcoo = mp.clone(0, 0);//坐标点

    public float mrot = 0f;//旋转量
    public float msx = 1f;//X缩放量
    public float msy = 1f;//Y缩放量
    public int mss = 0xe7FEF382;//线条颜色
    public int mfs = 0xf69ABCF5;//填充颜色

    public DashPathEffect de;
    public boolean mdir = true;


    public Shape shape(Shape shape) {
        b(shape.mb);
        a(shape.ma);
        coo(shape.mcoo);
        ss(shape.mss);
        fs(shape.mfs);
        return this;
    }

    public Shape b(float b) {
        this.mb = b;
        return this;
    }

    public Shape de(DashPathEffect de) {
        this.de = de;
        return this;
    }

    public Shape p(Pos p) {
        this.mp = p;
        return this;
    }

    public Shape p(float x, float y) {
        this.mp = mp.clone(x, y);
        return this;
    }

    public Shape a(Pos a) {
        this.ma = a;
        return this;
    }

    public Shape a(float x, float y) {
        this.ma = mp.clone(x, y);
        return this;
    }

    public Shape coo(Pos mcoo) {
        this.mcoo = mcoo;
        return this;
    }

    public Shape coo(float x, float y) {
        this.mcoo = mp.clone(x, y);
        return this;
    }

    public Shape rot(float rot) {
        this.mrot = rot;
        return this;
    }

    public Shape sx(float sx) {
        this.msx = sx;
        return this;
    }

    public Shape sy(float sy) {
        this.msy = sy;
        return this;
    }

    public Shape fs(int fs) {
        this.mfs = fs;
        return this;
    }

    public Shape ss(int ss) {
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
