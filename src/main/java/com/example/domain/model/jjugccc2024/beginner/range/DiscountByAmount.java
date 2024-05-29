package com.example.domain.model.jjugccc2024.beginner.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

enum DiscountByAmount {
    少額(0, Amount.of(2_000)),
    普通(3, Amount.of(5_000)),
    高額(5, Amount.of(10_000)),
    超高額(10, Amount.上限額);

    final int 割引率;
    final Amount 上限境界;

    DiscountByAmount(int 割引率, Amount 上限境界) {
        this.割引率 = 割引率;
        this.上限境界 = 上限境界;
    }


    static final DiscountByAmount[] values = DiscountByAmount.values();
    Amount 下限境界() {
        if (this == 少額) return Amount.of(0);
        return values[ordinal() - 1].上限境界;
    }

    static final List<AmountRange> list =
            Arrays.stream(values)
            .map(element -> AmountRange.create(element.下限境界(), element.上限境界))
            .toList();

}
