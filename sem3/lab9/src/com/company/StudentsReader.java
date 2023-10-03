package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentsReader {
    public static Collection readStudentsFromFile(File fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(fileName);
        Collection collection = new Collection();
        while (scanner.hasNext()) {
            try {
                int cardNumber = Integer.parseInt(scanner.next());
                String surname = scanner.next();
                String subject = scanner.next();
                int grade = Integer.parseInt(scanner.next());
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

            } catch (NumberFormatException exc) {
                exc.printStackTrace();
            }
        }
        return collection;
    }
}
