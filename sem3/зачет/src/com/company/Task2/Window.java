package com.company.Task2;

import javax.swing.*;
import java.awt.*;

import java.util.Locale;
import java.util.Scanner;

//В текстовую область/поле редактирования вводится текст
//Командная кнопка инициирует обработку текста и отображение результата в другом поле редактирования
//ВВОДИМЫЙ ТЕКСТ
//разделители между лексемами: пробел, запятая, точка с запятой, переход на новую строку.
//Символ “точка” является разделителем целой и дробной части в вещественных числах.
//НАЙТИ
//сумму всех лексем-чисел.
//Например, для текста:
//Один 1, два 2, три 3 один 1 два 2; Много слов 123.456;
//Минус один -1;
//Надо вывести:
//131.456

public class Window extends JFrame {
    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 500);
        setResizable(false);
        setLayout(new GridBagLayout());
        Locale.setDefault(new Locale("en", "US"));

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        JTextField input = new JTextField("input", 50);
        //input.setSize(200, 100);
        add(input, c);

        JTextField output = new JTextField("output", 50);
        output.setEditable(false);
        add(output, c);

        JButton sumButton = new JButton("Find sum");
        add(sumButton, c);

        sumButton.addActionListener(a -> {
            String text = input.getText();
            Scanner scanner = new Scanner(text).useDelimiter("[\\s,;\\n]+");
            double sum = 0;
            try {
                while (scanner.hasNextDouble()) {
                    sum += scanner.nextDouble();
                }
                System.out.println(sum);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            output.setText(String.valueOf(sum));

        });

    }
}
