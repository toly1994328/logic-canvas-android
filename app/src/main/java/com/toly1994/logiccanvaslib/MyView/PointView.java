package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.toly1994.logic_canvas.base.BaseView;
import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.PainterEnum;
import com.toly1994.logic_canvas.core.shape.ShapeDot;
import com.toly1994.logic_canvas.logic.Logic;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/8 0008:8:29<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class PointView extends BaseView {
    private static final String TAG = "PointView";
    private float mRot=0;


    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean init(AttributeSet attrs) {

        setOnEventListener(new OnEventListener() {
            @Override
            public void down(Pos pos) {

            }

            @Override
            public void up(Pos pos, MoveSpeed speed, Orientation orientation) {

            }

            @Override
            public void move(Pos pos, double s, float dy, float dx, double dir) {

            }

        });

        return true;
    }

    //ρ= a(1-cosθ)
    //ρ= a(1-sin3θ)
    //ρ=(e^(cosθ)- 2cos(4θ) + [sin(θ/12)]^5)*100
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);
//        CanvasUtils.drawCoord(getContext(), coo, 50, canvas);
//        CanvasUtils.drawGrid(getContext(), 50, canvas);

        for (int i = 1; i < 500; i++) {
            float ang = (float) (i);
            float rad = Logic.rad(ang);

//            float c = (float) (100 * (1 - Math.sin(rad)));
            float c = (float) (100 * (1 - Math.cos(3 * Logic.rad(ang))));
//          float c = (float) (Math.pow(Math.E,Math.cos(rad)) - 2 * Math.cos(4 * rad) + Math.pow(Math.sin(rad / 12), 5));
            painter.draw(new ShapeDot()
                    .ang(ang).c(c).b(4).coo(coo).rot(mRot)
                    .fs(Color.parseColor("#F05617")));
        }
    }

}
