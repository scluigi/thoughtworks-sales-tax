package com.test.sales.product.item.impl;

import java.math.BigDecimal;

import com.test.sales.product.item.AbstractProduct;
import com.test.sales.product.tax.IVisitor;
import com.test.sales.product.tax.TaxStrategy;

public class Food extends AbstractProduct {
	
	public Food(String description, BigDecimal price, TaxStrategy... tax) {
		super( description, price, tax  );
    }
	
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}