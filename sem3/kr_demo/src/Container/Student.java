package Container;

import java.util.Comparator;

public class Student implements Comparable<Student>{
    private String surname;
    private Integer year;
    private Double averageScore;

    public Student(String surname, Integer year, Double averageScore) {
        this.surname = surname;
        this.year = year;
        this.averageScore = averageScore;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public int compareTo(Student o) {
        return Comparator.
                comparing(Student::getAverageScore).
                thenComparing(Student::getYear).
                thenComparing(Student::getSurname).
                compare(this, o);
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", year=" + year +
                ", averageScore=" + averageScore +
                '}' + '\n';
    }
}
