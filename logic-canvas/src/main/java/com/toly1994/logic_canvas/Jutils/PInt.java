package com.toly1994.logic_canvas.Jutils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 作者：张风捷特烈
 * 时间：2018/8/24 0024:8:21
 * 邮箱：1981462002@qq.com
 * 说明：正整数集合
 */
public class PInt {

    private List<Integer> whiteBox = new ArrayList<>();//白盒
    private List<Integer> blackBox = new ArrayList<>();//黑盒

    private int mNum;//传入数据

    public PInt(int num) {
        mNum = num;
        if (num <= 0) {
            new RuntimeException("It is can't small than Zero");
        }
    }

    /**
     * 收集所有公因数
     *
     * @return
     */
    public List<Integer> collectBall() {
        collectWhiteBall();
        collectBlackBall();
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.addAll(whiteBox);
        set.addAll(blackBox);
        return new ArrayList<>(set);
    }

    /**
     * 白盒收集公因数
     */
    private void collectWhiteBall() {
        whiteBox.add(1);
        double limitLine = Math.sqrt(mNum);//白盒边界最大值
        for (int i = 2; i <= limitLine; i++) {
            if (mNum % i == 0) {
                whiteBox.add(i);
            }
        }
    }

    /**
     * 用白盒映射出黑盒中公因数
     */
    private void collectBlackBall() {
        for (Integer i : whiteBox) {
            blackBox.add(mNum / i);
        }
    }

    /**
     * 判断是否时时质数
     *
     * @return
     */
    public boolean isPrime() {
        return collectBall().size() == 2;
    }

    /**
     * 判断是否时时质数
     *
     * @return
     */
    public static boolean isPrime(int num) {
        PInt pInt = new PInt(num);
        return pInt.collectBall().size() == 2;
    }

    /**
     * 获取区域内的所有质数(含起始)
     *
     * @param a 起始点
     * @param b 终止点
     * @return
     */
    public static ArrayList<Integer> getPrimeFromA2B(int a, int b) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = a; i <= b; i++) {
            PInt pInt = new PInt(i);
            if (pInt.isPrime()) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 一个数的哥德巴赫猜想所有可能表达式
     * @param num
     * @return
     */
    public static String guess(int num) {
        ArrayList<Integer> rightBox = new ArrayList<>();
        for (int i = 0; i <= num / 2; i++) {
            if (PInt.isPrime(i) && PInt.isPrime(num - i)) {
                rightBox.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : rightBox) {
            sb.append(i + "+" + (num - i) + "=" + num + "\n");
        }
        return sb.toString();
    }
}
