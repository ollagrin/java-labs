package com.company;

import javax.swing.*;
import java.awt.*;

public class Task2Panel extends JPanel {

    public Task2Panel(){
        setLayout(new GridLayout(10,10));
        setPreferredSize(MainWindow.SIZE);

        for (int i = 0 ; i < 100 ; i++){
            String text = "Press me!";
            JButton button = new JButton(text);
            Color color = Color.PINK;
            button.addMouseListener(new MyMouseEvent(color, text));
            button.setBackground(color);
            add(button);
        }
    }
}
