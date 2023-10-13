package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    //Во входном файле input1.txt находится текст. В тексте в квадратных скобках размещены номера фрагментов,
    //которые нужно вставить в исходный текст из списка фрагментов.
    //Список фрагментов находится во входном файле input2.txt в следующем формате:
    //[номер фрагмета]текст фрагмента
    //Каждый фрагмент начинается с новой строки.
    //Один и тот же фрагмент может быть использован для вставки несколько раз.
    //Исходный файл input1.txt может быть пустым. В файле input1.txt может быть несколько строк.
    //Все фрагменты указаны корректно.
    //Например,
    //input1.txt
    //Microsoft [4] is a suite of apps that [2] you stay connected [1] get things done
    //input2.txt
    //[1]and
    //[2]help
    //[3]the
    //[4]365
    //[5]day
    //В выходной файл output1.txt вывести полученный текст после вставки фрагментов - 4 балла

    private static Map<String, String> findFragments(File fragments) throws FileNotFoundException {
        Scanner scanner = new Scanner(fragments);
        Map<String, String> fragmentsMap = new TreeMap<>();
        while (scanner.hasNext()) {
            StringBuilder number = new StringBuilder();
            StringBuilder fragment = new StringBuilder();
            StringBuilder wordFromFile = new StringBuilder(scanner.next());
            for (int i = 0; i < wordFromFile.length(); ++i) {
                if (i <= 2) {
                    number.append(wordFromFile.charAt(i));
                } else {
                    fragment.append(wordFromFile.charAt(i));
                }
            }
            fragmentsMap.put(number.toString(), fragment.toString());
        }
        return fragmentsMap;
    }

    public static void insertFragmentsInFile(File text, File fragments) throws IOException {
        Scanner scanner = new Scanner(text);
        Map<String, String> fragmentsMap = findFragments(fragments);
        ArrayList<String> words = new ArrayList<>();
        while(scanner.hasNext()){
            String word = scanner.next();
            if(fragmentsMap.containsKey(word)){
                words.add(fragmentsMap.get(word));
            }else{
                words.add(word);
            }
        }
        FileWriter fileWriter = new FileWriter(text, false);
        for(String str : words){
            fileWriter.write(str);
            fileWriter.write(' ');
        }
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException {
        insertFragmentsInFile(new File("input1.txt"), new File("input2.txt"));
    }
}
