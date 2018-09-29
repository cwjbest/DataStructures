package com.cwj.test.callback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cwj on 18-4-3.
 */
public class Login extends JFrame {
    public Login(String str){
        super(str);
    }
    static Login fr = new Login("回调测试");

    public static void main(String[] args) {
        fr.setSize(500, 500);
        fr.setLocation(500, 300);
        fr.setBackground(null);
        fr.setLayout(null);

        Button button = new Button("click");
        button.setSize(50, 25);
        button.setLocation(50, 50);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//这就是回调函数
                fr.dispose();
                JFrame frame = new JFrame("新窗口");
                frame.setLocation(100,50);
                frame.setSize(500,500);
                frame.setVisible(true);
            }
        });

        fr.add(button);
        fr.setVisible(true);
    }
}
