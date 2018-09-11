package com.example.domain.type.money;

import java.util.function.BiFunction;

/**
 * 金額範囲 AmountRange型
 */
public class AmountRange {

	private Amount min;
	private Amount max;
	
	/**
	 * コンストラクタ
	 * 
	 * @param min 最小値(以上を判定)
	 * @param max 最大値(未満を判定)
	 */
	public AmountRange(Amount min, Amount max) {
		if (min == null) {
			throw new NullPointerException();
		}
		if (max == null) {
			throw new NullPointerException();
		}
		this.min = min;
		this.max = max;
	}
	
	/**
	 * 
	 * 「最小値以上 - 最大値未満」であるかどうかを確認する。
	 * 
	 * @param target
	 * @return true: 範囲内,
	 */
	public boolean inBetweenMinOrOverAndLessThanMax(Amount target) {
		if (target == null) {
			throw new NullPointerException();
		}
		return target.isGreaterOrEqualTo(min) && target.isLessThan(max);
	}

	/**
	 * 
	 * 比較条件を指定して範囲チェック
	 *  
	 * @param func 
	 * @return true: 範囲内, false: 範囲外
	 */
	public boolean dynamicRangeCheckBy(BiFunction<Amount, Amount, Boolean> func){
		return func.apply(min, max);
	}
}
