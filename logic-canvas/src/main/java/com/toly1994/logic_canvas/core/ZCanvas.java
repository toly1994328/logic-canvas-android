package com.toly1994.logic_canvas.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

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
            Log.d(TAG, "s2r: " + info);
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
     * 绘制线
     *
     * @param info
     */
    public void drawLine(Painter info) {
        s2r(info, ShapePath.linePath(info));
    }

    /**
     * 绘制线多点 //add-toly:2018-7-7 09:49:50
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
     * @param info
     */
    public void drawRect(Painter info) {
        float width = info.mx.floatValue();
        float height = info.my.floatValue();
        float r = info.mr.floatValue();
        boolean exist = Logic.isExist(info.mfs);
        info.mps = new Pos[]{new Pos(0, r), new Pos(width - r, 0),
                new Pos(width, height - r), new Pos(r, height)};
        if (exist) {
            drawLines(info);

        }
        s2r(info, ShapePath.rectPath(info));

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

    /**
     * 获取画笔：DrawUtils.getPaint(Color.GREEN, Paint.Style.STROKE, 10);
     *
     * @param color 画笔颜色
     * @param style 画笔样式
     * @param width 画笔宽
     * @return
     */
    public static Paint getPaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }

    public static Paint getPaint(int color) {
        return getPaint(color, Paint.Style.FILL_AND_STROKE, 2);
    }

    public static Paint getPaint() {
        return getPaint(Color.BLACK, Paint.Style.FILL_AND_STROKE, 2);
    }

    /**
     * 绘制网格
     *
     * @param
     * @param step
     */
    public void drawGrid(int step, Context ctx) {
        if (step == 0) {
            return;
        }
        for (int i = 0; i < getScreenHeight(ctx) / step; i++) {
            mCanvas.drawLine(0, 0 + step * i, getScreenWidth(ctx), 0 + step * i, getPaint());
        }
        for (int i = 0; i < getScreenWidth(ctx) / step; i++) {
            mCanvas.drawLine(0 + step * i, 0, 0 + step * i, getScreenHeight(ctx), getPaint());
        }
    }

    /**
     * 绘制直角坐标系
     *
     * @param coo    坐标原点
     * @param line_h 小线高
     * @param step   小线间隔（像素）
     */
    public void drawCoord(Pos coo, float line_h, float step, Context ctx) {
        Pos COO = coo;//坐标原点
        float LINE_H = line_h;//小线高
        float STEP = step;//小线间隔（像素）
        drawLines(new Painter().ps(new Pos(-COO.x, 0)
                , new Pos(getScreenWidth(ctx) - coo.x, 0)).ss(Color.BLACK).coo(COO));

//
        for (int i = 1; i < getScreenWidth(ctx) / STEP; i++) {
            drawLines(
                    new Painter().ps(new Pos(-COO.x + STEP * i, 0)
                            , new Pos(-COO.x + STEP * i, LINE_H)).ss(Color.BLACK).coo(COO));
        }

//        drawLines(new Painter().ps(
//                new Pos(0, -COO.y)
//                , new Pos(0, -COO.y + getScreenHeight(ctx)))
//                .coo(COO).ss(Color.BLACK).dp(ctx));

        for (int i = 1; i < getScreenHeight(ctx) / STEP; i++) {
            drawLines(
                    new Painter().
                            ps(new Pos(0, (COO.y - getScreenHeight(ctx)+ STEP * i))
                            , new Pos(LINE_H, (COO.y - getScreenHeight(ctx) + STEP * i)))
                            .ss(Color.BLACK)
                            .coo(COO));
//

        }
    }


    /**
     * 获得屏幕高度
     *
     * @return 屏幕高度
     */
    public static int getScreenWidth(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenHeight(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
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
