package com.example.domain.model.jjugccc2024.advanced.proportion;

import java.util.Map;

public class ShareByAmount {
    Map<ShareHolder, Amount> 金額構成;

    private ShareByAmount(Map<ShareHolder, Amount> 金額構成) {
        this.金額構成 = 金額構成;
    }

    static ShareByAmount of(Map<ShareHolder, Amount> 金額構成) {
        return new ShareByAmount(金額構成);
    }
}
