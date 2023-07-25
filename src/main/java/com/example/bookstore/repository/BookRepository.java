package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Book> getBooksMethod(String sql) {
        return jdbcTemplate.query(sql, (r, i) -> {
            Book rowObject = new Book();
            rowObject.setName(r.getString("name"));
            rowObject.setAuther(r.getString("author"));
            rowObject.setGenre(r.getString("genre"));
            rowObject.setPrice(r.getDouble("price"));
            rowObject.setNumberOfPages(r.getInt("numberOfPages"));
            return rowObject;
        });
    }

    public void addBookToCart(Book book ){
        String sql = "INSERT INTO booksInCart  (name, author ,genre,price ,numberOfPages)" +
                " VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getName(), book.getAuther(), book.getGenre(), book.getPrice(), book.getNumberOfPages());


    }
    public List<Book> getBookFromCart(){

        return getBooksMethod("select * from booksincart");
    }
    public void deleteBookFromCart(Book book){

        String sql = "DELETE FROM booksInCart WHERE name = ?";
        jdbcTemplate.update(sql, book.getName());

    }

    public void emptyCart( ){
        String sql = "DELETE FROM booksincart" ;
        jdbcTemplate.update(sql);
    }

    public void addBookToStore(Book book ){
        String sql = "INSERT INTO booksInStore  (name, author ,genre,price ,numberOfPages)" +
                " VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getName(), book.getAuther(), book.getGenre(), book.getPrice(), book.getNumberOfPages());



    }
    public List<Book> getBookFromStore(){

        return getBooksMethod("select * from booksinstore");

    }
}
