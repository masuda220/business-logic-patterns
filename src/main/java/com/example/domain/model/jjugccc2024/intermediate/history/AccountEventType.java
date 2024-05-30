package com.example.domain.model.jjugccc2024.intermediate.history;

enum AccountEventType {
    入金(1),
    出金(-1);

    final int 符号;

    AccountEventType(int 符号) {
        this.符号 = 符号;
    }
}