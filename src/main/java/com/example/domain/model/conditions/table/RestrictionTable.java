package com.example.domain.model.conditions.table;

import com.example.domain.model.conditions.DelayStatus;
import com.example.domain.model.conditions.MemberType;
import com.example.domain.model.conditions.RestrictionOfQuantity;

import java.util.EnumMap;
import java.util.Map;

import static com.example.domain.model.conditions.DelayStatus.*;
import static com.example.domain.model.conditions.MemberType.大人;
import static com.example.domain.model.conditions.MemberType.子供;
import static com.example.domain.model.conditions.RestrictionOfQuantity.*;

/**
 * 貸出制限の表条件
 */
public class RestrictionTable {

    Map<DelayStatus, Map<MemberType, RestrictionOfQuantity>> table = new EnumMap<>(DelayStatus.class);

    {
        define(遅延日数３日未満, 大人, 貸出５冊まで);
        define(遅延日数３日未満, 子供, 貸出７冊まで);

        define(遅延日数７日未満, 大人, 貸出不可);
        define(遅延日数７日未満, 子供, 貸出４冊まで);

        define(それ以外, 大人, 貸出不可);
        define(それ以外, 子供, 貸出不可);
    }

    void define(DelayStatus delayStatus, MemberType memberType, RestrictionOfQuantity restrictionOfQuantity) {
        Map<MemberType, RestrictionOfQuantity> subMap = table.get(delayStatus);
        if(subMap == null) {
            subMap = new EnumMap<>(MemberType.class);
        }
        subMap.put(memberType, restrictionOfQuantity);
        table.put(delayStatus, subMap);
    }

    public RestrictionOfQuantity lookup(DelayStatus delayStatus, MemberType memberType) {
        return table.get(delayStatus).get(memberType);
    }
}