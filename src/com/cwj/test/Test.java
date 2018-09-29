package com.cwj.test;

import java.util.*;

/**
 * Created by cwj on 18-3-28.
 *
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("cwj", "kobe", "harden");
        for (String name:list) {
            System.out.println(name);
        }
        //这是java8的新特性，利用lambda表达式来简化代码
        list.forEach(a -> System.out.println(a));

        List<GPU> gpus = Arrays.asList(
                new GPU("GTX-1080", 700, 2560, 1607, 1733, 8),
                new GPU("GTX-1070", 550, 1920, 1506, 1683, 8),
                new GPU("GTX-970", 330, 1664, 1050, 1178, 4),
                new GPU("GTX-770", 400, 1536, 1046, 1085, 2));
        gpus.sort(new Comparator<GPU>() {
            @Override
            public int compare(GPU o1, GPU o2) {
                return o1.getB() - o2.getB();
            }
        });

        for (GPU gpu:gpus) {
            System.out.println(gpu.getA());
        }

        System.out.println("lambda...");
        gpus.sort((g1, g2) -> g1.getB() -g2.getB());

        //下面这种方式更加简便
        gpus.sort(Comparator.comparingInt(GPU::getB));
        gpus.forEach((g) -> System.out.println(g.getA()));
    }


}

class GPU{
    private String a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public GPU(String a, int b, int c, int d, int e, int f){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
}
