# Business Logic Patterns

## 目的とアプローチ
- 業務アプリケーション分野のドメインオブジェクトの設計パターンを提供する
- 型（値の種類）の設計スキルを向上するための学習教材や設計のヒントとして提供する
- そのまま再利用するための汎用的な部品は提供しない
- 基本レベルとして、金額・数量・率・日付など、業務で関心のある値の計算・判断を扱う値オブジェクトの設計パターンを提供する
- 応用レベルとして、値オブジェクトを組み合わせた複合オブジェクトの設計パターンを提供する

wikiページ 
https://github.com/masuda220/business-logic-patterns/wiki

## 考え方
- 標準APIの BigDecimalや、LocalDateは汎用的で使いにくい
  * 有効な値の範囲が広すぎる
  * 余計なメソッドが多い
  * 余計なオプション指定（引数）が多い
  * 足りないメソッドが多い
- その他の公開されているライブラリも同じ
- 特定の文脈で、特定の用途に使いやすい、値を扱うクラスのシンプルな設計パターンを揃えたい
- 具体的で限定的な用途を想定して、汎用ライブラリの設計を思い切って単純化する

## 対象とする範囲
現在の対象範囲は、以下の通り

|領域|説明|実装|
|---|---|---|
|数量と単位| 数量計算や、単位換算|[Quantity](/src/main/java/com/example/domain/type/quantity/Quantity.java), [Unit](/src/main/java/com/example/domain/type/quantity/unit/Unit.java)|
|金額|金額計算、税、割引。いったんは、円に限定|[Amount](/src/main/java/com/example/domain/type/money/Amount.java), [DailyPayroll](/src/main/java/com/example/application/service/payroll/DailyPayroll.java)|
|日付|日付計算、日数計算|[Days](/src/main/java/com/example/domain/type/date/Days.java)|
|識別番号/識別名|一致不一致の判定|
|範囲| from-toを扱うクラス、そのコレクション|[AmountRange](/src/main/java/com/example/domain/type/money/AmountRange.java), [DateRange](/main/java/com/example/domain/type/date/DateRange.java)|
|位置|位置の表現、距離計算、場所の判定|
|分類|クラシフィケーション、カテゴライズ、グルーピング|
|残高|履歴と残高の計算|
|状態遷移|状態遷移ルールの表現と演算|[State](/src/main/java/com/example/domain/model/gate/State.java)|
|進捗|進捗状態（ビジネスマイルストーン）の表現と演算|



