package com.example.domain.model.jjugccc2024.intermediate.history;

import java.time.LocalDate;

/**
 * 口座
 */
class Account {
    EventHistory 入出金履歴;

    private Account(EventHistory 入出金履歴) {
        this.入出金履歴 = 入出金履歴;
    }

    Amount 残高(LocalDate 計算日) {
        return 入出金履歴.投影(計算日);
    }

    static Account 生成(EventHistory 入出金履歴) {
        return new Account(入出金履歴);
    }
}
