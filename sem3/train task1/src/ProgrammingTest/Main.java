package ProgrammingTest;

import Candy.*;
import Present.Present;
import File.FileReader;
import Exception.EnumIncorrectException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Present<Candy> present = new Present<Candy>();
        File file = new File("C:\\Users\\olgag\\OneDrive\\Рабочий стол\\учеба\\2 курс\\прога\\New Year\\src\\input.txt");
        try {
            FileReader.readCandiesFromFile(file, present);
        } catch (FileNotFoundException | EnumIncorrectException e) {
            e.printStackTrace();
        }

        System.out.println(present.toString());

        System.out.println("Sum weight: " + present.getTotalWeight() + '\n');

        System.out.println("Sorted by sugar: ");
        ArrayList<Candy> candiesOfSugar = new ArrayList<>(present.getCandyBySugar(14, 16));
        for(Candy candy : candiesOfSugar){
            System.out.println(candy.toString());
        }

        Predicate<Candy> predicate = new Predicate<Candy>() {
            @Override
            public boolean test(Candy candies) {
                return (candies.getWeight() <= 35);
            }
        };

        System.out.println("Sorted by predicate: ");
        List<Candy> candiesList = present.filterCandies(predicate);
        for(Candy tmp : candiesList){
            System.out.println(tmp.toString());
        }

//        Candy candy1 = new Alenka(12, 13);
//        Candy candy2 = new Polesse(80, 50);
//        Candy candy3 = new Romashka(35, 12);
//        Candy candy4 = new Alenka(35, 14);
//        Candy candy5 = new Alenka(36, 13.8);
//
//        Present present = new Present();
//        present.addCandy(candy1);
//        present.addCandy(candy2);
//        present.addCandy(candy3);
//        present.addCandy(candy4);
//        present.addCandy(candy5);
//
//        System.out.println(present.getCandyBySugar(10, 30));

    }
}

