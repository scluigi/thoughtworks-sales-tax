package com.test.sales.product;

import static com.test.sales.product.tax.TaxStrategy.Exempt;
import static com.test.sales.product.tax.TaxStrategy.Imported;
import static com.test.sales.product.tax.TaxStrategy.Normal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.test.sales.product.item.AbstractProduct;
import com.test.sales.product.item.ProductBuilder;
import com.test.sales.product.item.impl.Book;
import com.test.sales.product.item.impl.Food;
import com.test.sales.product.item.impl.Media;
import com.test.sales.product.item.impl.Medicine;
import com.test.sales.product.item.impl.PersonalCare;

public class ProductFactory {

	static {
		ProductFactory.instance().registerProduct("BOOK_001", new ProductBuilder("1 book", Book.class, new BigDecimal("12.49"), Exempt ));
		ProductFactory.instance().registerProduct("CD_001", new ProductBuilder("1 music CD", Media.class, BigDecimal.valueOf(14.99d), Normal ));
		ProductFactory.instance().registerProduct("CHOCO_001", new ProductBuilder("1 chocolate bar", Food.class, BigDecimal.valueOf(0.85d), Exempt ));
	
		ProductFactory.instance().registerProduct("CHOCOBOX_001", new ProductBuilder("1 imported box of chocolates", Food.class, BigDecimal.valueOf(10d), Exempt, Imported ));
		ProductFactory.instance().registerProduct("PERFUME_001", new ProductBuilder("1 imported bottle of perfume", PersonalCare.class, BigDecimal.valueOf(47.50d), Normal, Imported ));

		ProductFactory.instance().registerProduct("PERFUME_IMP_001", new ProductBuilder("1 imported bottle of perfume", PersonalCare.class, BigDecimal.valueOf(27.99d), Normal, Imported ));
		ProductFactory.instance().registerProduct("PERFUME_002", new ProductBuilder("1 bottle of perfume", PersonalCare.class, BigDecimal.valueOf(18.99d), Normal ));
		ProductFactory.instance().registerProduct("PILLS_001", new ProductBuilder("1 packet of headache pills", Medicine.class, BigDecimal.valueOf(18.99d), Exempt ));
		ProductFactory.instance().registerProduct("CHOCOBOX_IMP_001", new ProductBuilder("1 box of imported chocolates", Food.class, BigDecimal.valueOf(18.99d), Normal, Imported ));
	}
	
	private Map<String, ProductBuilder> m_RegisteredProducts = new HashMap<String, ProductBuilder>();

	private static ProductFactory instance;
	
	public static ProductFactory instance() {

		if (instance == null) {
			instance = new ProductFactory();
		}
		return instance;
	}

	public void registerProduct(String productID, ProductBuilder p) {
		m_RegisteredProducts.put(productID, p);
	}

	public AbstractProduct createProduct(String productID) {
				
		if (!m_RegisteredProducts.containsKey(productID))
			throw new ProductNotFoundException();
		
		return m_RegisteredProducts.get(productID).createProduct();
	}
}
