package com.company.Summative2VieraBryan.dao;

import com.company.Summative2VieraBryan.dto.Author;


import java.util.List;

public interface AuthorDao {

    //Create
    Author addAuthor(Author author);

    //Read book by id
    Author getAuthor(int id);

    //Read all
    List<Author> getAllAuthors();

    //Update
    void updateAuthor(Author author);

    //Delete
    void deleteAuthor(int id);
}
