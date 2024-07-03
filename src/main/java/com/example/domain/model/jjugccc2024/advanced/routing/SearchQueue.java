package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 探索地点のキュー
 *
 * 幅優先探索
 */
class SearchQueue {
    Queue<Place> 探索地点のキュー = new LinkedList<>();

    void 追加(Place 地点) {
        探索地点のキュー.add(地点);
    }

    Place 取り出し() {
        return 探索地点のキュー.remove();
    }

    boolean 空でない() {
        return !探索地点のキュー.isEmpty();
    }
}
