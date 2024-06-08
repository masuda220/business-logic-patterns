package com.example.domain.model.jjugccc2024.intermediate.history;

import java.time.LocalDate;
import java.util.List;

class EventHistory {
    List<AccountEvent> 入出金履歴;

    private EventHistory(List<AccountEvent> 入出金履歴) {
        this.入出金履歴 = 入出金履歴;
    }

    Amount 残高の投影(LocalDate 計算日) {
        return 入出金履歴.stream()
                .filter(入出金 -> 入出金.以前(計算日))
                .map(AccountEvent::入出金額)
                .reduce(Amount.ゼロ, Amount::足す);
    }

    static EventHistory of(List<AccountEvent> 履歴データ) {
        return new EventHistory(履歴データ);
    }
}
