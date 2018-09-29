package com.cwj.test.callback;

/**
 * Created by cwj on 18-4-3.
 */
public class Worker implements DoHomeWork {

    @Override
    public void doHomeWork(String question, String answer) {
        System.out.println("作业本");
        if (answer.equals("")){
            System.out.println("不知道");
        }else {
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        String question = "1+1=?";
        new Roomate().getAnswer(question, worker);
//        new Roomate().getAnswer(question, new DoHomeWork() {
//            @Override
//            public void doHomeWork(String question, String answer) {
//                System.out.println(answer);
//            }
//        });
    }
}
