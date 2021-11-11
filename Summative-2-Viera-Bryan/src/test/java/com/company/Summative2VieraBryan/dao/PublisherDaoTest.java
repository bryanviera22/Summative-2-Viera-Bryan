package com.company.Summative2VieraBryan.dao;

import com.company.Summative2VieraBryan.dto.Author;
import com.company.Summative2VieraBryan.dto.Book;
import com.company.Summative2VieraBryan.dto.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDaoTest {

    @Autowired
    BookDao bookDao;
    @Autowired
    AuthorDao authorDao;
    @Autowired
    PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        // Clean up the test db
        List<Book> bList = bookDao.getAllBooks();
        for(Book b : bList){
            bookDao.deleteBook(b.getId());
        }

        List<Author> aList = authorDao.getAllAuthors();
        for(Author a : aList){
            authorDao.deleteAuthor(a.getId());
        }

        List<Publisher> pList = publisherDao.getAllPublishers();
        for(Publisher p : pList){
            publisherDao.deletePublisher(p.getId());
        }
    }

    @Test
    public void getDeletePublisher() {

        Publisher publisher = new Publisher();
        publisher.setName("Cannon Gate");
        publisher.setStreet("552 Waterview Cl");
        publisher.setCity("San Francisco");
        publisher.setState("California");
        publisher.setPostalCode("63001");
        publisher.setPhone("703-569-4578");
        publisher.setEmail("cannongpublisher@gmail.com");
        publisherDao.addPublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());

        assertEquals(publisher1, publisher);

        publisherDao.deletePublisher(publisher.getId());

        publisher1 = publisherDao.getPublisher(publisher.getId());

        assertNull(publisher1);
    }

    @Test
    public void getAllPublishers() {

        Publisher publisher = new Publisher();
        publisher.setName("Cannon Gate");
        publisher.setStreet("552 Waterview Cl");
        publisher.setCity("San Francisco");
        publisher.setState("California");
        publisher.setPostalCode("63001");
        publisher.setPhone("703-569-4578");
        publisher.setEmail("cannongpublisher@gmail.com");
        publisherDao.addPublisher(publisher);

        publisher = new Publisher();
        publisher.setName("New Publisher");
        publisher.setStreet("1478 Angel Wing Ct");
        publisher.setCity("Arlington");
        publisher.setState("Virginia");
        publisher.setPostalCode("20146");
        publisher.setPhone("703-154-4546");
        publisher.setEmail("newpublisher@gmail.com");
        publisherDao.addPublisher(publisher);

        List<Publisher> pList = publisherDao.getAllPublishers();

        assertEquals(pList.size(), 2);
    }

    @Test
    public void updatePublisher() {

        Publisher publisher = new Publisher();
        publisher.setName("Cannon Gate");
        publisher.setStreet("552 Waterview Cl");
        publisher.setCity("San Francisco");
        publisher.setState("California");
        publisher.setPostalCode("63001");
        publisher.setPhone("703-569-4578");
        publisher.setEmail("cannongpublisher@gmail.com");
        publisherDao.addPublisher(publisher);

        publisher.setName("New Publisher");
        publisher.setStreet("1545 New Street Way");
        publisher.setCity("Falls Church");
        publisher.setState("Virginia");
        publisher.setPostalCode("12540");
        publisher.setPhone("503-451-4547");
        publisher.setEmail("newpublisher1@gmail.com");
        publisherDao.updatePublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());

        assertEquals(publisher1, publisher);

    }
}
