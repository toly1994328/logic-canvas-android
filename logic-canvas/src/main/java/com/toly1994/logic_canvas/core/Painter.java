package com.toly1994.logic_canvas.core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.shape.Shape;
import com.toly1994.logic_canvas.core.shape.ShapeLine;
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


    public void attach(Canvas canvas) {
        mCanvas = canvas;
    }

    /**
     * 绘制入口
     */
    public Painter draw(final Shape shape) {

        mCanvas.save();
        float x = shape.mp.x;
        float y = shape.mp.y;

        Pos coo = shape.mcoo;
        if (Logic.isExist(coo)) {
            x = coo.x + x;
            y = coo.y - shape.mp.y;
//            shape.mp.x = x;
//            shape.mp.y = y;
        }

        mCanvas.translate(x, y);
        mCanvas.translate(shape.ma.x, shape.ma.y);
        mCanvas.rotate(shape.mrot);
        mCanvas.scale(shape.msx, shape.msy);
        mCanvas.translate(-shape.ma.x, -shape.ma.y);

        this.setOnPrepared(new OnPrepared() {
            @Override
            public void draw(Paint paint) {
                Path path = shape.formPath();
                if (path != null) {
                    if (shape.de != null) {
                        paint.setPathEffect(shape.de);
                    }
                    mCanvas.drawPath(path, paint);
                }
            }
        });

        if (mOnPrepared != null) {
            mOnPrepared.draw(initPaint(shape));
        }

        mCanvas.restore();

        return this;
    }

    /**
     * 绘制多个shape
     *
     * @param shapes
     */
    public void draw(Shape... shapes) {
        for (Shape shape : shapes) {
            draw(shape);
        }
    }

    /**
     * 根据shape 初始画笔
     *
     * @param shape Shape
     */
    private Paint initPaint(Shape shape) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿

        //很巧妙的解决了一个小问题。toly----2018年9月6日08:37:37
        boolean isStroke = shape.mss != 0xe792A09D;//说明设置了线条颜色
        boolean isFill = shape.mfs != 0xf69ABCF5;//说明设置了填充颜色

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

        paint.setStrokeWidth(shape.mb);

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

//    TODO 这个方法挺好玩的 先注释着  toly----2018年9月5日16:11:54
//    public void cap(Shape shape) {
//        shape.formPath();
//        ShapeLine shapeL = (ShapeLine) (shape);
//
//
//        float deta = shapeL.mang > 90 ? 20f : -20;
//
//        Shape cap = new ShapeLine()
//                .c(150f).ang(-90f)
//                .p(shapeL.mv.refY()).coo(shapeL.mcoo).b(8f);
//        draw(cap);
//    }

    /**
     * 给向量加个箭头...纯属好看
     *
     * @param shapeL 直线
     */
    public void cap(ShapeLine shapeL) {

        Pos posOfCap = shapeL.mv.add(shapeL.mp);//箭头向量的起点
        System.out.println(shapeL.mang);
        ShapeLine cap = (ShapeLine) new ShapeLine()//一侧箭头
                .c(30f).ang(-shapeL.mang + 150)
                .p(posOfCap).coo(shapeL.mcoo).b(3f).ss(Color.BLUE);
        ShapeLine cap2 = cap.deepClone().ang(-shapeL.mang-150f);//另一侧箭头
        draw(cap,cap2);//绘制箭头
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
