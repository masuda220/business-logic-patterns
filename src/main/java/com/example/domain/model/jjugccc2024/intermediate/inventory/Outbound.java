package com.example.domain.model.jjugccc2024.intermediate.inventory;

import java.time.LocalDate;

/**
 * 出庫
 */
class Outbound implements Comparable<Outbound> {
    final LocalDate 出庫日;
    final int 出庫数;

    static final int 出庫数ゼロ = 0;

    private Outbound(LocalDate 出庫日, int 出庫数) {
        this.出庫日 = 出庫日;
        this.出庫数 = 出庫数;
    }

    static Outbound of(LocalDate 出庫日, int 出庫数) {
        return new Outbound(出庫日, 出庫数);
    }

    @Override
    public int compareTo(Outbound other) {
        return 出庫日.compareTo(other.出庫日);
    }
}
