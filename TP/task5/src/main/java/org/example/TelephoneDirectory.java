package org.example;

import java.util.*;

public class TelephoneDirectory {
    TreeMap<String, LinkedHashSet<String>> phoneHashMap = new TreeMap<>();
    void add(String lastname, String phone){
        LinkedHashSet<String> phoneSet;
        checkForPhone(phone);
        if(phoneHashMap.containsKey(lastname)){
            //phoneSet = new LinkedHashSet<>();
            phoneSet = phoneHashMap.get(lastname);
            phoneSet.add(phone);
        }
        else {
            phoneSet = new LinkedHashSet<>();
            phoneSet.add(phone);
        }
        phoneHashMap.put(lastname, phoneSet);
    }
    void checkForPhone(String phone){

        for (LinkedHashSet<String> hashSet: phoneHashMap.values()){
            if(hashSet.contains(phone)){
                hashSet.remove(phone);
                break;
            }
        }

    }
    void get(String lastname){
        if(phoneHashMap.containsKey(lastname)){
            System.out.print(lastname + " ");
            for (String string: phoneHashMap.get(lastname)){
                System.out.print(" " + string);
            }
        }
        else {
            System.out.print("Номеров для такой фамилии нет");
        }

    }
    void get(){
        System.out.println(phoneHashMap);
    }
}
