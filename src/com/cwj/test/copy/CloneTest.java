package com.cwj.test.copy;

/**
 * Created by cwj on 18-8-29.
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
//        FatherA fatherA = new FatherA();
//        fatherA.name = "A";
//        fatherA.age = 18;
//        fatherA.child = new Child();
//        fatherA.child.name = "a";
//        fatherA.child.age = 28;
//
//        FatherA fatherB = (FatherA) fatherA.clone();//浅拷贝
//
//        System.out.println(fatherA == fatherB);
//        System.out.println(fatherA.hashCode());
//        System.out.println(fatherB.hashCode());
//        System.out.println(fatherA.name);
//        System.out.println(fatherB.name);
//        System.out.println();
//        System.out.println(fatherA.child == fatherB.child);
//        System.out.println(fatherA.child.hashCode());
//        System.out.println(fatherB.child.hashCode());

        FatherB fatherA = new FatherB();
        fatherA.name = "A";
        fatherA.age = 18;
        fatherA.child = new Child();
        fatherA.child.name = "a";
        fatherA.child.age = 28;

        FatherB fatherB = (FatherB) fatherA.clone();//浅拷贝

        System.out.println(fatherA == fatherB);
        System.out.println(fatherA.hashCode());
        System.out.println(fatherB.hashCode());
        System.out.println(fatherA.name);
        System.out.println(fatherB.name);
        System.out.println();
        System.out.println(fatherA.child == fatherB.child);
        System.out.println(fatherA.child.hashCode());
        System.out.println(fatherB.child.hashCode());
    }
}

class FatherA implements Cloneable{
    public String name;
    public int age;
    public Child child;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }
}

class FatherB implements Cloneable{
    public String name;
    public int age;
    public Child child;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            FatherB cloneFatherB = (FatherB) super.clone();
            cloneFatherB.child = (Child) this.child.clone();
            return cloneFatherB;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }
}

class Child implements Cloneable{
    public String name;
    public int age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
