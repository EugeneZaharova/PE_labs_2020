package ru.eugene.backend;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LRUCache {
    private int capacity;
    private Queue<String> queue = new LinkedList<>();
    private Map<String, String> dict = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public String get(String key) {
        if (dict.containsKey(key)) {
            queue.remove(key);
            queue.add(key);
            return dict.get(key);
        }
        return "-";
    }

    public void set(String key, String value) {
        if (!dict.containsKey(key) && queue.size() >= capacity) {
            String rm = queue.remove();
            dict.remove(rm);
        } else if (dict.containsKey(key)) {
            queue.remove(key);
        }

        queue.add(key);
        dict.put(key, value);;
    }

    public void rem(String key) {
        queue.remove(key);
        dict.remove(key);
    }
}
