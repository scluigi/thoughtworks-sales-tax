package com.test.sales.product.tax;

import java.math.BigDecimal;

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
    	
		this.salesTaxes = medicine.getSalesTaxes().add(this.salesTaxes);
		
    	this.totalWithTax = this.totalWithTax.add(medicine.getPriceWithTax());
    }
    
    @Override
    public void visit(Book book) {

		this.salesTaxes = book.getSalesTaxes().add(this.salesTaxes);

    	this.totalWithTax = this.totalWithTax.add(book.getPriceWithTax());
    }

    @Override
    public void visit(Media media) {
 
		this.salesTaxes = media.getSalesTaxes().add(this.salesTaxes);

    	this.totalWithTax = this.totalWithTax.add(media.getPriceWithTax());
    }

    @Override
    public void visit(Food food) {
  
		this.salesTaxes = food.getSalesTaxes().add(this.salesTaxes);

    	this.totalWithTax = this.totalWithTax.add(food.getPriceWithTax());
    }

	@Override
	public void visit(PersonalCare personal) {

		this.salesTaxes = personal.getSalesTaxes().add(this.salesTaxes);

		this.totalWithTax = this.totalWithTax.add(personal.getPriceWithTax());
	}
		
    public BigDecimal getSalesTaxes() {
		return salesTaxes;
	}

	public BigDecimal getTotalWithTax() {
        return totalWithTax;
    }
}