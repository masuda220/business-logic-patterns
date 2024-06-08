package com.example.domain.model.jjugccc2024.advanced.proportion;

/**
 * シェア
 */
record Share(String 企業, int 分担量) implements Comparable<Share> {
    boolean 同じ企業(Share 対象) {
        return 企業.equals(対象.企業);
    }

    Share 掛ける(int 倍数) {
        return new Share(企業, 分担量 * 倍数);
    }

    Share 割る(int 除数) {
        return new Share(企業, 分担量 / 除数); // 端数切捨て
    }

    Share 増やす(int 増分) {
        return new Share(企業, 分担量 + 増分);
    }

    static Share of(String 出資者, Amount 金額) {
        return Share.of(出資者, 金額.金額());
    }

    static Share of(String 出資者, int 分担量) {
        return new Share(出資者, 分担量);
    }

    @Override
    public int compareTo(Share 比較対象) {
        return Integer.compare(比較対象.分担量, 分担量); // 降順
    }
}
