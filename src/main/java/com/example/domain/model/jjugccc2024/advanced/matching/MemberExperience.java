package com.example.domain.model.jjugccc2024.advanced.matching;

/**
 * メンバーの経験言語
 */
class MemberExperience {
    Member メンバー;
    LanguageSet 経験言語;

    private MemberExperience(Member メンバー, LanguageSet 経験言語) {
        this.メンバー = メンバー;
        this.経験言語 = 経験言語;
    }

    int 経験言語数() {
        return 経験言語.count();
    }

    MemberExperience 要求に合致(LanguageSet 要求言語) {
        LanguageSet 照合結果 = 経験言語.合致した言語(要求言語);
        return MemberExperience.of(メンバー, 照合結果);
    }

    static MemberExperience of(Member メンバー, Languages... 経験言語) {
        return new MemberExperience(メンバー, LanguageSet.of(経験言語));
    }

    static MemberExperience of(Member メンバー, LanguageSet 経験言語) {
        return new MemberExperience(メンバー, 経験言語);
    }

    String 名前() {
        return メンバー.名前();
    }

    @Override
    public String toString() {
        return "MemberExperiencedLanguages{" +
                "メンバー=" + メンバー +
                ", 経験言語=" + 経験言語 +
                '}';
    }
}
