package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] strings = new String[]{"Java", "C#", "C++", "Ruby", "Go", "Python", "C#", "Java", "JavaScript", "Python", "Lua", "Java"};
        System.out.println(Arrays.asList(strings) + " Количество элементов: "+ Integer.toString(strings.length));
        HashSet<String> hashSet = new HashSet<>();
        HashMap<String, Integer>  hashMap = new HashMap<>();
        for (String string: strings){
            hashSet.add(string);
            if(hashMap.containsKey(string)){hashMap.put(string, hashMap.get(string)+1);}
            else {hashMap.put(string, 1);}
        }

        System.out.println(hashSet + " Количество элементов: "+ Integer.toString(hashSet.toArray().length));
        System.out.println(hashMap);

        TelephoneDirectory directory = new TelephoneDirectory();
        directory.add("Альто", "+79000000001");
        directory.add("Далли", "+79000000010");
        directory.add("Броко", "+79000000020");
        directory.add("Броко", "+79000000022");
        directory.add("Броко", "+79000000021");
        directory.add("Ватко", "+79000000001");

        directory.add("Далли", "+79000000234");

        directory.add("Альто", "+79000000234");

        directory.add("Броко", "+79000000234");
        directory.get();
    }
}