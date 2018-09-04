package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.logic_canvas.bean.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.shape.ShapeRect;
import com.toly1994.logic_canvas.simple.CanvasUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class RectView extends View {


    private Context mContext;

    public RectView(Context context) {
        super(context);
        mContext = context;
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final Painter painter = new Painter(canvas);

        painter.draw(
                new ShapeRect()
                        .x(1000/2f).y(618/2f).r(50f)
                        .b(5f).ss(Color.RED).fs(Color.GRAY)
                        .p(new Pos(100,-100))
        );

//        painter.drawLines(
//                new Painter()
//                        .b(5f)
//                        .ps(
//                                new Pos(0, 0),
//                                new Pos(200, -200),
//                                new Pos(200,-400),
//                                new Pos(200,-400),
//                                new Pos(800,-400),
//                                new Pos(0,0)
//                        )
//        );
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


        CanvasUtils.drawGrid(mContext,50,canvas);
//        CanvasUtils.drawCoord(mContext, new Pos(600, 200), 10, 50, canvas);
    }
}
