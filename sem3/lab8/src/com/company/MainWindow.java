package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

    private JTabbedPane mainPane;
    private JPanel panelTask1;
    private Task2Panel panelTask2;
    private JPanel panelTask3;
    private ButtonGroup radioButtonGroup;
    private JRadioButton[] radioButtons;
    private ImageIcon[] pictures;
    private JList leftList;
    private JList rightList;
    private DefaultListModel leftListModel;
    private DefaultListModel rightListModel;
    private JButton moveToLeft;
    private JButton moveToRight;
    public final static Dimension SIZE = new Dimension(1000, 500);
    public final static Dimension LIST_SIZE = new Dimension(300, 500);
    public final static int NUM = 3;

    MainWindow() {
        super("Lab8");
        mainPane = new JTabbedPane();
        panelTask1 = new JPanel();
        panelTask2 = new Task2Panel();
        panelTask3 = new JPanel();

        //task1
        JPanel panelButtons = new JPanel(new BorderLayout());
        leftList = new JList();
        rightList = new JList();
        leftListModel = new DefaultListModel();
        rightListModel = new DefaultListModel();

        moveToLeft = new JButton("<");
        moveToRight = new JButton(">");

        panelTask1.setLayout(new BorderLayout());
        panelTask1.add(leftList, BorderLayout.WEST);
        panelTask1.add(rightList, BorderLayout.EAST);
        panelButtons.add(moveToLeft, BorderLayout.SOUTH);
        panelButtons.add(moveToRight, BorderLayout.NORTH);
        panelTask1.add(panelButtons, BorderLayout.CENTER);
        panelTask1.setPreferredSize(SIZE);
        leftList.setPreferredSize(LIST_SIZE);
        rightList.setPreferredSize(LIST_SIZE);

        leftListModel.addElement(99);
        leftListModel.addElement(87);
        leftListModel.addElement(78);
        leftListModel.addElement("AA");
        leftListModel.addElement("I");
        leftListModel.addElement("op");
        rightListModel.addElement("Hello");
        rightListModel.addElement("Humans");
        rightListModel.addElement("Dog");
        rightListModel.addElement("!");
        rightListModel.addElement("Maths");

        leftList.setModel(leftListModel);
        rightList.setModel(rightListModel);


        mainPane.add(panelTask1, "Task 1");
        mainPane.add(panelTask2, "Task 2");
        mainPane.add(panelTask3, "Task 3");
        add(mainPane);

        moveToLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] temp = rightList.getSelectedIndices();

                if (temp.length != 0) {
                    int i = 0;
                    for (int index : temp) {
                        leftListModel.addElement(rightListModel.get(index - i));
                        rightListModel.removeElementAt(index - i);
                        i++;
                    }

                }
            }
        });

        moveToRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] temp = leftList.getSelectedIndices();
                if (temp.length != 0) {
                    int i = 0;
                    for (int index : temp) {
                        rightListModel.addElement(leftListModel.get(index - i));
                        leftListModel.removeElementAt(index - i);
                        i++;
                    }
                }
            }
        });

        //task3
        pictures = new ImageIcon[NUM + 1];
        for (int i = 0; i < 4; i++) {
            String pic = "src\\picture" + i + ".jpg";
            pictures[i] = new ImageIcon(pic);
        }

        radioButtonGroup = new ButtonGroup();
        radioButtons = new JRadioButton[NUM];
        for (int i = 0; i < NUM; i++) {
            radioButtons[i] = new JRadioButton(String.valueOf(i + 1));
            radioButtons[i].setFont(new Font("Dialog", Font.ITALIC, 50));
            radioButtons[i].setIcon(pictures[3]);
            radioButtons[i].setSelectedIcon(pictures[0]);
            radioButtons[i].setPressedIcon(pictures[2]);
            radioButtons[i].setRolloverIcon(pictures[1]);
            radioButtonGroup.add(radioButtons[i]);
            panelTask3.add(radioButtons[i]);
        }
    }
}
