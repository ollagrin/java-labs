package File;

import Candy.*;
import Present.Present;
import Exception.EnumIncorrectException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    public static void readCandiesFromFile(File file, Present<Candy> present) throws FileNotFoundException,
            EnumIncorrectException {
        Scanner scanner = new Scanner(file);
        String name;
        double weight;
        double sugar;
        String color;
        ChupaChupsColor chupaChupsColor = ChupaChupsColor.BLACK;
        while (scanner.hasNext()) {
            name = scanner.next();
            weight = Double.parseDouble(scanner.next());
            sugar = Double.parseDouble(scanner.next());
            if (name.equals("ChupaChups")) {
                color = scanner.next();
                present.add(new ChupaChups(name, weight, sugar, ChupaChupsColor.valueOf(color.toUpperCase())));
            } else {
                present.add(new Candy(name, weight, sugar) {
                });
            }
        }
    }
}
