package com.example.domain.model.jjugccc2024.advanced.proportion;

class SharePieByAmount {
    SharePie 金額構成;

    private SharePieByAmount(SharePie 金額構成) {
        this.金額構成 = 金額構成;
    }

    boolean 同じ金額構成(SharePieByAmount 比較対象) {
        return this.金額構成.equals(比較対象.金額構成);
    }

    static SharePieByAmount of(SharePie 金額構成) {
        return new SharePieByAmount(金額構成);
    }
}
