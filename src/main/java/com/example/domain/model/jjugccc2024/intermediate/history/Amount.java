package com.example.domain.model.jjugccc2024.intermediate.history;

class Amount {
    int 金額;

    static final Amount ゼロ = Amount.of(0);

    private Amount(int 金額) {
        this.金額 = 金額;
    }

    Amount 足す(Amount 他の金額) {
        return Amount.of(金額 + 他の金額.金額);
    }

    boolean 同じ(Amount 他の金額) {
        return this.金額 == 他の金額.金額;
    }

    static Amount of(int 金額) {
        return new Amount(金額);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "金額=" + 金額 +
                '}';
    }
}
