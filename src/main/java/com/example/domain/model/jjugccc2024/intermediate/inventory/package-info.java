/**
 * 供給能力（納期回答）
 *
 * - 残高
 * - 入庫予定
 * - 出庫予定（引当）
 *
 * ## 考え方
 *
 * - 当日残高見込み = 前日残高見込み + 入庫予定数 - 出庫予定数
 * - 当日出荷可能数 = 前日残高見込み - 当日出庫予定数
 *
 * 前日残高見込み = sum(前日までの入庫予定数）- sum(前日までの出庫予定数）
 *
 * ## 設計メモ
 *
 * - 入庫予定一覧 (イベント一覧）
 * - 出庫予定一覧（イベント一覧）
 *
 * - 当日の出荷可能数
 *   - 日付指定
 *   - 前日までの入庫予定の合計
 *   - 前日までの出庫予定の合計
 *   - 前日までの残高 - 当日の出庫予定 = 当日の出荷可能性
 */
package com.example.domain.model.jjugccc2024.intermediate.inventory;