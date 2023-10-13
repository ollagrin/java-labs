package tests;

import java.util.Date;

public class MyClass {
    public Date date;
    public Integer money;

     public MyClass(){
        date = new Date();
        money = Math.toIntExact(Math.round(Math.random() * 100));
    }
}

