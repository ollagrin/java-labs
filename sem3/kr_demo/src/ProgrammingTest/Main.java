package ProgrammingTest;

import Container.*;
import Exceptions.EmptyContainerException;
import FileReader.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static AbstractMap.SimpleEntry<Student, Student> twoGreat(List<Student> students) {
        students.sort(Comparator.comparing(Student::getAverageScore).reversed());
        return new AbstractMap.SimpleEntry<Student, Student>(students.get(0),
                students.get(1));
    }

    public static int frequencyWithScore(List<Student> students, double score){
        return (int) students.stream().filter(s -> s.getAverageScore().equals(score)).count();
    }

    public static int frequencyWithPredicate(List<Student> students, Predicate<Student> predicate){
        return (int) students.stream().filter(predicate).count();
    }

    public static int binarySearch(List<Student> students, Student student){
        students.sort(Student::compareTo);
        return Collections.binarySearch(students, student);
    }



    public static void main(String[] args) {
        Container<Double> numbers = new Container<Double>();
        File fileWithNumbers = new File
                ("C:\\Users\\olgag\\OneDrive\\Рабочий стол\\учеба\\2 курс\\прога\\KR_DEMO\\src\\numbers.txt");
        try {
            FileReader.readNumbersFromFile(fileWithNumbers, numbers);
            System.out.println(numbers.max());
            System.out.println(numbers.min());
        } catch (FileNotFoundException | EmptyContainerException e) {
            e.printStackTrace();
        }

        Container<Student> students = new Container<Student>();
        File fileWithStudents = new File
                ("C:\\Users\\olgag\\OneDrive\\Рабочий стол\\учеба\\2 курс\\прога\\KR_DEMO\\src\\students.txt");
        try {
            FileReader.readStudentsFromFile(fileWithStudents, students);
            System.out.println(students.max());
            System.out.println(students.min());
            System.out.println(twoGreat(students).toString());
            System.out.println(frequencyWithScore(students, 6.8));
            System.out.println(binarySearch());
        } catch (FileNotFoundException | EmptyContainerException e) {
            e.printStackTrace();
        }
    }
}
