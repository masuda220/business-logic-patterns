package com.example.domain.model.jjugccc2024.intermediate.inventory;

import java.time.LocalDate;

/**
 * 入庫
 */
class Inbound implements Comparable<Inbound> {
    final LocalDate 入庫日;
    final int 入庫数;

    private Inbound(LocalDate 入庫日, int 入庫数) {
        this.入庫日 = 入庫日;
        this.入庫数 = 入庫数;
    }

    static Inbound of(LocalDate 入庫日, int 入庫数) {
        return new Inbound(入庫日, 入庫数);
    }

    @Override
    public int compareTo(Inbound other) {
        return 入庫日.compareTo(other.入庫日);
    }
}
