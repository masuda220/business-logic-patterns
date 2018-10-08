package com.example.domain.type.hour;

import java.time.temporal.ChronoUnit;

/**
 * 開始時刻と終了時刻を表現する(時刻間の時間間隔を返す)
 */
public class HourTimeRange {

    HourTime begin;
    HourTime end;

    public HourTimeRange(HourTime begin, HourTime end) {
        this.begin = begin;
        this.end = end;
    }

    public HourAndMinute between() {
        long difference = ChronoUnit.MINUTES.between(begin.value, end.value);
        int minutes = Math.toIntExact(difference);
        return HourAndMinute.from(new Minute(minutes));
	}
}