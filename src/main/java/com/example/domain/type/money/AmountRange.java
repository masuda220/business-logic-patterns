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
	 * @throws NullPointerException 指定された値がnullです
	 */
	public AmountRange(Amount min, Amount max) {
		if (min == null) {
			throw new NullPointerException("The specified min value is null.");
		}
		if (max == null) {
			throw new NullPointerException("The specified max value is null.");
		}
		this.min = min;
		this.max = max;
	}
	
	/**
	 * 
	 * 「最小値以上 - 最大値未満」であるかどうかを確認する。
	 * 
	 * @param target 比較したい値(Amount型)
	 * @return true: 範囲内, false: 範囲外
	 * @throws NullPointerException 指定された値がnullです
	 */
	public boolean contains(Amount target) {
		if (target == null) {
			throw new NullPointerException("The specified value is null.");
		}
		return target.isGreaterOrEqualTo(min) && target.isLessThan(max);
	}
}
