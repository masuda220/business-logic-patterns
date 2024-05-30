package com.example.domain.model.jjugccc2024.intermediate.history;

import java.time.LocalDate;

class AccountEvent {
    AccountEventType 種別;
    LocalDate 発生日;
    Amount 金額;

    private AccountEvent(AccountEventType 種別, LocalDate 発生日, Amount 金額) {
        this.種別 = 種別;
        this.発生日 = 発生日;
        this.金額 = 金額;
    }

    Amount 入出金額() {
        return Amount.of(種別.符号 * 金額.金額);
    }

    boolean より後(LocalDate 対象日) {
        return 発生日.isAfter(対象日);
    }
    boolean 以前(LocalDate 対象日) {
        return !より後(対象日);
    }

    static AccountEvent 入金(LocalDate 発生日, Amount 金額) {
        return new AccountEvent(AccountEventType.入金, 発生日, 金額);
    }

    static AccountEvent 出金(LocalDate 発生日, Amount 金額) {
        return new AccountEvent(AccountEventType.出金, 発生日, 金額);
    }
}
