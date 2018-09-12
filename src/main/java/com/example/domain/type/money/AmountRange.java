package com.example.domain.type.money;

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
	public boolean contains(Amount target) {
		if (target == null) {
			throw new NullPointerException();
		}
		return target.isGreaterOrEqualTo(min) && target.isLessThan(max);
	}
}
