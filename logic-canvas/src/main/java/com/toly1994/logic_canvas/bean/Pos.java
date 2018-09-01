package com.toly1994.logic_canvas.bean;

/**
 * 作者：张风捷特烈
 * 时间：2018/7/5:9:07
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class Pos {
    /**
     * x维度
     */
    public Float x;
    /**
     * y维度
     */
    public Float y;

    /**
     *
     * @param x x维度
     * @param y y维度
     */
    public Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
