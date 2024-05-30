package com.example.domain.model.jjugccc2024.intermediate.inventory;

import java.time.LocalDate;
import java.util.List;

class OutboundSchedule {
    List<Outbound> 出庫予定;

    int 当日出荷予定数(LocalDate 指定日) {
        Outbound 当日出庫予定 = 出庫予定.stream()
                .filter(出庫 -> 出庫.出庫日.isEqual(指定日))
                .findFirst()
                .orElse(Outbound.of(指定日, 0));
        return 当日出庫予定.出庫数;
    }

    int 前日までの累計(LocalDate 指定日) {
        return 出庫予定.stream()
                .filter(出庫 -> 出庫.出庫日.isBefore(指定日))
                .mapToInt(出庫 -> 出庫.出庫数)
                .sum();
    }
}