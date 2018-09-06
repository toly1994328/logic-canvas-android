package com.toly1994.logiccanvaslib.array;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.toly1994.logic_canvas.base.BaseView;
import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.PainterEnum;
import com.toly1994.logic_canvas.core.shape.Shape;
import com.toly1994.logic_canvas.core.shape.ShapeRect;
import com.toly1994.logic_canvas.go.NumGo;
import com.toly1994.logic_canvas.utils.ColUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class RunBallView extends BaseView {

    Pos coo = v2(500, 600);
    private NumGo mRunNum;

    private long lastFrameTime;
    private Pos mV;
    private Pos mS;
    private Shape mFA;

    private int mAColor = Color.BLUE;
    private Pos mG;

    public RunBallView(Context context) {
        super(context);
    }

    public RunBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init() {
        //创建一个水平速度向量对象,并赋值水平速度为100px/s,竖直速度50px/s
        mV = v2(3000, -5000);
        //创建一个位移向量对象
        mS = v2(0, 0);
        //加速度
        mG = v2(0, -10);

        mRunNum = new NumGo(false, 0, 100000).setOnUpdate(new NumGo.OnUpdate() {
            @Override
            public void onUpdate(float rate) {
                long frameTime = System.currentTimeMillis();
                float detaT = frameTime - lastFrameTime;//时间变化量

                Pos detaV = mG.dotC(detaT);//速度变化量
                mV = mV.add(detaV);//速度增量和
                Pos detaS = mV.dotC(detaT / 10000);//位移增量=速度*时间变化量
                mS = mS.add(detaS);//位移总量 = 位移增量和

                if (mS.x > 200 || mS.x < -200) {
                    mV = mV.refX();
                    mAColor = ColUtils.randomRGB();
                }
                if (mS.y > 200 || mS.y < -600) {
                    mV = mV.refY();
                    mAColor = ColUtils.randomRGB();
                }

                invalidate();
                lastFrameTime = frameTime;//时刻更新
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);

        mFA = sa.deepClone().r(20).ang(360).fs(mAColor).p(mS).coo(coo);
        Shape fr = new ShapeRect().x(440).y(840).r(1).fs(0x66B8F8F0).coo(coo).p(-220, -620);

        painter.draw(fr, mFA);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        parseEvent(event);

        setOnEventListener(new OnEventListener() {
            @Override
            public void down() {

            }

            @Override
            public void up() {
                lastFrameTime = System.currentTimeMillis();
                mRunNum.go();
            }

            @Override
            public void move() {

            }
        });

        return super.onTouchEvent(event);
    }
}
