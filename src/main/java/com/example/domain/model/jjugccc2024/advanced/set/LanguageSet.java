package com.example.domain.model.jjugccc2024.advanced.set;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 経験言語
 */
public class LanguageSet {
    Set<Languages> 経験言語;

    private LanguageSet(Set<Languages> 経験言語) {
        this.経験言語 = 経験言語;
    }

    LanguageSet 合致(LanguageSet 比較対象) {
        Set<Languages> 要求と一致した経験言語 = 経験言語.stream()
                .filter(比較対象.経験言語::contains)
                .collect(Collectors.toSet());
        return new LanguageSet(要求と一致した経験言語);
    }

    static LanguageSet of(Languages... 経験言語) {
        return new LanguageSet(Set.of(経験言語));
    }

    public int count() {
        return 経験言語.size();
    }

    @Override
    public String toString() {
        return "LanguageSet{" +
                "経験言語=" + 経験言語 +
                '}';
    }
}