package com.example.domain.type.quantity.unit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class UnitConverterTest {

    static Unit piece = Unit.PIECE;
    static Unit box = Unit.box(10);
    static Unit carton = Unit.carton(5, box);

    @ParameterizedTest
    @MethodSource
    void convert(String message, int original, Unit source, int expected, Unit target) {
        UnitConverter converter = new UnitConverter(source, target);
        int converted = converter.convert(original);
        assertEquals(expected, converted, message);
    }

    static List<Arguments> convert() {
        return List.of(
              arguments("個から箱", 10, piece, 1, box),
              arguments("個からカートン",50, piece, 1, carton),
              arguments("箱からカートン", 5, box, 1, carton),
              arguments("箱から個", 1, box, 10, piece),
              arguments("カートンから個", 1, carton, 50, piece),
              arguments("カートンから箱", 1, carton, 5, box)
        );
    }

    @ParameterizedTest
    @MethodSource
    void convertThrowsException(String message, int original, Unit source, Unit target) {
        UnitConverter converter = new UnitConverter(source, target);
        assertThrows(IllegalArgumentException.class, () -> converter.convert(original));
    }

    static List<Arguments> convertThrowsException() {
        return List.of(
              arguments("個から箱", 11, piece, box),
              arguments("個からカートン",51, piece, carton),
              arguments("箱からカートン", 6, box, carton)
        );
    }

}