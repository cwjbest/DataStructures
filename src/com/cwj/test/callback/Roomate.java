package com.cwj.test.callback;

/**
 * Created by cwj on 18-4-3.
 */
public class Roomate {
    public void getAnswer(String question, DoHomeWork someone){
        if(question.equals("1+1=?")){
            someone.doHomeWork(question, "2");
        }else if("当x趋向于0，sin(x)/x =?".equals(question)) {
            System.out.println("思考...");
            for (int i=1;i<=3;++i){
                System.out.println(i + "s");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("");
            someone.doHomeWork(question, "1");
        }else {
            someone.doHomeWork(question, "");
        }
    }
}
