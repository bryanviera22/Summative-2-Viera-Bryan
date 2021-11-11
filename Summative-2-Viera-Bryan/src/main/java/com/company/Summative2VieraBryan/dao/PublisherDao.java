package com.company.Summative2VieraBryan.dao;

import com.company.Summative2VieraBryan.dto.Publisher;

import java.util.List;

public interface PublisherDao {

    //Create
    Publisher addPublisher(Publisher publisher);

    //Read book by id
    Publisher getPublisher(int id);

    //Read all
    List<Publisher> getAllPublishers();

    //Update
    void updatePublisher(Publisher publisher);

    //Delete
    void deletePublisher(int id);

}
