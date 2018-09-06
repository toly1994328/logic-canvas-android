package com.toly1994.logic_canvas.core.shape;

import android.graphics.Path;

/**
 * 作者：张风捷特烈
 * 时间：2018/7/6:10:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class ShapeCommon extends Shape {

    ////////////////////////单例模式start//////////////////////////////
    /**
     * 文件帮助类对象
     */
    private static ShapeCommon sShapeCommon;

    /**
     * 私有化构造函数
     */
    private ShapeCommon() {

    }

    /**
     * 单例模式获取FileHelper
     *
     * @return ShapeCommon
     */
    public static ShapeCommon getInstance() {
        if (sShapeCommon == null) {
            synchronized (ShapeCommon.class) {
                if (sShapeCommon == null) {
                    sShapeCommon = new ShapeCommon();
                }
            }
        }
        return sShapeCommon;
    }


    private Path mPath;

    public ShapeCommon(Path path) {
        mPath = path;
    }

    @Override
    public Path formPath() {
        return mPath;
    }
}
