package com.test.sales;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.test.sales.product.ProductFactory;
import com.test.sales.product.item.IItemProduct;

public class SalesTaxesTester {

	// Input 1:
	// 1 book at 12.49
	// 1 music CD at 14.99
	// 1 chocolate bar at 0.85

	// Output 1:
	// 1 book : 12.49
	// 1 music CD: 16.49
	// 1 chocolate bar: 0.85
	// Sales Taxes: 1.50
	// Total: 29.83
	@Test
	public void testInput1() {
		
        ShoppingCart cart = new ShoppingCart();
        
        IItemProduct book = ProductFactory.instance().createProduct("BOOK_001");
        IItemProduct musicCD = ProductFactory.instance().createProduct("CD_001");
        IItemProduct chocolateBar = ProductFactory.instance().createProduct("CHOCO_001");
        
        cart.addItem(book);
        cart.addItem(musicCD);
        cart.addItem(chocolateBar);
		       
        printInputProduct(book);
        printInputProduct(musicCD);
        printInputProduct(chocolateBar);
        
        printCartProduct(cart);
        
        assertTrue(0 == BigDecimal.valueOf(29.83d).compareTo(cart.getTotalWithTax()));
        
		assertTrue(0 == BigDecimal.valueOf(12.49d).compareTo(book.getPriceWithTax()));
		assertTrue(0 == BigDecimal.valueOf(16.49d).compareTo(musicCD.getPriceWithTax()));
		assertTrue(0 == BigDecimal.valueOf(0.85d).compareTo(chocolateBar.getPriceWithTax()));

		assertTrue(0 == BigDecimal.valueOf(29.83d).compareTo(cart.getTotalWithTax()));
	}
	
	// Input 2:
	// 1 imported box of chocolates at 10.00
	// 1 imported bottle of perfume at 47.50

	// Output 2:
	// 1 imported box of chocolates: 10.50
	// 1 imported bottle of perfume: 54.65
	// Sales Taxes: 7.65
	// Total: 65.15
	@Test
	public void testInput2() {

        ShoppingCart cart = new ShoppingCart();
        
        IItemProduct importedChocolates = ProductFactory.instance().createProduct("CHOCOBOX_001");
        IItemProduct importedPerfume = ProductFactory.instance().createProduct("PERFUME_001");

        cart.addItem(importedChocolates);
        cart.addItem(importedPerfume);
		
        printInputProduct(importedChocolates);
        printInputProduct(importedPerfume);
        
        printCartProduct(cart);

		assertTrue(0 == BigDecimal.valueOf(10.50d).compareTo(importedChocolates.getPriceWithTax()));
		assertTrue(0 == BigDecimal.valueOf(54.65d).compareTo(importedPerfume.getPriceWithTax()));
		assertTrue(0 == BigDecimal.valueOf(7.65d).compareTo(cart.getSalesTaxes()));
		assertTrue(0 == BigDecimal.valueOf(65.15d).compareTo(cart.getTotalWithTax()));
	}

	// Input 3:
	// 1 imported bottle of perfume at 27.99
	// 1 bottle of perfume at 18.99
	// 1 packet of headache pills at 9.75
	// 1 box of imported chocolates at 11.25

	// Output 3:
	// 1 imported bottle of perfume: 32.19
	// 1 bottle of perfume: 20.89
	// 1 packet of headache pills: 9.75
	// 1 imported box of chocolates: 11.85
	// Sales Taxes: 6.70
	// Total: 74.68
	@Test
	public void testInput3() {
        
		ShoppingCart cart = new ShoppingCart();
        
		IItemProduct importedPerfume = ProductFactory.instance().createProduct("PERFUME_IMP_001");
		IItemProduct perfume = ProductFactory.instance().createProduct("PERFUME_002");
		IItemProduct headachePills = ProductFactory.instance().createProduct("PILLS_001");
		IItemProduct importedChocolates = ProductFactory.instance().createProduct("CHOCOBOX_IMP_001");
		
		cart.addItem(importedPerfume);
		cart.addItem(perfume);
		cart.addItem(headachePills);
		cart.addItem(importedChocolates);
		
        printInputProduct(importedPerfume);
        printInputProduct(perfume);
        printInputProduct(headachePills);
        printInputProduct(importedChocolates);
       
        printCartProduct(cart);
        
		assertTrue(0 == BigDecimal.valueOf(32.19d).compareTo(importedPerfume.getPriceWithTax()));
		assertTrue(0 == BigDecimal.valueOf(20.89d).compareTo(perfume.getPriceWithTax()));
		assertTrue(0 == BigDecimal.valueOf(9.75d).compareTo(headachePills.getPriceWithTax()));
		assertTrue(0 == BigDecimal.valueOf(11.85d).compareTo(importedChocolates.getPriceWithTax()));
		
		assertTrue(0 == BigDecimal.valueOf(6.70d).compareTo(cart.getSalesTaxes()));
		assertTrue(0 == BigDecimal.valueOf(74.68d).compareTo(cart.getTotalWithTax()));
	}
	
	private void printCartProduct(ShoppingCart cart) {
		StringBuilder sb = new StringBuilder();

		sb.append("\nSales Taxes: ");
		
		sb.append(cart.getSalesTaxes());
		sb.append("\nTotal ");
		sb.append(cart.getTotalWithTax());

		System.out.println( sb );
	}

	private void printInputProduct( IItemProduct product ) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(product.getDescription());
		sb.append(" : ");
		sb.append(product.getPriceWithTax());
		
		System.out.println(sb.toString());
	}

}