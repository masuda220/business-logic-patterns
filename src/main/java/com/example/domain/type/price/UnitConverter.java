package com.example.domain.type.price;

import com.example.domain.type.money.Amount;
import com.example.domain.type.quantity.unit.PieceUnit;
import com.example.domain.type.quantity.unit.Unit;

public class UnitConverter {

    Unit unit;

    public UnitConverter(Unit unit) {
        this.unit = unit;
    }

    public UnitPrice toPiece(UnitPrice target) {
        if (unit.isPiece()) {
            // TODO フィールドのunitを使用しないパターン。このパターンが存在する時点で実装を再検討すべき？
            // return target;
        }

        if (target.unit.isPiece())
            // TODO 上記TODOと同じ
            return target;

        Amount[] amount = target.amount.divideAndRemainder(unit.piece());

        // TODO よりスマートな方法を検討
        if (amount[1].isEqualTo(new Amount(0))) {
            return new UnitPrice(amount[0], new PieceUnit());
        }

        throw new IllegalArgumentException();
    }

    public UnitPrice toBox(UnitPrice target) {
        if (unit.isPiece()) {
            // TODO 上記TODOと同じ
        }

        if (target.unit.isPiece()) {
            Amount amount = target.amount.multiply(unit.piece());
            return new UnitPrice(amount, unit);
        }

        if (target.unit.isEqualTo(unit))
            return target;

        throw new IllegalArgumentException();
    }

}
