package ru.eugene.backend;

public class Application {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(100);
        cache.set("Jesse", "Pinkman");
        cache.set("Walter", "White");
        cache.set("Jesse", "James");
        System.out.println(cache.get("Jesse")); // вернёт 'James'
        cache.rem("Walter");
        System.out.println(cache.get("Walter")); // вернёт '-'
    }
}
