package Writer;

import Cource.Course;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private final Course course;

    public Writer(Course course) {
        this.course = course;
    }

    public void writeIntoFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(course.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
