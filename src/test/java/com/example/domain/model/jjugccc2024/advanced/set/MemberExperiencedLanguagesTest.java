package com.example.domain.model.jjugccc2024.advanced.set;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static com.example.domain.model.jjugccc2024.advanced.set.Languages.*;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberSkillMatchingTest {

    static Set<MemberExperience> メンバーごとの経験言語 = Set.of(
            MemberExperience.of(new Member("佐々木"), Java, SQL),
            MemberExperience.of(new Member("細谷"), JavaScript, Python),
            MemberExperience.of(new Member("松本"), Python, SQL),
            MemberExperience.of(new Member("中山"), CSharp, SQL),
            MemberExperience.of(new Member("太田"), GO, Rust, CPlusPlus)
    );

    static LanguageSet 要求言語 = LanguageSet.of(Java, SQL, JavaScript);

    static Map<Integer, Set<String>> expected = Map.of(
            0, Set.of("太田"),
            1, Set.of("松本", "細谷", "中山"),
            2, Set.of("佐々木")
    );

    @Test
    void 要求に合致() {
        Map<Integer, Set<String>> 合致数ごとのメンバー名リスト =
                メンバーごとの経験言語.stream()
                        .map(e -> e.要求に合致(要求言語))
                        .collect(groupingBy(MemberExperience::経験言語数, mapping(MemberExperience::名前, toSet())));
        assertEquals(expected, 合致数ごとのメンバー名リスト);
    }
}