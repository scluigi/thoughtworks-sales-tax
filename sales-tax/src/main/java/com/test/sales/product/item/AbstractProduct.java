package com.test.sales.product.item;

import java.math.BigDecimal;

import com.test.sales.product.tax.TaxStrategy;

public abstract class AbstractProduct implements IItemProduct {

	private String description;
	private final BigDecimal price;
	private final TaxStrategy[] tax;
	
	public AbstractProduct(String description, BigDecimal price, TaxStrategy... tax) {
		this.description = description;
		this.price = price;
		this.tax = tax;
	}

	/* (non-Javadoc)
	 * @see com.test.sales.product.item.IItemProduct#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see com.test.sales.product.item.IItemProduct#getPrice()
	 */
	@Override
	public BigDecimal getPrice() {
		return price;
	}

	/* (non-Javadoc)
	 * @see com.test.sales.product.item.IItemProduct#getSalesTaxes()
	 */
	@Override
	public BigDecimal getSalesTaxes() {

		BigDecimal totalDuty = BigDecimal.ZERO;

		for (TaxStrategy taxStrategy : tax) {
			totalDuty = totalDuty.add( taxStrategy.caculateTaxes(price) );
		}

		return totalDuty;
	}

	/* (non-Javadoc)
	 * @see com.test.sales.product.item.IItemProduct#getPriceWithTax()
	 */
	@Override
	public BigDecimal getPriceWithTax() {
		return getPrice().add(getSalesTaxes());
	}
}