package com.example.domain.model.jjugccc2024.advanced.proportion;

/**
 * *金額
 */
record Amount(int 金額) {
    int 差分(Amount 他の金額) {
        return 金額 - 他の金額.金額;
    }
}
