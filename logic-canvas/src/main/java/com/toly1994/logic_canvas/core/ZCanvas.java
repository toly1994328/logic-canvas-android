package com.toly1994.logic_canvas.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.toly1994.logic_canvas.bean.Painter;
import com.toly1994.logic_canvas.bean.Pos;
import com.toly1994.logic_canvas.logic.Logic;


/**
 * 作者：张风捷特烈
 * 时间：2018/7/6:10:43
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class ZCanvas {
    private static final String TAG = "ZCanvas";
    private Canvas mCanvas;

    public ZCanvas(Canvas canvas) {
        mCanvas = canvas;
    }

    /**
     * 绘制入口
     *
     * @param info
     */
    public void s2r(Painter info, final Path path) {
        mCanvas.save();

        float x = info.mp.x;
        float y = info.mp.y;

        Pos coo = info.mcoo;
        if (Logic.isExist(coo)) {
            x = coo.x + x;
            y = coo.y - info.mp.y;
            info.mp.x = x;
            info.mp.y = y;

//            for (int i = 0; i < info.mps.length; i++) {
//                float y1 = -info.mps[i].y;
//                info.mps[i].y = y1;
//            }

        }
        mCanvas.translate((float) (x + 0), (float) (y + 0));

        mCanvas.translate((info.ma.x).floatValue(), (info.ma.y).floatValue());
        mCanvas.rotate(info.mrot.floatValue());
        mCanvas.scale(info.msx,info.msy);
        mCanvas.translate(-(info.ma.x).floatValue(), -(info.ma.y).floatValue());


        this.setOnPrepared(new OnPrepared() {
            @Override
            public void draw(Paint paint) {
                if (path != null) {
                    mCanvas.drawPath(path, paint);
                }
            }
        });

        if (mOnPrepared != null) {

            Paint paint = new Paint();
            paint.setAntiAlias(true);//抗锯齿

            if (Logic.isExist(info.mss)) {
                paint.setColor(info.mss);
                paint.setStyle(Paint.Style.STROKE);
            }

            if (Logic.isExist(info.mfs)) {
                paint.setColor(info.mfs);
                paint.setStyle(Paint.Style.FILL);
            }

            paint.setStrokeWidth((info.mb).intValue());
            mOnPrepared.draw(paint);

        }

        mCanvas.restore();
    }


    /**
     * 将图形图形打包在一起移动
     *
     * @param px
     * @param py
     * @param painters
     */
    public void groupMove(Float px, Float py, Painter... painters) {
        for (Painter info : painters) {
            info.mp = new Pos(px, py);
        }
    }

    /**
     * 将图形图形打包在一起旋转
     *
     * @param ax
     * @param ay
     * @param rot
     * @param painters
     */
    public void groupRot(Float ax, Float ay, Float rot, Painter... painters) {
        for (Painter info : painters) {
            info.ma = new Pos(ax, ay);
            info.mrot = rot;
        }
    }


    /**
     * 绘制线: //TODO 解析线方程
     *
     * @param info
     */
    private void drawLine(Painter info) {
        s2r(info, ShapePath.linePath(info));
    }

    /**
     * 绘制线多点直线 //add-toly:2018-7-7 09:49:50
     *
     * @param info
     */
    public void drawLines(Painter info) {
        s2r(info, ShapePath.linesPath(info));

    }

    /**
     * 绘制圆
     *
     * @param info
     */
    public void drawCircle(Painter info) {

        s2r(info, ShapePath.circlePath(info));

    }

    /**
     * 绘制弧
     *
     * @param info
     */
    public void drawArc(Painter info) {

        s2r(info, ShapePath.arcPath(info));

    }

    /**
     * 绘制正n边形
     *
     * @param info
     */
    public void drawTrg(Painter info) {

        s2r(info, ShapePath.trgPath(info));
    }

    /**
     * 绘制矩形：x,y,r
     *
     * @param info //TODO 矩形填充异常---hehehe
     */
    public void drawRect(Painter info) {
//        float width = info.mx.floatValue();
//        float height = info.my.floatValue();
//        float r = info.mr.floatValue();
//
//        boolean exist = Logic.isExist(info.mfs);
//
//        if (exist) {
//            info.mps = new Pos[]{new Pos(0, r), new Pos(width - r, 0),
//                    new Pos(width, r - height), new Pos(r, -height)};
//            drawLines(info);
////            s2r(info, ShapePath.rectPath(info));
//        } else {
            s2r(info, ShapePath.rectPath(info));
//        }

    }


    /**
     * 绘制n角星
     *
     * @param info
     */
    public void drawNStar(Painter info) {

        s2r(info, ShapePath.starPath(info));
    }

    /**
     * 绘制正n角星
     *
     * @param info
     */
    public void drawRegularStar(Painter info) {

        s2r(info, ShapePath.regularStarPath(info));
    }

    /**
     * 绘制正n边形
     *
     * @param info
     */
    public void drawRegularPolygon(Painter info) {

        s2r(info, ShapePath.regularPolygonPath(info));
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
