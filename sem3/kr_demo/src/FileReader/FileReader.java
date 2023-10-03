package FileReader;

import Container.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static void readNumbersFromFile(File file, Container<Double> numbers) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Double num = Double.parseDouble(scanner.next());
            numbers.add(num);
        }
    }

    public static void readStudentsFromFile(File file, Container<Student> students) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String surname = scanner.next();
            int year = Integer.parseInt(scanner.next());
            double averageScore = Double.parseDouble(scanner.next());
            Student student = new Student(surname, year, averageScore);
            students.add(student);
        }
    }
}
