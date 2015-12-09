package com.test.sales;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.test.sales.product.tax.IVisitable;
import com.test.sales.product.tax.TaxVisitor;

public class ShoppingCart {
    private Set<IVisitable> items = null;
    
    private TaxVisitor visitor;
    
    protected TaxVisitor getTaxVisitor() {
    
    	if (visitor == null) {
    		visitor = new TaxVisitor();
    		
            for (IVisitable item : items) {
                item.accept(visitor);
                
            }	
    	}	
    	
    	return visitor;
    }
    
    public BigDecimal getSalesTaxes() {

        return getTaxVisitor().getSalesTaxes();
    }

    public BigDecimal getTotalWithTax() {

        return getTaxVisitor().getTotalWithTax();
    }

    public void addItem(IVisitable item) {
    	if (items == null)
    		items = new HashSet<IVisitable>();
    	
    	items.add(item);
    }
}