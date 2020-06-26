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
    public boolean equals(Object o) {
        DelayOfMember that = (DelayOfMember) o;
        return delayStatus == that.delayStatus &&
                memberType == that.memberType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(delayStatus, memberType);
    }
}
