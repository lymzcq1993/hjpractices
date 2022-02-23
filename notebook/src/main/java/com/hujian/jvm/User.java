package com.hujian.jvm;

public class User {
    private String name;
    private Integer age;

    public User(){};
    public User(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
//        System.out.println(age);
    }
}
