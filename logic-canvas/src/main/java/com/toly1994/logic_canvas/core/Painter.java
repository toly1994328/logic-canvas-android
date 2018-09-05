package com.toly1994.logic_canvas.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.toly1994.logic_canvas.bean.Pos;
import com.toly1994.logic_canvas.core.shape.Shape;
import com.toly1994.logic_canvas.core.shape.ShapeText;
import com.toly1994.logic_canvas.logic.Logic;


/**
 * 作者：张风捷特烈
 * 时间：2018/7/6:10:43
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class Painter {
    private static final String TAG = "Painter";
    private Canvas mCanvas;


    public Painter(Canvas canvas) {
        mCanvas = canvas;
    }

    /**
     * 绘制入口
     */
    public void draw(final Shape shape) {

        mCanvas.save();
        float x = shape.mp.x;
        float y = shape.mp.y;

        Pos coo = shape.mcoo;
        if (Logic.isExist(coo)) {
            x = coo.x + x;
            y = coo.y - shape.mp.y;
            shape.mp.x = x;
            shape.mp.y = y;
        }
        mCanvas.translate(x + shape.mb, y + shape.mb);
        mCanvas.translate((shape.ma.x).floatValue(), (shape.ma.y).floatValue());
        mCanvas.rotate(shape.mrot.floatValue());
        mCanvas.scale(shape.msx, shape.msy);
        mCanvas.translate(-(shape.ma.x).floatValue(), -(shape.ma.y).floatValue());


        this.setOnPrepared(new OnPrepared() {
            @Override
            public void draw(Paint paint) {
                Path path = shape.formPath();
                if (path != null) {
                    mCanvas.drawPath(path, paint);
                }
            }
        });

        if (mOnPrepared != null) {
            mOnPrepared.draw(initPaint(shape));
        }

        mCanvas.restore();
    }

    /**
     * 根据shape 初始画笔
     *
     * @param shape Shape
     */
    private Paint initPaint(Shape shape) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿

        boolean isStroke = Logic.isExist(shape.mss);
        boolean isFill = Logic.isExist(shape.mfs);

        if (isStroke && !isFill) {
            paint.setColor(shape.mss);
            paint.setStyle(Paint.Style.STROKE);
        }

        if (isFill) {
            paint.setColor(shape.mfs);
            paint.setStyle(Paint.Style.FILL);
        }

        if (!isFill && !isStroke) {
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
        }

        paint.setStrokeWidth((shape.mb).intValue());

        return paint;
    }


    /**
     * 将图形图形打包在一起移动
     *
     * @param px
     * @param py
     * @param shapes
     */
    public void groupMove(Float px, Float py, Shape... shapes) {
        for (Shape info : shapes) {
            info.mp = new Pos(px, py);
        }
    }

    /**
     * 将图形图形打包在一起旋转
     *
     * @param ax
     * @param ay
     * @param rot
     * @param shapes
     */
    public void groupRot(Float ax, Float ay, Float rot, Shape... shapes) {
        for (Shape info : shapes) {
            info.ma = new Pos(ax, ay);
            info.mrot = rot;
        }
    }

    public void drawText(Shape shape) {
        ShapeText tShape = (ShapeText) shape;

        Paint paint = initPaint(shape);
        paint.setTextSize((tShape.mSize));

        switch (tShape.mAl) {
            case ">":
                paint.setTextAlign(Paint.Align.RIGHT);
                break;
            case "<":
                paint.setTextAlign(Paint.Align.LEFT);
                break;
            default:
                paint.setTextAlign(Paint.Align.CENTER);
        }

        mCanvas.drawText(tShape.mStr, shape.mp.x, shape.mp.y, paint);
    }

    //////////////////////////////////画板准备完成监听
    interface OnPrepared {
        void draw(Paint paint);
    }

    private OnPrepared mOnPrepared;

    private void setOnPrepared(OnPrepared onPrepared) {
        mOnPrepared = onPrepared;
    }
}
