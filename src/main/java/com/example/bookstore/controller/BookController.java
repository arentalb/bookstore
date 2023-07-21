package com.example.bookstore.controller;

import com.example.bookstore.exeptions.NotAdminExeption;
import com.example.bookstore.model.Book;
import com.example.bookstore.scopeServices.LoggendinAdminService;
import com.example.bookstore.scopeServices.LoggendinUserService;
import com.example.bookstore.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService ;
    private final LoggendinAdminService  loggendinAdminService;
    private final LoggendinUserService  loggendinUserService;


    public BookController(BookService bookService, LoggendinAdminService loggendinAdminService, LoggendinUserService loggendinUserService) {

        this.bookService = bookService;
        this.loggendinAdminService = loggendinAdminService;
        this.loggendinUserService = loggendinUserService;
    }

    @GetMapping(path = "/admin")
    public String getAdmin(){
        String message  ;
        if (loggendinAdminService.getName()== null ){
            throw new NotAdminExeption() ;
        }else {
            message = "welcome to admin page " ;
        }
        return message;
    }
    @PostMapping(path = "/admin")
    public ResponseEntity<List<Book>> postAdmin(@RequestBody Book mybook  ){
        if (loggendinAdminService.getName()== null ) {

            return ResponseEntity.status(400).body(null);

        }else {
            Book book = new Book();
            book.setName(mybook.getName());
            book.setAuther(mybook.getAuther());
            book.setGenre(mybook.getGenre());
            book.setPrice(mybook.getPrice());
            book.setNumberOfPages(mybook.getNumberOfPages());

            bookService.addBookToStore(book);
            List<Book> books = bookService.getBookFromStore();
            return ResponseEntity.status(200).body(books);

        }


    }

    @GetMapping(path = "/store")
    public ResponseEntity<List<Book>> getstore( ){

        if (loggendinUserService.getName()== null &&loggendinAdminService.getName() ==null){
            return null ;
        }else {
            var books = bookService.getBookFromStore();
            return ResponseEntity.status(200).body(books);

        }


    }
    @PostMapping(path = "/store")
    public ResponseEntity<List<Book>> poststore(@RequestBody Book mybook){

        Book book = new Book();
        book.setName(mybook.getName());
        book.setAuther(mybook.getAuther());
        book.setGenre(mybook.getGenre());
        book.setPrice(mybook.getPrice());
        book.setNumberOfPages(mybook.getNumberOfPages());

        bookService.addBookToCart(book);
        var books = bookService.getBookFromCart();

        return ResponseEntity.status(200).body(books);

    }


    @GetMapping(path = "/cart")
    public ResponseEntity<List<Book>> getCart(){
        if (loggendinUserService.getName()== null && loggendinAdminService.getName() ==null){
            return null;
        }else {
            var books = bookService.getBookFromCart();
            return ResponseEntity.status(200).body(books);
        }

    }
    @PostMapping(path = "/cart")
    public ResponseEntity<List<Book>> postCart(@RequestBody Book mybook ){




        bookService.deleteBookFromCart(mybook);

        if (bookService.getBookFromCart().isEmpty()){
            return null ;

        }else {
            var books = bookService.getBookFromCart();
            return ResponseEntity.status(200).body(books);

        }



    }
}
