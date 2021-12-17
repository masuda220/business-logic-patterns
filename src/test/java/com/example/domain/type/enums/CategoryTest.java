package com.example.domain.type.enums;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.domain.type.enums.Category.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void sortedCategories() {
        List<Category> expected = List.of(_3, _1, _2);
        List<Category> actual = Category.sortedCategories();
        assertEquals(expected, actual);
    }

    @Test
    void sortByPriority() {
        List<String> expected = List.of(_3.name, _1.name, _2.name);
        List<String> actual = Category.sortByPriority();
        assertEquals(expected, actual);
    }
}