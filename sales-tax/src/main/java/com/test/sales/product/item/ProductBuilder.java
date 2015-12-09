package com.test.sales.product.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import com.test.sales.product.ProductNotFoundException;
import com.test.sales.product.tax.TaxStrategy;

public class ProductBuilder  {

	private Class<? extends AbstractProduct> cClassProduct;
	private BigDecimal price;
	private TaxStrategy[] tax;
	private AtomicLong aQuantity;
	private String description;
	
	public ProductBuilder(String description, Class<? extends AbstractProduct> cClassProduct, BigDecimal price, TaxStrategy... tax) {
		this.description = description;
		this.cClassProduct = cClassProduct;
		this.price = price;
		this.tax = tax;
		this.aQuantity = new AtomicLong(1);
	}
	
	public synchronized AbstractProduct createProduct() throws ProductNotFoundException {
		
		if (aQuantity.longValue() == 0)
			throw new ProductNotFoundException(); 
		
	    Constructor productConstructor;
		try {
			productConstructor = cClassProduct.getDeclaredConstructor(new Class[] {String.class, BigDecimal.class, TaxStrategy[].class });
	     
			AbstractProduct abstractProduct = (AbstractProduct)productConstructor.newInstance(new Object[] {description, price, tax });
		
			aQuantity.decrementAndGet();
			
			return abstractProduct;
			
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			
			throw new ProductNotFoundException();
		}
	}

	public String getDescription() {
		return description;
	}
}