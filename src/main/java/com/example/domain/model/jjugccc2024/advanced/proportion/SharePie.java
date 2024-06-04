package com.example.domain.model.jjugccc2024.advanced.proportion;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 分担構成
 */
class SharePie {
    final SortedSet<Share> 分担構成;

    private SharePie(SortedSet<Share> 分担構成) {
        this.分担構成 = 分担構成;
    }

    SharePie 主分担者に配分して調整(int 差分) {
        Share 主分担の現在の分担内容 = 分担構成.first(); // 大きい順の先頭
        Share 調整後の分担内容 = 主分担の現在の分担内容.値の調整(差分);

        Set<Share> 調整用の分担構成 = new HashSet<>(分担構成);
        調整用の分担構成.remove(主分担の現在の分担内容);
        調整用の分担構成.add(調整後の分担内容);

        return SharePie.構築(調整用の分担構成);
    }

    int 合計() {
        return 分担構成.stream().mapToInt(Share::値).sum();
    }

    boolean 同じ分担構成(SharePie 比較対象) {
        return this.分担構成.equals(比較対象.分担構成);
    }

    /**
     * 重複なし、値の大きい順、変更不可のShareの集合を構築する
     */
    static SharePie 構築(Collection<Share> 構成要素) {
        List<String> 出資企業リスト = 構成要素.stream()
                .map(Share::toString)
                .distinct().toList();

        if (構成要素.size() != 出資企業リスト.size()) throw new IllegalArgumentException("出資企業が重複");

        SortedSet<Share> 値の大きい順 = 構成要素.stream()
                .sorted(Comparator.comparingInt(Share::値).reversed())
                .collect(Collectors.toCollection(TreeSet::new));
        return new SharePie(Collections.unmodifiableSortedSet(値の大きい順));
    }
}