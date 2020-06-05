package com.example.domain.model.conditions;

/**
 * 冊数制限
 */
enum RestrictionOfQuantity {
    貸出５冊まで(5),
    貸出７冊まで(7),
    貸出４冊まで(4),
    貸出不可(0);

    int limit;

    RestrictionOfQuantity(int limit) {
        this.limit = limit;
    }
}
