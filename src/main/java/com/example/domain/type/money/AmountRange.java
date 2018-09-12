package com.example.domain.type.money;

/**
 * 金額範囲 AmountRange型
 */
public class AmountRange {

	private Amount min;
	private Amount max;
	
	public AmountRange(Amount min, Amount max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean contains(Amount target) {
		return target.isGreaterOrEqualTo(min) && target.isLessThan(max);
	}
}
