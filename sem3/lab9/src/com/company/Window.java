package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame {

    private JMenuBar menuBar;
    private JList students;
    private JList excStudents;
    private Collection collection;

    Window() {
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        JMenuBar menu = new JMenuBar();

        JMenu mainMenu = new JMenu("Menu");
        menu.add(mainMenu);

        JMenuItem openMenu = new JMenuItem("Open");
        mainMenu.add(openMenu);

        JMenuItem addMenu = new JMenuItem("Add");
        mainMenu.add(addMenu);

        JMenuItem sortMenu = new JMenuItem("Sort");
        mainMenu.add(sortMenu);

        setJMenuBar(menu);

        students = new JList<>();
        add(students, BorderLayout.NORTH);

        excStudents = new JList<>();
        collection = new Collection();

        openMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();
                try {
                    collection = StudentsReader.readStudentsFromFile(file);
                    view(students);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(
                            Window.this,
                            ex.getMessage(),
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });

        addMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cardNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter card number", ""));
                    String surname = JOptionPane.showInputDialog("Enter surname", "");
                    String subject = JOptionPane.showInputDialog("Enter subject", "");
                    Integer grade = Integer.parseInt(JOptionPane.showInputDialog("Enter grade", ""));
                    boolean isFound = false;
                    for (Student student : collection) {
                        if (student.getCardNumber() == cardNumber && student.getSurname().equals(surname)) {
                            isFound = true;
                            student.getGrades().put(subject, grade);
                            break;
                        }
                    }
                    if (!isFound) {
                        Map<String, Integer> grades = new HashMap<>();
                        grades.put(subject, grade);
                        collection.add(new Student(cardNumber, surname, grades));
                    }
                    view(students);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Window.this, "Error data");
                }
            }
        });

        sortMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> list = collection.findExcStudents();

                DefaultListModel<String> listModel = new DefaultListModel();
                for(Student student : list){
                    listModel.addElement(student.toString());
                }
                excStudents.setModel(listModel);

                JPanel addPanel = new JPanel();

                addPanel.setLayout(new BorderLayout());
                addPanel.add(excStudents, BorderLayout.NORTH);

                JOptionPane.showMessageDialog(Window.this, addPanel);
            }
        });
    }

    private void view(JList list){
        list.setSize(300,400);
        DefaultListModel<String>  listModel = new DefaultListModel();
        for(Student student : collection){
            listModel.addElement(student.toString());
        }
        list.setModel(listModel);
    }
}
