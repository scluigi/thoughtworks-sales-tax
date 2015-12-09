package com.test.sales.product.item;

import java.math.BigDecimal;

import com.test.sales.product.tax.IVisitable;

public interface IItemProduct extends IVisitable {

	String getDescription();

	BigDecimal getPrice();

	BigDecimal getSalesTaxes();

	BigDecimal getPriceWithTax();

}