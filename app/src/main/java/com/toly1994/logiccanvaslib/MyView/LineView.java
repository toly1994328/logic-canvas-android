package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.logic_canvas.bean.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.shape.Shape;
import com.toly1994.logic_canvas.core.shape.ShapeLine;
import com.toly1994.logic_canvas.simple.CanvasUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class LineView extends View {


    private Context mContext;

    public LineView(Context context) {
        super(context);
        mContext = context;
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final Painter painter = new Painter(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);


//        painter.draw(
//                new ShapeLine()
//                        .ps(new Pos(0, 0), new Pos(200, -200))
//                        .b(5f)
//        );


        Shape shape = new ShapeLine()
                .ps(
                        new Pos(200, 100),
                        new Pos(100, 200)
                ).b(5f).coo(300f,300f);

//        canvas.drawPath(shape.formPath(), paint);              // 绘制Path
        painter.draw(shape);
//
//        Painter ang = new Painter()
//                .b(5f)
//                .p(new Pos(0, 0))
//                .c(300F)
//                .ang(45f)
//                .p(500f,-500f);
//        painter.drawLine(
//                ang
//
//        );


//        CanvasUtils.drawGrid(mContext, 50, canvas);
        CanvasUtils.drawCoord(mContext, new Pos(300f,300f),50, canvas);
    }
}
