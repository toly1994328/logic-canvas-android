package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;

import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.logic.Logic;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/4 0004:9:31<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：线
 */
public class ShapeLine extends Shape implements Serializable, Cloneable {


    /**
     * 依靠点集绘制
     */
    public Pos[] mps;


    @Override
    public Path formPath() {
        Path path = new Path();

        //绘制向量
        if (Logic.isExist(mc, mang) || Logic.isExist(mv)) {
            parse();
            path.moveTo(0, 0);
            path.lineTo(mv.x, -mv.y);

        } else {
            Pos[] poss = this.mps;
            path.moveTo(poss[0].x, -poss[0].y);
            for (Pos pos : poss) {
                path.lineTo(pos.x, -pos.y);
            }
        }
        return path;
    }

//    /**
//     * 序列化深拷贝：慎用
//     *
//     * @return
//     */
//    public ShapeLine formAll() {//将对象写到流里
//        ObjectInputStream oi = null;
//        try {
//            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            ObjectOutputStream oo = new ObjectOutputStream(bo);
//            oo.writeObject(this);//从流里读出来
//            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
//            oi = new ObjectInputStream(bi);
//            return (ShapeLine) oi.readObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    /**
     * 浅克隆
     *
     * @return 浅克隆对象
     */
    public ShapeLine clone() {
        ShapeLine clone = null;
        try {
            clone = (ShapeLine) super.clone();
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
    public ShapeLine deepClone() {
        ShapeLine clone = null;
        try {
            clone = (ShapeLine) super.clone();
            if (mv != null) {
                clone.mv = mv.clone();
            }
            clone.mp = mp.clone();
            clone.ma = ma.clone();
            clone.mcoo = mcoo.clone();
            if (mps != null) {
                for (int i = 0; i < mps.length; i++) {
                    clone.mps[i] = mps[i].clone();
                }
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * 直线向量解析
     */
    public ShapeLine parse() {
        if (!Logic.isExist(mv)) {
            double x = Math.cos(Logic.rad(mang));
            double y = Math.sin(Logic.rad(mang));
            mv = mp.clone((float) x, (float) y).dotC(mc);
        } else {
            mang = mv.deg();
            mc = mv.length();
        }

        return this;
    }


    // TODO 直线解析
    /**
     * 向量
     */
    public Pos mv;
    /**
     * 向量长度
     */
    public Float mc;
    /**
     * 向量夹角(角度数)
     */
    public Float mang;


    public Float mk;//斜率
    public Float mb0 = 0f;//直线解析式x=0时，b的值
    public Float mx = 0f;//直线解析式y时，x的值
    public Float my = 0f;//直线解析式x时，y的值


    public ShapeLine v(Pos v) {
        this.mv = v;
        return this;
    }

    public ShapeLine v(float x, float y) {
        this.mv = mp.clone(x, y);
        return this;
    }

    public Pos add(ShapeLine shapeLine) {
        return mv.clone(mv).add(shapeLine.mv);

    }

    public ShapeLine c(Float c) {
        this.mc = c;
        return this;
    }

    public ShapeLine ang(Float ang) {
        this.mang = -ang;
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

    @Override
    public String toString() {
        return "ShapeLine{" +
                "mps=" + Arrays.toString(mps) +
                ", mv=" + mv +
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
                ", msx=" + msx +
                ", msy=" + msy +
                ", mfs=" + mfs +
                ", mss=" + mss +
                ", de=" + de +
                ", mdir=" + mdir +
                '}';
    }
}
