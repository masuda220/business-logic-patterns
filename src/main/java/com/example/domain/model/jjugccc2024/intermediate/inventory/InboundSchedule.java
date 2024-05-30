package com.example.domain.model.jjugccc2024.intermediate.inventory;

import java.time.LocalDate;
import java.util.List;

class InboundSchedule {
    List<Inbound> 入庫予定;

    int 前日までの累計(LocalDate 指定日) {
        return 入庫予定.stream()
                .filter(入庫 -> 入庫.入庫日.isBefore(指定日))
                .mapToInt(入庫 -> 入庫.入庫数)
                .sum();
    }
}