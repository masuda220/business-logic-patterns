package com.example.domain.model.jjugccc2024.intermediate.inventory;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * 入庫予定
 */
class InboundSchedule {
    List<Inbound> 入庫予定;

    private InboundSchedule(List<Inbound> 入庫予定) {
        this.入庫予定 = 入庫予定;
    }

    int 前日までの累計(LocalDate 指定日) {
        return 入庫予定.stream()
                .filter(入庫 -> 入庫.入庫日.isBefore(指定日))
                .mapToInt(入庫 -> 入庫.入庫数)
                .sum();
    }

    boolean 入庫予定なし() {
        return 入庫予定.isEmpty();
    }

    Stream<LocalDate> 出荷可能期間() {
        if (入庫予定なし()) throw new IllegalStateException("入庫予定なし");

        LocalDate もっとも早い出荷可能日 = 入庫予定.getFirst().入庫日.plusDays(1);
        LocalDate もっとも遅い出荷可能日 = 入庫予定.getLast().入庫日.plusDays(1);

        return もっとも早い出荷可能日.datesUntil(もっとも遅い出荷可能日.plusDays(1));
    }

    static InboundSchedule from(Collection<Inbound> 入庫予定) {
        List<Inbound> 日付順 = 入庫予定.stream().sorted().toList();
        return new InboundSchedule(日付順);
    }
}