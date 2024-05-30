package com.example.domain.model.jjugccc2024.intermediate.inventory;

import java.time.LocalDate;

class Inbound {
    final LocalDate 入庫日;
    final int 入庫数;

    private Inbound(LocalDate 入庫日, int 入庫数) {
        this.入庫日 = 入庫日;
        this.入庫数 = 入庫数;
    }

    static Inbound of(LocalDate 入庫日, int 入庫数) {
        return new Inbound(入庫日, 入庫数);
    }
}
