package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.logic_canvas.bean.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.shape.ShapeText;
import com.toly1994.logic_canvas.simple.CanvasUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ZTextView extends View {


    private Context mContext;   
    private Pos pos = Pos.init();

    public ZTextView(Context context) {
        super(context);
        mContext = context;
    }

    public ZTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final Painter painter = new Painter(canvas);

//        for (int i = -4; i < 14; i += 2) {
//            float num = (float) (Math.random() * 500);
//            Shape line = new ShapeRect()
//                    .x(50f).y(num).r(10f)
//                    .fs(ColUtils.randomRGB()).coo(400f, 800f).p(50f * i, 0f);
//
//            painter.drawText(
//                    new ShapeText().str((int)num + "").size(28)
//                            .p(pos.form(22+50f * i, -num-10)
//                                    .add(pos.form(400f, 800f)))
//            );
//            painter.draw(line);
//        }

        painter.drawText(
                new ShapeText()
                        .str("Toly")//文字
                        .size(80)//大小
                        .al("<")//对齐方式
                        .p(400f,400f));


//        CanvasUtils.drawGrid(mContext, 50, canvas);
        CanvasUtils.drawCoord(mContext, pos.form(400, 400), 50, canvas);
    }
}
