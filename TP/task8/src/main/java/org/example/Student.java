package org.example;

public class Student {
    String firstname;
    String lastname;
    int course;
    double avgRng;

    public Student(String firstname, String lastname, int course, double avgRng) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.course = course;
        this.avgRng = avgRng;
    }

    public double getAvgRng() {
        return avgRng;
    }

    public int getCourse() {
        return course;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

