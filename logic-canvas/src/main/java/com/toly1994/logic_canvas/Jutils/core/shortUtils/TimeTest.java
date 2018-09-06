package com.toly1994.logic_canvas.Jutils.core.shortUtils;

/**
 * 作者：张风捷特烈
 * 时间：2018/8/25 0025:10:16
 * 邮箱：1981462002@qq.com
 * 说明：耗时测试类
 */
public abstract class TimeTest {

    public TimeTest() {
        this("");
    }

    public TimeTest(String str) {
        long startTime = System.currentTimeMillis();
        run();
        long endTime = System.currentTimeMillis();
        System.out.println(str+"方法耗时:" + (endTime - startTime)/1000.f + "秒");
    }

    protected abstract void run();
}
