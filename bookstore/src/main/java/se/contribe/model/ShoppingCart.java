package se.contribe.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ShoppingCart {

    private List<Book> booksInCart = new ArrayList<Book>();

    public List<Book> getBooksInCart() {
        return booksInCart;
    }

    public void setBooksInCart(List<Book> booksInCart) {
        this.booksInCart = booksInCart;
    }

}
