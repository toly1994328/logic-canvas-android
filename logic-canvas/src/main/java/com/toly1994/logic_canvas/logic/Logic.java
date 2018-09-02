package com.toly1994.logic_canvas.logic;


import com.toly1994.logic_canvas.bean.Pos;

/**
 * 作者：张风捷特烈
 * 时间：2018/7/5:8:59
 * 邮箱：1981462002@qq.com
 * 说明： 逻辑类
 */
public class Logic {

    /**
     * 弧度制化为角度制
     *
     * @param rad 弧度
     * @return 角度
     */
    public static float deg(float rad) {
        return (float) (rad * 180 / Math.PI);
    }

    /**
     * 角度制化为弧度制
     *
     * @param deg 角度
     * @return 弧度
     */
    public static float rad(float deg) {
        return (float) (deg * Math.PI / 180);
    }

    /**
     * 两点间夹角，以p0为旋转点 ，逆时针度数
     *
     * @param p0 第一点
     * @param p1 第二点
     * @return 两点间夹角
     */
    public static float angleOf2Pos(Pos p0, Pos p1) {
        return (float) Math.atan((p1.x - p0.x) / (p1.y - p0.y));
    }


    /**
     * 两点间距离函数
     *
     * @param p0 第一点
     * @param p1 第二点
     * @return 两点间距离
     */
    public static float disPos2d(Pos p0, Pos p1) {
        return (float) Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
    }

    /**
     * 获得两点连线的中点
     *
     * @param p1 第一点
     * @param p2 第二点
     * @return 中点
     */
    public static Pos getMiddlePos(Pos p1, Pos p2) {
        return new Pos((p1.x + p2.x) / 2.0f, (p1.y + p2.y) / 2.0f);
    }


    /**
     * 根据百分比获取两点之间的某个点坐标
     *
     * @param p1      第一点
     * @param p2      第二点
     * @param percent 分度值
     * @return 根据百分比获取两点之间的某个点坐标
     */
    public static Pos getPointByPercent(Pos p1, Pos p2, float percent) {
        return new Pos(evaluateValue(percent, p1.x, p2.x), evaluateValue(percent, p1.y, p2.y));
    }

    /**
     * 根据分度值，计算从start到end中，fraction位置的值。fraction范围为0 -> 1
     *
     * @param fraction 分率
     * @param start    起始点
     * @param end      终止点
     * @return 根据分度值，计算从start到end中，fraction位置的值
     */
    public static float evaluateValue(float fraction, Number start, Number end) {
        return start.floatValue() + (end.floatValue() - start.floatValue()) * fraction;
    }


    /**
     * 获取 通过指定圆心，斜率为lineK的直线与圆的交点。
     *
     * @param center 圆心
     * @param radius 半径
     * @param k      直线K值
     * @return 交点
     */
    public static Pos[] getCrossOfCircle$Line(Pos center, float radius, Double k) {
        Pos[] points = new Pos[2];

        float radian, xOffset = 0, yOffset = 0;
        if (k != null) {
            radian = (float) Math.atan(k);//得到该角的角度
            xOffset = (float) (Math.sin(radian) * radius);//得到对边的长
            yOffset = (float) (Math.cos(radian) * radius);//得到邻边的长
        } else {
            xOffset = radius;
            yOffset = 0;
        }
        points[0] = new Pos(center.x + xOffset, center.y - yOffset);
        points[1] = new Pos(center.x - xOffset, center.y + yOffset);

        return points;
    }

    /**
     * 一元二次函数解
     *
     * @param a 二次项系数
     * @param b 一次项系数
     * @param c 常数项参数
     * @return 解集
     */
    public static Pos getOyTc(float a, float b, float c) {
        float delta = b * b - 4 * a * c;
        if (delta >= 0) {
            float x1 = (float) (-b / (2 * a) + Math.sqrt(delta) / (2 * a));
            float x2 = (float) (-b / (2 * a) - Math.sqrt(delta) / (2 * a));
            return new Pos(x1, x2);
        } else {
            return null;
        }
    }

    /**
     * 判断参数是否存在
     */
    public static boolean isExist(Object... objs) {


        for (Object o : objs) {
            if (o == null) {
                return false;
            }
        }
        return true;
    }

}
