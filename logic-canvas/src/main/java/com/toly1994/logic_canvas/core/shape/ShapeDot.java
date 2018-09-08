package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;
import android.graphics.RectF;

import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.logic.Logic;

import java.io.Serializable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/4 0004:9:31<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：线
 */
public class ShapeDot extends Shape implements Serializable, Cloneable {


    @Override
    public Path formPath() {



        if (mang != null && mc != null) {
            float x = (float) (Math.cos(Logic.rad(mang)) * mc);
            float y = (float) (Math.sin(Logic.rad(mang)) * mc);
            mv = mp.clone(x, y);
        }

        Path path = new Path();
        Float r = this.mb / 2;
        RectF rectF = null;

        if (mv != null) {
             rectF = new RectF(-r+mv.x , -r +mv.y, r+mv.x , r+mv.y );
            path.addArc(rectF, 0, 360);
            return path;
        } else {
             rectF = new RectF(-r, -r, r, r);
            path.addArc(rectF, 0, 360);
        }


        return path;
    }


    /**
     * 浅克隆
     *
     * @return 浅克隆对象
     */
    public ShapeDot clone() {
        ShapeDot clone = null;
        try {
            clone = (ShapeDot) super.clone();
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
    public ShapeDot deepClone() {
        ShapeDot clone = null;
        try {
            clone = (ShapeDot) super.clone();
            if (mv != null) {
                clone.mv = mv.clone();
            }

            clone.mp = mp.clone();
            clone.ma = ma.clone();
            clone.mcoo = mcoo.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * 直线向量解析
     */
    public ShapeDot parse() {
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


    public ShapeDot v(Pos v) {
        this.mv = v;
        return this;
    }

    public ShapeDot v(float x, float y) {
        this.mv = mp.clone(x, y);
        return this;
    }

    public Pos add(ShapeDot shapeLine) {
        return mv.clone(mv).add(shapeLine.mv);

    }

    public ShapeDot c(Float c) {
        this.mc = c;
        return this;
    }

    public ShapeDot ang(Float ang) {
        this.mang = -ang;
        return this;
    }


    @Override
    public String toString() {
        return "ShapeLine{" +
                ", mv=" + mv +
                ", mc=" + mc +
                ", mang=" + mang +
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
