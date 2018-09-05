package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;

/**
 * 作者：张风捷特烈
 * 时间：2018/7/6:10:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class ShapeEmpty extends Shape {

    private Path mPath;

    public ShapeEmpty() {
    }

    public ShapeEmpty(Path path) {
        mPath = path;
    }

    @Override
    public Path formPath() {
        return mPath;
    }
}
