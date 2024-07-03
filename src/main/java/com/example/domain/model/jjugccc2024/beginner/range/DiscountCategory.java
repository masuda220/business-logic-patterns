package com.example.domain.model.jjugccc2024.beginner.range;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * 金額別の割引率
 */
enum DiscountCategory {
    少額(Amount.of(2_000), DiscountRate.of(0)),
    普通(Amount.of(5_000), DiscountRate.of(3)),
    高額(Amount.of(10_000), DiscountRate.of(5)),
    超高額(Amount.上限額, DiscountRate.of(10));

    final Amount 上限境界;
    final DiscountRate 割引率;

    DiscountCategory(Amount 上限境界, DiscountRate 割引率) {
        this.上限境界 = 上限境界;
        this.割引率 = 割引率;
    }

    static Amount 割り引く(Amount 割引対象金額) {
        DiscountCategory 価格帯 = 該当する(割引対象金額);
        return 割引対象金額.割り引く(価格帯.割引率);
    }

    // 実装の詳細

    static final DiscountCategory[] 価格帯一覧 = DiscountCategory.values();
    Amount 下限境界() {
        if (this == 少額) return Amount.of(0);
        return 価格帯一覧[ordinal() - 1].上限境界;
    }

    static final Map<DiscountCategory, AmountRange> 価格帯別割引テーブル =
        Arrays.stream(価格帯一覧).collect(
                toMap(価格帯 -> 価格帯, 価格帯 -> AmountRange.生成(価格帯.下限境界(), 価格帯.上限境界))
        );

    static DiscountCategory 該当する(Amount 元の金額) {
        return 価格帯別割引テーブル.entrySet().stream()
                .filter(価格帯 -> 価格帯.getValue().が次の金額を含む(元の金額))
                .findFirst()
                .orElseThrow().getKey();
    }
}