package org.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //List<String> words = List.of(wrds);

//        List<String> newWords =  Arrays.stream(words)
//                .sorted((word1, word2)-> word1.compareTo(word2))
//                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
//                .entrySet()
//                        .stream().forEach();
//        System.out.println(newWords);

//        System.out.println(Arrays.stream(words)
//                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toCollection(TreeSet::new))))
//                .entrySet()
//                .stream()
//                .max(Comparator.comparing(x->x.getKey()))
//                .map(longTreeSetEntry -> longTreeSetEntry.getValue().stream().collect(Collectors.joining(", ", "","."))).orElse(("")));
        System.out.println("\nЗадание 1");
        String[] words = {"Java","Ава","Ава","Ава", "7", "7", "7","Ruby", "Java", "Java", "Go", "Python"};

        //var newWords =
        Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toCollection(TreeSet::new))))
                .entrySet()
                .stream()
                .max(Comparator.comparing(x->x.getKey()))
                .map(longTreeSetEntry -> longTreeSetEntry.getValue().stream().collect(Collectors.toList()))
                .stream().forEach(wordA -> wordA.stream().forEach(word -> System.out.print(word + " ")));


        System.out.println("\n\nЗадание 2");
        Student[] students = {
                new Student("Ching", "Chong", 1, 2f),
                new Student("Oppo", "Toppo", 1, 5f),
                new Student("Francisco", "Ramon", 2, 3.25f),
                new Student("Lin", "Longe", 1, 4f),
                new Student("Ss", "Pss", 1, 3f),
                new Student("Loq", "Tok", 1, 3f),

        };
        FindBestStudents(students, 1, 4);
    }
    public static void FindBestStudents(Student[] students, int course, int successStudents){
        System.out.print(successStudents + " самых успешных студентов " + course + " курса зовут: ");
        Arrays.stream(students)
                .filter(student -> student.course == course)
                .sorted(Comparator.comparing(Student::getAvgRng).reversed())
                .collect(Collectors.toList())
                .stream()
                .limit(successStudents)
                .forEach(student -> System.out.print("["+ student.getFirstname() + " " + student.getLastname() + " Средний балл:" + student.getAvgRng() + "] "));
    }

}