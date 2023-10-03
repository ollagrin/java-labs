package com.company;

import java.util.ArrayList;
import java.util.Set;

public class Collection extends ArrayList<Student>{
    public ArrayList<Student> findExcStudents(){
        ArrayList<Student> excStudents = new ArrayList<>();

        for(Student stud : this){
            Set<String> subjects = stud.getGrades().keySet();
            boolean isExcecellent = false;
            for(String subject : subjects){
                isExcecellent = stud.getGrades().get(subject) > 9;
            }
            if(isExcecellent) {
                excStudents.add(stud);
            }
        }
        return excStudents;
    }
}
