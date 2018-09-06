package com.toly1994.logic_canvas.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.toly1994.logic_canvas.base.Cons;
import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.PainterEnum;
import com.toly1994.logic_canvas.core.shape.ShapeLine;
import com.toly1994.logic_canvas.core.shape.ShapeText;

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
        Pos pos = new Pos(0,0);
        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);
        if (step == 0) {
            return;
        }
        //横线
        for (int i = 0; i < getScreenHeight(ctx) / step; i++) {
            ;
            painter.draw(new ShapeLine().ps(
                    pos.clone(0f, 0f - step * i),
                    pos.clone(getScreenWidth(ctx) + 0F, 0f - step * i)
                    ).ss(Color.GRAY).b(2f).de(Cons.DOT_LINE_4)
            );
        }
        //竖线
        for (int i = 0; i <= getScreenWidth(ctx) / step; i++) {
            painter.draw(new ShapeLine().ps(
                    pos.clone(0f + step * i, 0f),
                    pos.clone(0f + step * i, 0f - getScreenHeight(ctx)))
                    .ss(Color.GRAY).b(2f).de(new DashPathEffect(new float[]{4, 4}, 0))
            );
        }
    }

    /**
     * @param ctx    上下文
     * @param coo    坐标系原点
     * @param step   小线间隔（像素）
     * @param canvas 画布
     */
    public static void drawCoord(Context ctx, Pos coo, float step, Canvas canvas) {
        Pos pos = new Pos(0,0);
        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);
        int cooColor = Color.BLACK;
        Float lineHeight = dp2px(ctx, 4f);
        int winH = getScreenHeight(ctx);
        int winW = getScreenWidth(ctx);
        //横线
        painter.draw(new ShapeLine()
                .ps(pos.clone(-coo.x, 0), pos.clone(winW - coo.x, 0))
                .ss(cooColor).coo(coo));
        //竖线
        painter.draw(new ShapeLine()
                .ps(pos.clone(0, -(winH - coo.y)), pos.clone(0, coo.y))
                .ss(cooColor).coo(coo));
        //右侧小线
        for (int i = 1; i < (winW - coo.x) / step; i++) {
            Pos p0 = pos.clone(step * i, 0);
            painter.draw(new ShapeLine()
                    .ps(p0, pos.clone(step * i, lineHeight)).
                            ss(cooColor).coo(coo));
            painter.drawText(
                    new ShapeText()
                            .str("" + (int) (i * step)).al(">")
                            .size(20).p(p0.add(coo).add(pos.clone(10, 25)))
                            .ss(cooColor)
            );
        }
        //左侧小线
        for (int i = 1; i < coo.x / step; i++) {
            Pos p0 = pos.clone(-step * i, 0);
            painter.draw(new ShapeLine()
                    .ps(p0, pos.clone(-step * i, lineHeight)).
                            ss(cooColor).coo(coo));
            painter.drawText(
                    new ShapeText()
                            .str("-" + (int) (i * step)).al(">")
                            .size(20).p(p0.add(coo).add(pos.clone(10f, 25f)))
                            .ss(cooColor)
            );
        }
        //上侧小线
        for (int i = 1; i <(winH - coo.y) / step; i++) {
            Pos p0 = pos.clone(0, step * i);
            painter.draw(
                    new ShapeLine()
                            .ps(p0, pos.clone(lineHeight, step * i)).
                            ss(cooColor).coo(coo));

            painter.drawText(
                    new ShapeText()
                            .str("-" + (int) (i * step)).al(">")
                            .size(20).p(p0.add(coo).add(pos.clone(-10f, 5f)))
                            .ss(cooColor)
            );
        }
        //下侧小线
        for (int i = 1; i < coo.y  / step; i++) {
            Pos p0 = pos.clone(0, -step * i);
            painter.draw(new ShapeLine()
                    .ps(p0, pos.clone(lineHeight, -step * i)).
                            ss(cooColor).coo(coo));

            painter.drawText(
                    new ShapeText()
                            .str("" + (int) (i * step)).al(">")
                            .size(20).p(p0.add(coo).add(pos.clone(-10, 5f)))
                            .ss(cooColor)
            );
        }
        //右小箭头
        painter.draw(new ShapeLine().ps(
                pos.clone(winW - coo.x - lineHeight * 2, lineHeight),
                pos.clone(winW - coo.x - lineHeight * 2, -lineHeight),
                pos.clone(winW - coo.x, 0),
                pos.clone(winW - coo.x - lineHeight * 2, lineHeight)
        ).fs(cooColor).coo(coo));
        //上小箭头
        painter.draw(new ShapeLine().ps(
                pos.clone(lineHeight, coo.y - lineHeight * 2),
                pos.clone(-lineHeight, coo.y - lineHeight * 2),
                pos.clone(0, coo.y),
                pos.clone(lineHeight, coo.y - lineHeight * 2)
        ).fs(cooColor).coo(coo));
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

    /**
     * 适配dp
     *
     * @param dp
     * @return
     */
    public static Float dp2px(Context ctx, Float dp) {
        if (dp != null) {
            final Float scale = ctx.getResources().getDisplayMetrics().density;
            return dp * scale + 0.5f;
        }
        return dp;
    }
}
