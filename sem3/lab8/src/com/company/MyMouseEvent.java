package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseEvent extends MouseAdapter {
    private Color color;
    private String text;

    public MyMouseEvent(Color color, String text) {
        this.color = color;
        this.text = text;
    }

    public void mouseEntered(MouseEvent e){
        JButton tempButton;
        tempButton = (JButton) e.getSource();
        tempButton.setBackground(Color.RED);
    }

    public void mouseExited(MouseEvent e)
    {
        JButton tempButton;
        tempButton = (JButton) e.getSource();
        tempButton.setBackground(color);
    }

    public void mousePressed(MouseEvent e)
    {
        JButton tempButton;
        tempButton = (JButton) e.getSource();
        text = tempButton.getText();
        tempButton.setText("Clicked!");
    }
    public void mouseReleased(MouseEvent e)
    {
        JButton tempButton;
        tempButton = (JButton) e.getSource();
        tempButton.setText(text);

    }
}
