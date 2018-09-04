package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/4 0004:9:31<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：矩形
 */
public class ShapeRect extends Shape {
    /**
     * 长
     */
    public Float mx = 0f;
    /**
     * 宽
     */
    public Float my = 0f;

    /**
     * 半径
     */
    public Float mr = 0f;//线与X轴夹角(角度数)


    @Override
    public Path formPath() {
        Path path = new Path();
        float width = this.mx - this.mb;
        float height = this.my - this.mb;
        float r = this.mr;

        if (2 * r > width || 2 * r > height) {
            return null;
        }

        path.addArc(new RectF(0, 0, 2 * r, 2 * r), 180, 90);
        path.lineTo(width - r, 0);
        path.addArc(new RectF(width - 2 * r, 0, width, 2 * r), -90, 90);
        path.lineTo(width, height - r);
        path.addArc(new RectF(width - 2 * r, height - 2 * r, width, height), 0, 90);
        path.lineTo(r, height);
        path.addArc(new RectF(0, height - 2 * r, 2 * r, height), 90, 90);
        path.lineTo(0, r);

        return path;
    }

    public ShapeRect x(Float mx) {
        this.mx = mx;
        return this;
    }

    public ShapeRect y(Float my) {
        this.my = my;
        return this;
    }

    public ShapeRect r(Float mr) {
        this.mr = mr;
        return this;
    }
}
