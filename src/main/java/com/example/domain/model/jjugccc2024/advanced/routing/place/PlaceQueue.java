package com.example.domain.model.jjugccc2024.advanced.routing.place;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 探索地点のキュー
 *
 * 幅優先探索
 */
public class PlaceQueue {
    Queue<Place> 探索地点のキュー = new LinkedList<>();

    public void 追加(Place 地点) {
        探索地点のキュー.add(地点);
    }

    public Place 取り出し() {
        return 探索地点のキュー.remove();
    }

    public boolean 空でない() {
        return !探索地点のキュー.isEmpty();
    }
}
