package com.toly1994.logiccanvaslib.array;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.toly1994.logic_canvas.base.BaseView;
import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.PainterEnum;
import com.toly1994.logic_canvas.go.NumGo;
import com.toly1994.logic_canvas.utils.CanvasUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ArrowChangeView extends BaseView {
    private static final String TAG = "ArrowView";

    Pos coo = v2(500, 800);
    private float mRotateRate;
    private NumGo mRunNum;


    private float mAng = 0;

    public ArrowChangeView(Context context) {
        super(context);
    }

    public ArrowChangeView(Context context, @Nullable AttributeSet attrs) {
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
        //绘制网格
        CanvasUtils.drawGrid(getContext(), 50, canvas);
        //绘制坐标系
        CanvasUtils.drawCoord(getContext(), coo, 50, canvas);

//        for (int i = 0; i < 15; i++) {
//            //基于向量绘制直线
//            ShapeLine a = (ShapeLine) new ShapeLine()
//                    .ang((float) ZRandom.rangeInt(0,360))
//                    .c((float) ZRandom.rangeInt(100,400)).coo(coo).b( ZRandom.rangeInt(1,10)).ss(ColUtils.randomRGB());
//            painter.draw(a).cap(a);
//        }

//
        //基于向量绘制直线
//        ShapeLine a = (ShapeLine) new ShapeLine().ang(60f).c(300f).coo(coo).b(5f).ss(Color.GRAY);
//        painter.draw(a).cap(a);


//        pA = pA.ref();//取反向量
//        pA = pA.refX();//X取反向量
//        pA = pA.refY();//X取反向量
//        pA.release();//置为0向量

//        Pos pA = v2(100f, 100f);
//        Pos pB = v2(400f, 50f);
//        ShapeLine a = (ShapeLine) sl.deepClone().v(pA).coo(coo).b(5f).ss(Color.RED);
//        ShapeLine b = a.deepClone().v(pB).parse();
//        ShapeLine c = a.deepClone().v(pA.add(pB));
//
//        painter.draw(a).cap(a);
//        painter.draw(b).cap(b);
//        painter.draw(c).cap(c);
//
//        ShapeLine AC = (ShapeLine) sl.deepClone().ps(pA, pA.add(pB)).coo(coo).b(4).de(Cons.DOT_LINE_8).ss(Color.BLACK);
//        ShapeLine BC = AC.deepClone().ps(pB, pA.add(pB));
//        painter.draw(AC, BC);
//
//        painter.drawText(new ShapeText().str("A").size(40).ss(Color.BLACK).p(pA.refY().add(coo).add(5f, -5f)));
//        painter.drawText(new ShapeText().str("B").size(40).ss(Color.BLACK).p(pB.refY().add(coo).add(-5f, -5f)));
//        painter.drawText(new ShapeText().str("C").size(40).ss(Color.BLACK).p((pA.add(pB)).refY().add(coo).add(15f, -5f)));


//        final Shape commonShape = ShapeCommon.getInstance().coo(coo).b(5f).ss(Color.GRAY);
//
//
//        ShapeLine a = (ShapeLine) sl.ang(40f).c(200f).shape(commonShape);
//        ShapeLine b = (ShapeLine) a.deepClone().coo(coo).ss(Color.RED).p(100, 100).b(3);
//
//        painter.draw(b.ss(Color.GRAY).b(5));
//        a.mv.resize(500);
//        painter.draw(b.ss(Color.BLUE).b(1)).cap(b);
//
//
//        painter.draw(a).cap(a);

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
//                mRunNum.go();
            }

            @Override
            public void move() {

            }
        });

        return super.onTouchEvent(event);
    }
}
