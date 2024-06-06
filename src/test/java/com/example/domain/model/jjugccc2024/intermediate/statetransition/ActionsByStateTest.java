package com.example.domain.model.jjugccc2024.intermediate.statetransition;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.example.domain.model.jjugccc2024.intermediate.statetransition.ActionType.*;
import static com.example.domain.model.jjugccc2024.intermediate.statetransition.StateType.*;
import static org.junit.jupiter.api.Assertions.*;

class ActionsByStateTest {

    static ActionsByState 状態ごとに可能なアクション = new ActionsByState();
    @Test
    void 可能なアクション() {
        assertEquals(Set.of(承認,差し戻し), 状態ごとに可能なアクション.可能なアクション(審査中));
    }

    @Test
    void アクションの妥当性検査() {
        assertTrue(状態ごとに可能なアクション.アクションの妥当性検査(審査中, 承認));
        assertFalse(状態ごとに可能なアクション.アクションの妥当性検査(審査中, 取り下げ));
    }
}