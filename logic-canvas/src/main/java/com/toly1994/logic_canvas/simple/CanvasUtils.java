package com.toly1994.logic_canvas.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.toly1994.logic_canvas.bean.Painter;
import com.toly1994.logic_canvas.bean.Pos;
import com.toly1994.logic_canvas.core.ZCanvas;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:7:46<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：常用绘制图形
 */
public class CanvasUtils {

    /**
     * 绘制网格
     *
     * @param ctx    上下文
     * @param step   间隔
     * @param canvas 画布
     */
    public static void drawGrid(Context ctx, int step, Canvas canvas) {
        ZCanvas zCanvas = new ZCanvas(canvas);
        if (step == 0) {
            return;
        }
        //横线
        for (int i = 0; i < getScreenHeight(ctx) / step; i++) {
            zCanvas.drawLine(
                    new Painter()
                            .ss(Color.BLACK)
                            .b(2f)
                            .p0(0f, 0f + step * i)
                            .p1(getScreenWidth(ctx)+0F, 0f + step * i));
        }
        //竖线
        for (int i = 0; i <= getScreenWidth(ctx) / step; i++) {
            zCanvas.drawLine(
                    new Painter()
                            .ss(Color.BLACK)
                            .b(2f)
                            .p0(0f + step * i, 0f)
                            .p1(0f + step * i, 0f +getScreenHeight(ctx)));
        }
    }

    /**
     * @param ctx    上下文
     * @param coo    坐标系原点
     * @param line_h 小线高
     * @param step   小线间隔（像素）
     * @param canvas 画布
     */
    public static void drawCoord(Context ctx, Pos coo, float line_h, float step, Canvas canvas) {
        ZCanvas zCanvas = new ZCanvas(canvas);
        Pos COO = coo;//坐标原点
        float LINE_H = line_h;//小线高
        float STEP = step;//小线间隔（像素）
        zCanvas.drawLines(
                new Painter()
                        .ps(new Pos(-COO.x, 0),
                                new Pos(getScreenWidth(ctx) - coo.x, 0))
                        .ss(Color.BLACK)
                        .coo(COO));

//
        for (int i = 1; i < getScreenWidth(ctx) / STEP; i++) {
            zCanvas.drawLines(
                    new Painter()
                            .ps(new Pos(-COO.x + STEP * i, 0),
                                    new Pos(-COO.x + STEP * i, LINE_H)).
                            ss(Color.BLACK)
                            .coo(COO));
        }


        for (int i = 1; i < getScreenHeight(ctx) / STEP; i++) {
            zCanvas.drawLines(
                    new Painter().
                            ps(new Pos(0, (COO.y - getScreenHeight(ctx) + STEP * i)),
                                    new Pos(LINE_H, (COO.y - getScreenHeight(ctx) + STEP * i)))
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
    private static int getScreenWidth(Context ctx) {
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
    private static int getScreenHeight(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
