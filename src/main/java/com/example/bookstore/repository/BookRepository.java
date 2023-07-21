package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    public final List<Book> booksInCart ;
    public final List<Book> booksInStore ;

    public BookRepository() {
        this.booksInCart = new ArrayList<>();
        this.booksInStore = new ArrayList<>();
    }

}
