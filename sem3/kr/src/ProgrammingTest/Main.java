package ProgrammingTest;

import Bulb.Bulb;
import Collection.Collection;
import Exception.EmptyException;
import FileReader.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws EmptyException {
        File fileLED = new File
                ("C:\\Users\\olgag\\OneDrive\\Рабочий стол\\учеба\\2 курс\\прога\\кр\\src\\input1LED.txt");
        File fileIncandescent = new File
                ("C:\\Users\\olgag\\OneDrive\\Рабочий стол\\учеба\\2 курс\\прога\\кр\\src\\input2Incandescent.txt");

        //test for LED
        try {
            System.out.println("test for LED");
            Collection<Bulb> collectionWithLED = FileReader.readLEDFromFile(fileLED);
            System.out.println(collectionWithLED.toString());

            System.out.println("\n\nTest for method reverseSortOrder()\n");
            Collection<Bulb> sortedCollection = collectionWithLED.reverseSortOrder();
            System.out.println(sortedCollection.toString());

            System.out.println("\n\nTest for method sortByRatio()\n");
            Collection<Bulb> sortedByRatio = collectionWithLED.sortByRatio();
            System.out.println(sortedByRatio.toString());

            System.out.println("\n\nTest for method getListWithManufacturer()\n");
            List<String> listOfManufacture = collectionWithLED.getListWithManufacturer();
            System.out.println(listOfManufacture.toString());

            System.out.println("\n\nTest for method averagePriceOfManufacture()\n");
            double averagePrice = collectionWithLED.averagePriceOfManufacture("lg");
            System.out.printf("Average price of manufacture = %f", averagePrice);

        } catch (FileNotFoundException | EmptyException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n\n-----------------------------------------------------\n\n");

        //test for Incandescent
        try {
            System.out.println("test for Incandescent");
            Collection<Bulb> collectionWithInc = FileReader.readIncandescentFromFile(fileIncandescent);
            System.out.println(collectionWithInc.toString());

            System.out.println("\n\nTest for method reverseSortOrder()\n");
            Collection<Bulb> sortedCollection = collectionWithInc.reverseSortOrder();
            System.out.println(sortedCollection.toString());

            System.out.println("\n\nTest for method sortByRatio()\n");
            Collection<Bulb> sortedByRatio = collectionWithInc.sortByRatio();
            System.out.println(sortedByRatio.toString());

            System.out.println("\n\nTest for method getListWithManufacturer()\n");
            List<String> listOfManufacture = collectionWithInc.getListWithManufacturer();
            System.out.println(listOfManufacture.toString());

            System.out.println("\n\nTest for method averagePriceOfManufacture()\n");
            double averagePrice = collectionWithInc.averagePriceOfManufacture("lg");
            System.out.printf("Average price of manufacture = %f", averagePrice);

        } catch (FileNotFoundException | EmptyException e) {
            e.printStackTrace();
        }
    }
}
