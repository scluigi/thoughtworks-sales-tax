package com.test.sales.product.tax;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public enum TaxStrategy {
	
	Exempt {
		@Override
		public BigDecimal getTaxRate() {
 
			return BigDecimal.ZERO;
		}
	},
	
	Normal {
		@Override
		public BigDecimal getTaxRate() {
 
			return BigDecimal.valueOf(0.10d);
		}
	},
	
	Imported {
		@Override
		public BigDecimal getTaxRate() {
 
			return BigDecimal.valueOf(0.05d);
		}
	};

	private static final BigDecimal ROUNDUP_FACTOR = BigDecimal.valueOf(0.05d);
	
	public abstract BigDecimal getTaxRate();
	
	public BigDecimal caculateTaxes(BigDecimal price) {
	
		BigDecimal compute = price.multiply(getTaxRate());
		
		return compute.divide(ROUNDUP_FACTOR).round(
				new MathContext(2, RoundingMode.UP)).multiply(ROUNDUP_FACTOR);

	}
}