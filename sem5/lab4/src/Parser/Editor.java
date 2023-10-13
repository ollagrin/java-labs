package Parser;

import java.io.*;
import java.util.regex.Pattern;

public class Editor {
    public void refactor() throws IOException {
        File wordsFile =
                new File("C:\\Users\\olgag\\OneDrive\\Рабочий стол\\учеба\\3 курс\\дс1\\lab4\\src\\Parser\\" +
                        "replaceFile.txt");
        String[] words = readWords(wordsFile).split("\n");
        BufferedReader reader = new BufferedReader(
                new FileReader("C:\\Users\\olgag\\OneDrive\\Рабочий стол\\учеба\\3 курс\\дс1\\lab4\\src\\Parser\\" +
                                "program.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        boolean isClosed = true;
        int indexOfBeginning;
        int startRefactoring;
        while ((line = reader.readLine()) != null) {
            if (line.contains("\"")) {
                indexOfBeginning = line.indexOf("\"");
                startRefactoring = 0;
                while (indexOfBeginning >= 0) {
                    if (isClosed) {
                        String str = line.substring(startRefactoring, indexOfBeginning);
                        for (String word : words) {
                            line = line.replace(str, Pattern.compile(matcherPattern(word)).matcher(str).replaceAll(word));
                        }
                        isClosed = false;
                    } else {
                        isClosed = true;
                    }
                    startRefactoring = indexOfBeginning;
                    indexOfBeginning = line.indexOf("\"", indexOfBeginning + 1);
                }
                if (line.length() != 0) {
                    String str = line.substring(startRefactoring);
                    for (String word : words)
                        line = line.replace(str, Pattern.compile(matcherPattern(word)).matcher(str).replaceAll(word));
                }
                stringBuilder.append(line).append("\n");
                continue;
            }

            if (isClosed) {
                for (String word : words) {
                    line = Pattern.compile(matcherPattern(word)).matcher(line).replaceAll(word);
                }
            }
            stringBuilder.append(line).append("\n");
        }
        reader.close();
        BufferedWriter bufferedWriter =
                new BufferedWriter(
                new FileWriter(
                        "refactoredProgram.txt"));
        bufferedWriter.write(stringBuilder.toString());
        bufferedWriter.close();
    }

    private String readWords(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        reader.close();
        return stringBuilder.toString();
    }

    private String matcherPattern(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\\b").append('(');
        for (int i = 0; i < word.length(); i++) {
            stringBuilder.
                    append('[').
                    append(word.charAt(i)).
                    append('|').
                    append(Character.toUpperCase(word.charAt(i))).
                    append(']');
        }
        stringBuilder.append(')').append("\\b");
        return stringBuilder.toString();
    }
}
