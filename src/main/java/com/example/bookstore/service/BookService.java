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
        bookRepository.booksInCart.add(book);

    }
    public List<Book> getBookFromCart(  ){

        return bookRepository.booksInCart;
    }
    public void deleteBookFromCart(Book book){

        for(Book b :bookRepository.booksInCart){
            if (b.getName().equals(book.getName())){
                bookRepository.booksInCart.remove(b);
                break;
            }
        }

    }
    public void emptyCart( ){
        bookRepository.booksInCart.clear();
    }

    public void addBookToStore(Book book ){
        bookRepository.booksInStore.add(book);
    }
    public List<Book> getBookFromStore(){

        return bookRepository.booksInStore;
    }


}
