package org.example;


// shamelessly stolen from https://stackoverflow.com/a/6271781
public class Pair<T, U> {
    public final T first;
    public final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}