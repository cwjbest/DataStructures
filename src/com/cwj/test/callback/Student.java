package com.cwj.test.callback;

/**
 * Created by cwj on 18-4-3.
 *
 */
public class Student implements DoHomeWork {
    @Override
    public void doHomeWork(String question, String answer) {
        System.out.println("作业本");
        if (answer.equals("")){
            System.out.println("作业:" + question + " 答案:" + "不知道");
        }else {
            System.out.println("作业:" + question + " 答案:" + answer);
        }
    }

    public void ask(final String question, final Roomate roomate){
        new Thread(() -> roomate.getAnswer(question, Student.this)).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                roomate.getAnswer(question, Student.this);
//            }
//        }).start();

        goHome();
    }

    private void goHome(){
        System.out.println("我回家了，帮我坐下作业。。。");
    }

    public static void main(String[] args) {
        Student student = new Student();
        String question = "当x趋向于0，sin(x)/x =?";
        student.ask(question, new Roomate());
    }

}
