package com.toly1994.logic_canvas.core;

import android.graphics.Canvas;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/5 0005:17:00<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */


public enum PainterEnum {
    INSTANCE;
    private Painter instance;

    PainterEnum() {
        instance = new Painter();
    }

    public Painter getInstance(Canvas canvas) {
        instance.attach(canvas);
        return instance;
    }
}