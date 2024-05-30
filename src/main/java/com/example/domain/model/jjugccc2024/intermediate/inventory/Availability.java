package com.example.domain.model.jjugccc2024.intermediate.inventory;

import java.time.LocalDate;

class Availability {
    InboundSchedule 入庫予定;
    OutboundSchedule 出庫予定; // 引当

    int 出荷可能数(LocalDate 指定日) {
        return 前日残高(指定日) - 出庫予定.当日出荷予定数(指定日);
    }

    int 前日残高(LocalDate 指定日) {
        return 入庫予定.前日までの累計(指定日) - 出庫予定.前日までの累計(指定日);
    }
}