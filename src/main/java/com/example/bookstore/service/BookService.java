package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository  ;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBookToCart(Book book ){
        bookRepository.addBookToCart(book);

    }
    public List<Book> getBookFromCart(  ){

        return bookRepository.getBookFromCart();
    }
    public void deleteBookFromCart(Book book){
        bookRepository.deleteBookFromCart( book);
    }
    public void emptyCart( ){
        bookRepository.emptyCart();
    }

    public void addBookToStore(Book book ){
        bookRepository.addBookToStore(book);
    }
    public List<Book> getBookFromStore(){

        return bookRepository.getBookFromStore();
    }


}
