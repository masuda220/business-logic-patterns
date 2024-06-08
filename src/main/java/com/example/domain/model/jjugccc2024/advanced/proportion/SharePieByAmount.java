package com.example.domain.model.jjugccc2024.advanced.proportion;

/**
 * *金額パイ
 */
class SharePieByAmount {
    SharePie 金額構成;

    private SharePieByAmount(SharePie 金額構成) {
        this.金額構成 = 金額構成;
    }

    boolean 同じ金額構成(SharePieByAmount 比較対象) {
        return this.金額構成.同じ分担構成(比較対象.金額構成);
    }

    static SharePieByAmount of(SharePie 金額構成) {
        return new SharePieByAmount(金額構成);
    }

    @Override
    public String toString() {
        return "SharePieByAmount{" +
                "金額構成=" + 金額構成 +
                '}';
    }
}
