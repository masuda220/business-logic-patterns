package com.example.domain.model.jjugccc2024.advanced.matching;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 経験言語
 */
class LanguageSet {
    Set<Languages> 言語セット;

    private LanguageSet(Set<Languages> 言語セット) {
        this.言語セット = 言語セット;
    }

    LanguageSet 合致した言語(LanguageSet 比較対象) {
        Set<Languages> 一致した言語セット = 言語セット.stream()
                .filter(比較対象.言語セット::contains)
                .collect(Collectors.toSet());
        return new LanguageSet(一致した言語セット);
    }

    static LanguageSet of(Languages... 経験言語) {
        return new LanguageSet(Set.of(経験言語));
    }

    int count() {
        return 言語セット.size();
    }

    @Override
    public String toString() {
        return "LanguageSet{" +
                "経験言語=" + 言語セット +
                '}';
    }
}