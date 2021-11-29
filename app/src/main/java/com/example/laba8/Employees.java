package com.example.laba8;

public class Employees {
    private String Name;
    private String workingposition;
    private int age;

    Employees(String name, String workingposition, int age){

        this.Name = name;
        this.workingposition = workingposition;
        this.age = age;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getWorkingposition() {
        return workingposition;
    }
    public void setWorkingposition(String breed) {
        this.workingposition = workingposition;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public  String toString()
    {
        return "Имя сотрудника: " + Name + "\nДолжность: " + workingposition + "\nВозраст: " + age;
    }
}
