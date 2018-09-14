package com.example.domain.type.hour;

/**
 * 開始時刻と終了時刻を表現する(時刻間の時間間隔を返す)
 */
public class HourTimeRange {

    HourTime from;
    HourTime to;

    HourTimeRange(HourTime from, HourTime to) {
        this.from = from;
        this.to = to;
    }

    // FIXME betweenのほうがわかりやすいかも
    public HourAndMinute differenceTime() {
        int fromH = from.hourPoint.getHour();
        int fromM = from.hourPoint.getMinute();
        int toH = to.hourPoint.getHour();
        int toM = to.hourPoint.getMinute();

        int prepareFrom = fromH * 60 + fromM;
        int prepareTo = toH * 60 + toM;

        Minute minute = new Minute(prepareTo - prepareFrom);

        return HourAndMinute.from(minute);
	}
}