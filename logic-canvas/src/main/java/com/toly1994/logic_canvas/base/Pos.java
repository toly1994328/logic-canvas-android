package com.toly1994.logic_canvas.base;

import java.io.Serializable;

/**
 * 作者：张风捷特烈
 * 时间：2018/7/5:9:07
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class Pos implements Cloneable, Serializable {

    /**
     * x维度
     */
    public float x;
    /**
     * y维度
     */
    public float y;

    /**
     * @param x x维度
     * @param y y维度
     */
    public Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Pos clone(float x, float y) {
        Pos clone = null;
        try {
            clone = (Pos) super.clone();
            clone.x = x;
            clone.y = y;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }


    public Pos clone() {
        Pos clone = null;
        try {
            clone = (Pos) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }


    public Pos clone(Pos p) {
        return clone(p.x, p.y);
    }

    /**
     * 该向量点到原点的距离
     *
     * @return 该向量点到原点的距离
     */
    public float length() {
        // 返回平方根
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * 向量加法
     *
     * @param target 目标向量
     * @return 当前向量与目标向量的和向量
     */
    public Pos add(Pos target) {
        return clone(this.x + target.x, this.y + target.y);
    }

    public Pos add(float x, float y) {
        return add(clone(x, y));
    }

    /**
     * 向量减法
     *
     * @param target 目标向量
     * @return 当前向量与目标向量的差向量
     */
    public Pos minus(Pos target) {
        return this.add(target.ref());
    }


    /**
     * 向量取反
     *
     * @return 向量取反
     */
    public Pos ref() {
        return clone(-this.x, -this.y);
    }

    /**
     * 仅y方向取反
     *
     * @return
     */
    public Pos refY() {
        return clone(this.x, -this.y);
    }

    /**
     * 仅x方向取反
     *
     * @return 仅x方向取反
     */
    public Pos refX() {
        return clone(-this.x, this.y);
    }

    /**
     * 释放
     */
    public Pos release() {
        this.x = 0f;
        this.y = 0f;
        return this;
    }


    /**
     * 向量弧度
     *
     * @return 向量弧度
     */
    public float rad() {
        return (float) Math.atan2(y, x);
    }


    /**
     * 向量角度
     *
     * @return 向量角度
     */
    public float deg() {
        return (float) (rad() / Math.PI * 180);
    }

    /**
     * 两向量间夹角:调用方指向目标方
     *
     * @return 向量角度
     */
    public float deg2(Pos target) {
        return deg() - target.deg();
    }


    //计算2个向量的夹角弧度
    //参考点积公式:v1 * v2 = cos<v1,v2> * |v1| *|v2|
    public static double degWith(Pos v1, Pos v2) {

        return (float) (radWith(v1, v2) / Math.PI * 180);
    }

    //计算2个向量的夹角弧度
    //参考点积公式:v1 * v2 = cos<v1,v2> * |v1| *|v2|
    public static double radWith(Pos v1, Pos v2) {
        if (!v1.isNormalized()) v1 = v1.clone().normalize(); // |v1| = 1
        if (!v2.isNormalized()) v2 = v2.clone().normalize(); // |v2| = 1
        return Math.acos(v1.dotProduct(v2));
    }

    //2个向量的数量积(点积)
    public double dotProduct(Pos v) {
        return x * v.x + y * v.y;
    }


    //是否已经标准化
    public boolean isNormalized() {
        return length() == 1.0;
    }


    /**
     * 两向量间夹角:调用方指向目标方
     *
     * @return 向量弧度
     */
    public float rad2(Pos target) {
        return (float) (deg2(target) / Math.PI * 180);
    }

    /**
     * 是否是零向量
     *
     * @return
     */
    public boolean isZero() {
        return x == 0 && y == 0;
    }

    /**
     * 向量点乘常量
     *
     * @param num 常量
     * @return 向量模等于相应倍数的同向向量
     */
    public Pos dotC(float num) {
        return clone(this.x * num, -this.y * num);
    }

    /**
     * 向量的长度设置为我们期待的value
     *
     * @param len
     */
    public Pos resize(float len) {
        return normalize().dotC(len);
    }

    /**
     * 单位化向量
     *
     * @return 单位化向量
     */
    public Pos normalize() {

        return this.dotC(1 / this.length());
    }


    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


}
