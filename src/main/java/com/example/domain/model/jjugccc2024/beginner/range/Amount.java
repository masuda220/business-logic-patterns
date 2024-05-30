package com.example.domain.model.jjugccc2024.beginner.range;

public class Amount {
    int 金額;

    static final Amount 上限額 = Amount.of(1_000_000);
    static final Amount 一円 = Amount.of(1);

    private Amount(int 金額) {
        this.金額 = 金額;
    }

    Amount 足す(Amount 別の金額) {
        return new Amount(金額 + 別の金額.金額);
    }

    Amount 引く(Amount 別の金額) {
        return new Amount(金額 - 別の金額.金額);
    }

    boolean 同じ金額(Amount other) {
        return 金額 == other.金額;
    }

    boolean が次の金額以上である(Amount other) {
        return 金額 >= other.金額;
    }

    boolean が次の金額未満である(Amount other) {
        return 金額 < other.金額;
    }

    Amount 割り引く(DiscountRate 割引率) {
        return Amount.of(割引率.適用する(金額));
    }

    static Amount of(int 金額) {
        if (金額 > 上限額.金額) throw new ArithmeticException("上限オーバー");
        return new Amount(金額);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "金額=" + 金額 +
                '}';
    }
}
