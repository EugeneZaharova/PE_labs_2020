package ru.eugene.backend;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private Queue<String> queue = new Queue<>();
    private Map<String, Element> dict = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public String get(String key) {
        if (dict.containsKey(key)) {
            queue.remove(dict.get(key).getNode());
            queue.add(key);
            return dict.get(key).getValue();
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

        Queue.Node<String> node = queue.add(key);
        dict.put(key, new Element(value, node));
    }

    public void rem(String key) {
        Element item = dict.remove(key);
        queue.remove(item.getNode());
    }

    @Data
    @AllArgsConstructor
    private static class Element {
        private String value;
        private Queue.Node<String> node;
    }
}
