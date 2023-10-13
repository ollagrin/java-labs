package FileReader;


import Bulb.Bulb;
import Bulb.LEDLamp;
import Bulb.IncandescentLamp;
import Collection.Collection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static Collection<Bulb> readLEDFromFile(File fileName) throws FileNotFoundException {
        Collection<Bulb> collection = new Collection<Bulb>();
        Scanner scanner = new Scanner(fileName);
        String manufacture;
        double power;;
        int numberOfBulbs;
        while (scanner.hasNext()) {
            try {
                manufacture = scanner.next();
                power = Double.parseDouble(scanner.next());
                numberOfBulbs = Integer.parseInt(scanner.next());
                collection.add(new LEDLamp(manufacture, power, numberOfBulbs));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return collection;
    }

    public static Collection<Bulb> readIncandescentFromFile(File fileName) throws FileNotFoundException {
        Collection<Bulb> collection = new Collection<Bulb>();
        Scanner scanner = new Scanner(fileName);
        String manufacture;
        double power;;
        double operatingTime;
        while (scanner.hasNext()) {
            try {
                manufacture = scanner.next();
                power = Double.parseDouble(scanner.next());
                operatingTime = Double.parseDouble(scanner.next());
                collection.add(new IncandescentLamp(manufacture, power, operatingTime));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return collection;
    }
}
