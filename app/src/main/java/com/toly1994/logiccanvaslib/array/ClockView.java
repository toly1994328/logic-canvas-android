package com.toly1994.logiccanvaslib.array;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.toly1994.logic_canvas.base.BaseView;
import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.PainterEnum;
import com.toly1994.logic_canvas.core.shape.Shape;
import com.toly1994.logic_canvas.core.shape.ShapeLine;
import com.toly1994.logic_canvas.utils.ColUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：时钟
 */
public class ClockView extends BaseView {
    private static final String TAG = "ClockView";

    Pos coo = v2(500, 800);

    private int mDotWith = 3;
    private int mDotColor = 3;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);
//        //绘制网格
//        CanvasUtils.drawGrid(getContext(), 50, canvas);
//        //绘制坐标系
//        CanvasUtils.drawCoord(getContext(), coo, 50, canvas);
//        绘制60个小线
        for (int i = 0; i < 60; i++) {
            ShapeLine a = null;
            if (i % 5 == 0) {
                a = (ShapeLine) sl.deepClone().ang(i * 6f).c(260f).parse().coo(coo);
                mDotWith = 5;
                mDotColor = ColUtils.randomRGB();
            } else {
                a = (ShapeLine) sl.deepClone().ang(i * 6f).c(280f).parse().coo(coo);
                mDotWith = 3;
                mDotColor = Color.BLUE;
            }
            ShapeLine b = a.deepClone().c(300f).v(null).parse();
            Shape c = sl.deepClone().ps(a.mv, b.mv).coo(coo).b(mDotWith).ss(mDotColor);
            //绘制小圆点
            painter.draw(c);
            if (i % 5 == 0) {
                painter.draw(sa.deepClone().r(4).ang(360).fs(Color.BLACK).p(a.mv).coo(coo));
            }

        }
        //绘制四个圆弧
        for (int i = 0; i < 4; i++) {
            Shape circle = sa.deepClone().r(350).ang(70).coo(this.coo).b(3).ss(Color.parseColor("#D4DAE7")).rot(10 + 90 * i).b(6);
            painter.draw(circle);
        }
        //绘制文字
        painter.drawText(st.deepClone().size(70).str("Ⅲ").p(coo.add(350, 30)));
        painter.drawText(st.deepClone().size(70).str("Ⅵ").p(coo.add(0, 350 + 30)));
        painter.drawText(st.deepClone().size(70).str("Ⅸ").p(coo.add(-350, 30)));
        painter.drawText(st.deepClone().size(70).str("Ⅻ").p(coo.add(0, -350 + 30)));
        painter.drawText(st.deepClone().size(35).str("Toly").p(coo.add(0, -180)));
        //绘制小圆
        Shape center1 = sa.deepClone().ang(270f).r(25).coo(this.coo).b(5).ss(Color.parseColor("#6B6B6B"));
        Shape center2 = sa.deepClone().ang(360).r(15).coo(this.coo).b(5).fs(Color.GRAY);
        Shape center3 = sa.deepClone().ang(360).r(5).coo(this.coo).fs(Color.BLACK);
        //绘制指针
        Shape secLine =  sl.deepClone().ang(-90f).c(330f).coo(this.coo).b(2).ss(Color.parseColor("#6B6B6B"));
        Shape hourLine =  sl.deepClone().ang(45f).c(150f).coo(this.coo).b(8).ss(Color.parseColor("#8FC552"));
        Shape minLine =  sl.deepClone().ang(145f).c(250f).coo(this.coo).b(8).ss(Color.parseColor("#87B953"));

        painter.draw(center1, secLine, hourLine, minLine, center2, center3);

    }

}
