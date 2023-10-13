package ProgrammingTest;

import Academic.Academic;
import Cource.Course;
import Notifier.Notifier;
import Student.Student;
import Student.Undergraduate;
import Student.Postgraduate;
import Writer.Writer;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class ProgrammingTest {

    public static void main(String[] args) {
        Set<Student> students = new HashSet<Student>();
        students.add(new Undergraduate("", "gg4", "", new Academic("Paul Deitel")));
        students.add(new Undergraduate("", "pr3", "", new Academic("Cay Horstmann")));
        students.add(new Postgraduate("", "te2", "", new Academic("Paul Deitel")));
        students.add(new Postgraduate("", "yj34", "", new Academic("Cay Horstmann")));
        students.add(new Postgraduate("", "jj8", "", new Academic("Cay Horstmann")));

        Course course = new Course("course", students);
        System.out.println(course.getPostgraduates("Cay Horstmann").toString());

        Notifier notifier = new Notifier(students);
        notifier.doNotifyAll("Hello, students!");

        //System.out.println(course.getStudents().toString());

        Iterator it = course.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }

        Predicate<Student> predicate = (Predicate<Student>) student -> student.getLogin().length() > 3;
        System.out.println(course.set(predicate).toString());

        Writer writer = new Writer(course);
        writer.writeIntoFile(new File("output.txt"));

    }
}
