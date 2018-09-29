package com.cwj.list;
import java.util.Random;

/**
 * Created by cwj on 18-9-17.
 * rand(7)生成rand(13)
 */
public class RandomX {
    public static void main(String[] args) {
        int[] nums = new int[9];
        long timep = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            int random9 = (rand7() - 1) * 7 + rand7();
            if (random9 > 45){
                random9 = (rand7() - 1) * 7 + rand7();
            }
            nums[random9 % 9]++;
        }
        long timen = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i+1 + " : " + nums[i]);
        }
        System.out.println("总用时：" + (timen-timep)/1000 + "s");
    }

    public static int rand7(){
        Random random = new Random();
        return  random.nextInt(7)+1;
    }
}
