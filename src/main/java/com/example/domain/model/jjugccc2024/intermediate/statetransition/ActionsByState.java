package com.example.domain.model.jjugccc2024.intermediate.statetransition;

import java.util.Map;
import java.util.Set;

import static com.example.domain.model.jjugccc2024.intermediate.statetransition.ActionType.*;
import static com.example.domain.model.jjugccc2024.intermediate.statetransition.StateType.*;

/**
 * 状態ごとに可能なアクションの定義
 */
class ActionsByState {
    Map<StateType, Set<ActionType>> 状態_アクション_マッピング =
            Map.of(
                    審査中, Set.of(承認, 差し戻し),
                    承認済, Set.of(開始, 取り下げ),
                    差し戻し中, Set.of(再申請, 取り下げ),
                    実施中, Set.of(中断, 完了),
                    中断中, Set.of(再開, 中止),
                    終了, Set.of()
            );

    Set<ActionType> 可能なアクション(StateType 現在の状態) {
        return 状態_アクション_マッピング.get(現在の状態);
    }

    boolean アクションの妥当性検査(StateType 現在の状態, ActionType 検査するアクション) {
        return 状態_アクション_マッピング.get(現在の状態).contains(検査するアクション);
    }
}
