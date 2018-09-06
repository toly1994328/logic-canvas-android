package com.toly1994.logiccanvaslib.array;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.toly1994.logic_canvas.base.BaseView;
import com.toly1994.logic_canvas.base.Cons;
import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.PainterEnum;
import com.toly1994.logic_canvas.core.shape.Shape;
import com.toly1994.logic_canvas.core.shape.ShapeCommon;
import com.toly1994.logic_canvas.core.shape.ShapeLine;
import com.toly1994.logic_canvas.go.NumGo;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ArrowView extends BaseView {
    private static final String TAG = "ArrowView";

    Pos coo = v2(500, 800);
    private float mRotateRate;
    private NumGo mRunNum;

    private float mAng = 0;

    public ArrowView(Context context) {
        super(context);
    }

    public ArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init() {
        mRunNum = new NumGo(false, 10, 10000).setOnUpdate(new NumGo.OnUpdate() {
            @Override
            public void onUpdate(float rate) {
                mRotateRate = rate;
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);

        //基于向量绘制直线
        Shape commonShape = ShapeCommon.getInstance().coo(coo).b(5f).ss(Color.RED);


        ShapeLine a = (ShapeLine) new ShapeLine().ang(mRotateRate*360).c(200f).shape(commonShape);
        painter.draw(a).cap(a);

        ShapeLine b = (ShapeLine)  new ShapeLine().ang(90f).c(200f).shape(commonShape);
        painter.draw(b).cap(b);

        ShapeLine c = (ShapeLine)  new ShapeLine().v(a.add(b)).shape(commonShape).ss(Color.BLUE);
        painter.draw(c).cap(c);

        painter.draw( new ShapeLine().ps(a.mv,c.mv).shape(commonShape).de(Cons.DOT_LINE_4));
        painter.draw( new ShapeLine().ps(b.mv,c.mv).shape(commonShape).de(Cons.DOT_LINE_4));


//        CanvasUtils.drawGrid(getContext(), 50, canvas);
//        CanvasUtils.drawCoord(getContext(), coo, 50, canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        parseEvent(event);

        setOnEventListener(new OnEventListener() {
            @Override
            public void down() {
                Log.e(TAG, "down: " + mRotateRate);


            }

            @Override
            public void up() {
                mRunNum.go();
            }

            @Override
            public void move() {

            }
        });

        return super.onTouchEvent(event);
    }
}
