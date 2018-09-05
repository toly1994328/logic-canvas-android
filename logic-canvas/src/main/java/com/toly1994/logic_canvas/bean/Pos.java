package com.toly1994.logic_canvas.bean;

/**
 * 作者：张风捷特烈
 * 时间：2018/7/5:9:07
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class Pos implements Cloneable {


    /**
     * x维度
     */
    public Float x;
    /**
     * y维度
     */
    public Float y;

    /**
     * @param x x维度
     * @param y y维度
     */
    public Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Pos init() {
        return new Pos(0, 0);
    }

    public Pos form(float x, float y) {
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
        this.x += target.x;
        this.y += target.y;
        return this;
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
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    /**
     * 仅y方向取反
     *
     * @return
     */
    public Pos refY() {
        this.y = -this.y;
        return this;
    }

    /**
     * 仅x方向取反
     *
     * @return 仅x方向取反
     */
    public Pos refX() {
        this.x = -this.x;
        return this;
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
     * 向量点乘常量
     *
     * @param num 常量
     * @return 向量模等于相应倍数的同向向量
     */
    public Pos dotC(float num) {
        this.x *= num;
        this.y *= num;
        return this;

    }

    /**
     * 单位化向量
     * @return 单位化向量
     */
    public Pos  normalize() {

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
