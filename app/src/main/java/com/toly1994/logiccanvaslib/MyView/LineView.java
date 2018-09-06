package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.PainterEnum;
import com.toly1994.logic_canvas.core.shape.Shape;
import com.toly1994.logic_canvas.core.shape.ShapeCommon;
import com.toly1994.logic_canvas.core.shape.ShapeLine;
import com.toly1994.logic_canvas.utils.CanvasUtils;

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
        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);


//        painter.draw(
//                new ShapeLine()
//                        .ps(new Pos(0, 0), new Pos(200, -200))
//                        .b(5f)
//        );


//        Shape shape = new ShapeLine()
//                .ps(
//                        new Pos(200, 100),
//                        new Pos(100, 200),
//                        new Pos(200, 600)
//                ).b(5f).coo(600,600);
//        painter.draw(shape);

//        canvas.drawPath(shape.formPath(), paint);              // 绘制Path

        final Shape commonShape = ShapeCommon.getInstance().coo(600, 600).b(5f).ss(Color.GRAY);
        ShapeLine a = (ShapeLine) new ShapeLine().ang(80f).c(200f).parse().shape(commonShape);

        ShapeLine b = (ShapeLine) a.deepClone().ss(Color.RED);
        System.out.println("a1------:" + a);
//        painter.draw(a);
        System.out.println("a2------:" + a);
        System.out.println("b------:" + b);
        painter.draw(b);

//        ShapeLine shapeL = (ShapeLine) (shape);

//        shapeL.parse();
//        Pos mv = shapeL.mv;
//        Pos pos = mv.clone();
//        Pos add = pos.add(mv.clone(-100, -100));
//        painter.draw(new ShapeLine().ps(add, pos)
//                .ss(Color.BLUE).coo(shapeL.mcoo).b(39));

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


        CanvasUtils.drawGrid(mContext, 50, canvas);
        CanvasUtils.drawCoord(mContext, new Pos(600, 600), 50, canvas);
    }
}
