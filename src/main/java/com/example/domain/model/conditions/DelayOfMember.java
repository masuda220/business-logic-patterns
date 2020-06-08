package com.example.domain.model.conditions;

import java.util.Objects;

public class DelayOfMember {
    DelayStatus delayStatus;
    MemberType memberType;

    public DelayOfMember(DelayStatus delayStatus, MemberType memberType) {
        this.delayStatus = delayStatus;
        this.memberType = memberType;
    }

    @Override
    public boolean equals(Object other) {
        DelayOfMember that = (DelayOfMember) other;
        return delayStatus == that.delayStatus &&
                memberType == that.memberType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(delayStatus, memberType);
    }
}
