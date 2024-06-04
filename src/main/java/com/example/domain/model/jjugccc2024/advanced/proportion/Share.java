package com.example.domain.model.jjugccc2024.advanced.proportion;

/**
 * 分担
 */
record Share(String 出資者, int 値) {
    boolean 同じ企業(Share 対象) {
        return 出資者.equals(対象.出資者);
    }

    Share 値の調整(int 調整用の値) {
        return new Share(出資者, 値 + 調整用の値);
    }

    static Share from(String 出資者, Amount 金額) {
        return new Share(出資者, 金額.金額());
    }
}
