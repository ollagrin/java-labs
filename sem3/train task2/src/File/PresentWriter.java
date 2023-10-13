package File;

import Present.Present;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PresentWriter {
    public static void writePresentToFile(Present present, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(present.toString());
        writer.close();
    }
}
