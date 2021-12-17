package com.example.domain.type.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 名前と優先順位を持つ分類区分
 */
public enum Category {
    _1("A", 1),
    _2("B", 2),
    _3("X", 0);

    String name;
    int priority;

    Category(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    static List<String> sortByPriority() {
        List<String> target = new ArrayList<>();
        for (Category each : sortedCategories()) {
            target.add(each.name);
        }
        return target;
    }

    static List<Category> sortedCategories() {
        List<Category> list = Arrays.asList(Category.values());
        list.sort(Comparator.comparingInt(each -> each.priority));
        return list;
    }
}
