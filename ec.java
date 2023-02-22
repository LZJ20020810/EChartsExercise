package org.example.Bean;

public class ec {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ec{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    private String name;
    private int count;

    public ec(){}

    public ec(String name,int count){
        this.name=name;
        this.count=count;
    }


}
