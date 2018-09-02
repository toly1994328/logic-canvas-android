package com.toly1994.logic_canvas.bean;

import android.content.Context;
import android.graphics.Color;

import java.util.Arrays;



/**
 * 作者：张风捷特烈
 * 时间：2018/7/6:10:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class Painter {
    public Pos mp0 = new Pos(0, 0);
    public Pos mp1 = new Pos(0, 0);
    public Pos mp2 = new Pos(0, 0);

    public Pos[] mps;

    public Float mc = 0f;//周长
    public Float mang = 0f;//线与X轴夹角(弧度数)
    public Float mk = 0f;//斜率
    public Float mb0 = 0f;//直线解析式x=0时，y的值
    public Float mx = 0f;//
    public Float my = 0f;//

    public Float mb = 0f;//线宽
    public Pos mp = new Pos(0, 0);//图形距画布左顶点偏移量
    public Pos ma = new Pos(0, 0);//锚点
    public Pos mcoo= new Pos(0, 0);//锚点
    public Float mrot = 0f;//旋转量
    public Float msx = 1f;//X缩放量
    public Float msy = 1f;//Y缩放量
    public Integer mfs;//填充颜色
    public Integer mss = Color.BLUE;//线条颜色

    public boolean mdir = true;
    /**
     * 五角星f
     **/
    public Integer mnum = 5;//角数
    public Float mR = 100f;//外接圆半径
    public Float mr = 0f;//内接圆半径



    public Painter dp(Context ctx) {
        Painter painter = new Painter();
        painter.p0(dp2px(ctx, mp0.x), dp2px(ctx, mp0.y));
        painter.p1(dp2px(ctx, mp1.x), dp2px(ctx, mp1.y));
        painter.p2(dp2px(ctx, mp2.x), dp2px(ctx, mp2.y));

        Pos[] temp = mps;
        if (mps!=null) {
            for (int i = 0; i < mps.length; i++) {
                temp[i].x = dp2px(ctx, mps[i].x);
                temp[i].y = dp2px(ctx, mps[i].y);
            }
        }
        painter.ps(temp);

        painter.c(dp2px(ctx, mc));
        painter.ang(dp2px(ctx, mang));
        painter.k(dp2px(ctx, mk));
        painter.b0(dp2px(ctx, mb0));
        painter.x(dp2px(ctx, mx));
        painter.y(dp2px(ctx, my));
        painter.b(dp2px(ctx, mb));

        painter.p(dp2px(ctx, mp.x), dp2px(ctx, mp.y));
        painter.a(dp2px(ctx, ma.x), dp2px(ctx, ma.y));

        painter.coo(dp2px(ctx, mcoo.x), dp2px(ctx, mcoo.y));

        painter.rot(dp2px(ctx, mrot));
        painter.sx(dp2px(ctx, msx));
        painter.sy(dp2px(ctx, msy));

        painter.fs(mfs);
        painter.ss(mss);
        painter.dir(mdir);
        painter.num(mnum);


        painter.R(dp2px(ctx, mR));
        painter.r(dp2px(ctx, mr));

        return painter;
    }

    public Painter p0(Pos p0) {
        this.mp0 = p0;
        return this;
    }

    public Painter p0(Float x, Float y) {
        this.mp0 = new Pos(x, y);
        return this;
    }

    public Painter p1(Pos p1) {
        this.mp1 = p1;
        return this;
    }

    public Painter p1(Float x, Float y) {
        this.mp1 = new Pos(x, y);
        return this;
    }

    public Painter p2(Pos p2) {
        this.mp2 = p2;
        return this;
    }

    public Painter p2(Float x, Float y) {
        this.mp2 = new Pos(x, y);
        return this;
    }

    public Painter c(Float c) {
        this.mc = c;
        return this;
    }

    public Painter ang(Float ang) {
        this.mang = ang;
        return this;
    }

    public Painter k(Float k) {
        this.mk = k;
        return this;
    }

    public Painter b0(Float b0) {
        this.mb0 = b0;
        return this;
    }

    public Painter x(Float x) {
        this.mx = x;
        return this;
    }

    public Painter y(Float y) {
        this.my = y;
        return this;
    }

    public Painter b(Float b) {
        this.mb = b;
        return this;
    }

    public Painter p(Pos p) {
        this.mp = p;
        return this;
    }

    public Painter p(Float x, Float y) {
        this.mp = new Pos(x, y);
        return this;
    }

    public Painter a(Pos a) {
        this.ma = a;
        return this;
    }

    public Painter a(Float x, Float y) {
        this.ma = new Pos(x, y);
        return this;
    }
    public Painter coo(Pos mcoo) {
        this.mcoo = mcoo;
        return this;
    }
    public Painter coo(Float x, Float y) {
        this.mcoo = new Pos(x, y);
        return this;
    }

    public Painter rot(Float rot) {
        this.mrot = rot;
        return this;
    }
    public Painter sx(Float sx) {
        this.msx = sx;
        return this;
    }

    public Painter sy(Float sy) {
        this.msy = sy;
        return this;
    }

    public Painter fs(Integer fs) {
        this.mfs = fs;
        return this;
    }

    public Painter ss(Integer ss) {
        this.mss = ss;
        return this;
    }

    public Painter dir(boolean dir) {
        this.mdir = dir;
        return this;
    }

    public Painter num(Integer num) {
        this.mnum = num;
        return this;
    }

    public Painter R(Float r) {
        mR = r;
        return this;
    }

    public Painter r(Float r) {
        mr = r;
        return this;
    }

    public Painter ps(Pos... ps) {
        this.mps = ps;
        return this;
    }

    @Override
    public String toString() {
        return "Painter{" +
                "mp0=" + mp0 +
                ", mp1=" + mp1 +
                ", mp2=" + mp2 +
                ", mps=" + Arrays.toString(mps) +
                ", mc=" + mc +
                ", mang=" + mang +
                ", mk=" + mk +
                ", mb0=" + mb0 +
                ", mx=" + mx +
                ", my=" + my +
                ", mb=" + mb +
                ", mp=" + mp +
                ", ma=" + ma +
                ", mcoo=" + mcoo +
                ", mrot=" + mrot +
                ", mfs=" + mfs +
                ", mss=" + mss +
                ", mdir=" + mdir +
                ", mnum=" + mnum +
                ", mR=" + mR +
                ", mr=" + mr +
                '}';
    }

    public Float dp2px(Context ctx, Float dp) {
        if (dp!=null) {
            final Float scale = ctx.getResources().getDisplayMetrics().density;
            return dp * scale + 0.5f;
        }
        return dp;
    }
}
