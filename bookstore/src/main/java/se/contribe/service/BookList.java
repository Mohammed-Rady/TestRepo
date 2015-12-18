package se.contribe.service;

import se.contribe.model.Book;

public interface BookList {

    public Book[] list(String string);

    public boolean add(Book book, int amount);

    public int[] buy(Book... books);

}
