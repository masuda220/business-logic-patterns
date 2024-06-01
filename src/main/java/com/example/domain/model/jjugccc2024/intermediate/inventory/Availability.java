package com.example.domain.model.jjugccc2024.intermediate.inventory;

import java.time.LocalDate;
import java.util.List;

/**
 * 未来在庫
 */
class Availability {
    InboundSchedule 入庫予定;
    OutboundSchedule 出庫予定;

    Availability(InboundSchedule 入庫予定, OutboundSchedule 出庫予定) {
        this.入庫予定 = 入庫予定;
        this.出庫予定 = 出庫予定;
    }

    int 出荷可能数(LocalDate 指定日) {
        int 前日残高 = 入庫予定.前日までの累計(指定日) - 出庫予定.前日までの累計(指定日);
        int 当日出荷予定数 = 出庫予定.出荷予定数(指定日);
        return 前日残高 - 当日出荷予定数;
    }

    boolean 出荷可能(int 出荷希望数) {
        return 入庫予定.出荷可能期間()
                .anyMatch(対象日 -> 出荷可能数(対象日) >= 出荷希望数);
    }

    LocalDate 出荷可能日(int 出荷希望数) {
        if (!出荷可能(出荷希望数)) throw new IllegalStateException("出荷できない");

        List<LocalDate> 出荷可能日リスト = 入庫予定.出荷可能期間()
                .filter(対象日 -> 出荷可能数(対象日) >= 出荷希望数)
                .toList();

        return 出荷可能日リスト.getFirst();
    }

    static Availability of(InboundSchedule 入庫予定, OutboundSchedule 出庫予定) {
        return new Availability(入庫予定, 出庫予定);
    }
}