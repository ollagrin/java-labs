package Cource;

import Student.Student;
import Student.Postgraduate;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Course implements Iterable<Student> {
    Set<Student> students;
    String name;

    public Course(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Postgraduate> getPostgraduates(String NameOfSupervisor) {
        Set<Postgraduate> stud = new HashSet<Postgraduate>();
        for (Student student : students) {
            if (student instanceof Postgraduate &&
                    ((Postgraduate) student).getSupervisor().getName().equals(NameOfSupervisor)) {
                stud.add((Postgraduate) student);
            }
        }
        return stud;
    }

    public void add(Student student) {
        students.add(student);
    }

    /*public Set<Student> set(Predicate<> predicate){
        return (Set<Student>) Arrays.stream(students.toArray()).filter(predicate).collect(students.toArray()).toList());
    }*/

    public List<Object> set(Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }

    @Override
    public String toString() {
        return "Course{" +
                "students=" + students +
                ", name='" + name + '\'' +
                '}';
    }
}
