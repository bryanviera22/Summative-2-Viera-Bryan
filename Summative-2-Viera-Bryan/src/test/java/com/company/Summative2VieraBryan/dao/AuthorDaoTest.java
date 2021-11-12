package com.company.Summative2VieraBryan.dao;

import com.company.Summative2VieraBryan.dto.Author;
import com.company.Summative2VieraBryan.dto.Book;
import com.company.Summative2VieraBryan.dto.Publisher;
import org.junit.Before;
import org.junit.Ignore;
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
public class AuthorDaoTest {

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
    public void addGetDeleteAuthor() {

        Author author = new Author();
        author.setFirstName("Suzanne");
        author.setLastName("Collins");
        author.setStreet("123 Main St");
        author.setCity("New York City");
        author.setState("NY");
        author.setPostalCode("22123");
        author.setPhone("571-555-333");
        author.setEmail("suzanneColl@gmail.com");
        author = authorDao.addAuthor(author);

        Author author1 = authorDao.getAuthor(author.getId());

        assertEquals(author1, author);

        authorDao.deleteAuthor(author.getId());

        author1 = authorDao.getAuthor(author.getId());

        assertNull(author1);

    }

    @Test
    public void updateAuthor() {

        Author author = new Author();
        author.setFirstName("Suzanne");
        author.setLastName("Collins");
        author.setStreet("123 Main St");
        author.setCity("New York City");
        author.setState("NY");
        author.setPostalCode("22123");
        author.setPhone("571-555-333");
        author.setEmail("suzanneColl@gmail.com");
        author = authorDao.addAuthor(author);

        author.setFirstName("Paulo");
        author.setLastName("Coelho");
        author.setStreet("2285 Spring rd");
        author.setCity("Herndon");
        author.setState("VA");
        author.setPostalCode("19050");
        author.setPhone("571-578-695");
        author.setEmail("pauloCo@gmail.com");
        authorDao.updateAuthor(author);

        Author author1 = authorDao.getAuthor(author.getId());

        assertEquals(author1, author);

    }

    @Test
    public void getAllAuthors() {

        Author author = new Author();
        author.setFirstName("Suzanne");
        author.setLastName("Collins");
        author.setStreet("123 Main St");
        author.setCity("New York City");
        author.setState("NY");
        author.setPostalCode("22123");
        author.setPhone("571-555-333");
        author.setEmail("suzanneColl@gmail.com");
        author = authorDao.addAuthor(author);

        author = new Author();
        author.setFirstName("Jeanette");
        author.setLastName("Winterson");
        author.setStreet("523 Saint Peter St");
        author.setCity("San Antonio");
        author.setState("TX");
        author.setPostalCode("56977");
        author.setPhone("709-356-4564");
        author.setEmail("jeanw@gmail.com");
        author = authorDao.addAuthor(author);

        List<Author> aList = authorDao.getAllAuthors();
        assertEquals(aList.size(), 2);

    }
}
