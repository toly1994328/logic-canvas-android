package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.logic_canvas.bean.Painter;
import com.toly1994.logic_canvas.core.ZCanvas;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class LogicView extends View {


    private Context mContext;

    public LogicView(Context context) {
        super(context);
        mContext = context;
    }

    public LogicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ZCanvas zCanvas = new ZCanvas(canvas);
        zCanvas.drawNStar(new Painter().num(5).R(100f).r(50f).b(5f).fs(Color.YELLOW));
    }
}
