package com.example.domain.model.jjugccc2024.advanced.proportion;

import java.util.*;

/**
 * *シェアパイ
 *
 * 比例配分の実装クラス
 */
class SharePie {
    final SortedSet<Share> 分担構成;

    private SharePie(SortedSet<Share> 分担構成) {
        this.分担構成 = 分担構成;
    }

    SharePie 端数を最大分担者に割り当てて調整(long 正しい全体量) {
        long 端数 = 単純全体量との差分(正しい全体量);
        Collection<Share> 調整後の分担構成 = 端数調整(端数);
        return SharePie.値の大きい順で構築(調整後の分担構成);
    }

    private Collection<Share> 端数調整(long 端数金額) {
        Share 最大分担者の現在の分担内容 = 分担構成.first(); // 大きい順の先頭
        Share 最大分担者の端数調整後の分担内容 = 最大分担者の現在の分担内容.増やす(端数金額);

        Set<Share> 調整用の分担構成 = new HashSet<>(分担構成); // 作業用の可変Set

        調整用の分担構成.remove(最大分担者の現在の分担内容);
        調整用の分担構成.add(最大分担者の端数調整後の分担内容);

        return Collections.unmodifiableSet(調整用の分担構成); // 不変
    }

    private long 単純全体量との差分(long 正しい全体量) {
        long 現在の全体量 = 分担構成.stream().mapToLong(Share::分担量).sum();
        return 正しい全体量 - 現在の全体量;
    }

    SharePie 掛ける(long 倍数) {
        Collection<Share> 結果 = 分担構成.stream()
                .map(分担内容 -> 分担内容.掛ける(倍数)).toList();
        return SharePie.値の大きい順で構築(結果);
    }

    SharePie 割る(int 除数) {
        Collection<Share> 結果 = 分担構成.stream()
                .map(分担内容 -> 分担内容.割る(除数)).toList();
        return SharePie.値の大きい順で構築(結果);
    }

    long 分担量の合計() {
        return 分担構成.stream().mapToLong(Share::分担量).sum();
    }

    boolean 同じ分担構成(SharePie 比較対象) {
        return this.分担構成.equals(比較対象.分担構成);
    }

    /**
     * 重複なし、値の大きい順、変更不可のSharePieを構築する
     */
    static SharePie 値の大きい順で構築(Collection<Share> すべての構成要素) {
        出資企業の重複チェック(すべての構成要素);
        SortedSet<Share> 分担が大きい順 = Collections.unmodifiableSortedSet(new TreeSet<>(すべての構成要素));
        return new SharePie(分担が大きい順);
    }

    private static void 出資企業の重複チェック(Collection<Share> 構成要素) {
        long 出資企業数 = 構成要素.stream()
                .map(Share::toString).distinct().count();

        if (構成要素.size() != 出資企業数) throw new IllegalArgumentException("出資企業が重複");
    }

    @Override
    public String toString() {
        return "SharePie{" +
                "分担構成=" + 分担構成 +
                '}';
    }
}