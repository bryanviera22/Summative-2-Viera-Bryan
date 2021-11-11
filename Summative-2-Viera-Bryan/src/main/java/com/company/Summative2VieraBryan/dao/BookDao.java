package com.company.Summative2VieraBryan.dao;

import com.company.Summative2VieraBryan.dto.Book;

import java.util.List;

public interface BookDao {

    //Create
    Book addBook(Book book);

    //Read book by id
    Book getBook(int id);

    //Read all
    List<Book> getAllBooks();

    //Read book by author id
    List<Book> getBookByAuthor(int authorId);

    //Update
    void updateBook(Book book);
    
    //Delete
    void deleteBook(int id);
    
}
