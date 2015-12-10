package com.test.sales.product.tax;

import java.math.BigDecimal;

import com.test.sales.product.item.IItemProduct;
import com.test.sales.product.item.impl.Book;
import com.test.sales.product.item.impl.Food;
import com.test.sales.product.item.impl.Media;
import com.test.sales.product.item.impl.Medicine;
import com.test.sales.product.item.impl.PersonalCare;

public class TaxVisitor implements IVisitor {

    private BigDecimal salesTaxes = BigDecimal.ZERO;
    private BigDecimal totalWithTax = BigDecimal.ZERO;

    @Override
    public void visit(Medicine medicine) {
    	
		performeTax( medicine );	
    }
    
    @Override
    public void visit(Book book) {

		performeTax( book );	
    }

    @Override
    public void visit(Media media) {
 
		performeTax( media );	
    }

    @Override
    public void visit(Food food) {
  
		performeTax( food );	
    }

	@Override
	public void visit(PersonalCare personal) {

		performeTax( personal );	
	}
		
	protected void performeTax(IItemProduct item) {
		
		this.salesTaxes = this.salesTaxes.add( item.getSalesTaxes() );
		this.totalWithTax = this.totalWithTax.add( item.getPriceWithTax() );		
	}
	
    public BigDecimal getSalesTaxes() {
		return salesTaxes;
	}

	public BigDecimal getTotalWithTax() {
        return totalWithTax;
    }
}