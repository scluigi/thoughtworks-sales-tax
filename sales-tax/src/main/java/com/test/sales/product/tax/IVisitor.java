package com.test.sales.product.tax;

import com.test.sales.product.item.impl.Book;
import com.test.sales.product.item.impl.Food;
import com.test.sales.product.item.impl.Media;
import com.test.sales.product.item.impl.Medicine;
import com.test.sales.product.item.impl.PersonalCare;

public interface IVisitor {

    public void visit(Book book);

    public void visit(Media clothes);

    public void visit(Medicine medicine);
    
    public void visit(Food food);

    public void visit(PersonalCare food);
}