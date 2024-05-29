package com.example.domain.model.jjugccc2024.beginner.range;

/**
 * 金額範囲
 */
class AmountRange {
    Amount 下限; // 含む
    Amount 上限; // 含まない

    private AmountRange(Amount 下限, Amount 上限) {
        this.下限 = 下限;
        this.上限 = 上限;
    }

    boolean が次の金額を含む(Amount この金額) {
        if (この金額.が次の金額以上である(上限)) return false;
        if (この金額.が次の金額未満である(下限)) return false;
        return true;
    }

    static AmountRange create(Amount 下限, Amount 上限) {
        if (下限.が次の金額以上である(上限)) throw new ArithmeticException("下限が上限より大きい");
        return new AmountRange(下限, 上限);
    }

    @Override
    public String toString() {
        return "AmountRange{" +
                "下限=" + 下限 +
                ", 上限=" + 上限 +
                '}';
    }
}
