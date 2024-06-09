package com.example.domain.model.jjugccc2024.advanced.proportion;

/**
 * *金額
 */
record Amount(long 金額) {
    long 差分(Amount 他の金額) {
        return 金額 - 他の金額.金額;
    }
}
