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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

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
    public void addGetDeleteBook() {

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

        Publisher publisher = new Publisher();
        publisher.setName("Scholastic Press");
        publisher.setStreet("222 Way Rd");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11226");
        publisher.setPhone("703-707-936");
        publisher.setEmail("publisherSP@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("978-0-439");
        book.setPublishDate(LocalDate.of(2012, 05, 14));
        book.setAuthorId(author.getId());
        book.setTitle("The Hunger Games");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("12.99"));


        Book book1 = bookDao.addBook(book);

        assertEquals(book1, book);

        bookDao.deleteBook(book.getId());

        book1 = bookDao.getBook(book.getId());

        assertNull(book1);

    }

    @Test
    public void updateBook() {

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

        Publisher publisher = new Publisher();
        publisher.setName("Scholastic Press");
        publisher.setStreet("222 Way Rd");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11226");
        publisher.setPhone("703-707-936");
        publisher.setEmail("publisherSP@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("978-0-439");
        book.setPublishDate(LocalDate.of(2012, 05, 14));
        book.setAuthorId(author.getId());
        book.setTitle("The Hunger Games");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookDao.addBook(book);

        book.setTitle("NEW TITLE");

        bookDao.updateBook(book);

        Book book1 = bookDao.getBook(book.getId());

        assertEquals(book1, book);
    }

    @Test
    public void getAllBooks() {

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

        Author author1 = new Author();
        author1.setFirstName("Paulo");
        author1.setLastName("Coelho");
        author1.setStreet("235 Fair St");
        author1.setCity("Florida City");
        author1.setState("FL");
        author1.setPostalCode("33225");
        author1.setPhone("571-623-123");
        author1.setEmail("pauloCoel@gmail.com");
        author1 = authorDao.addAuthor(author1);

        Publisher publisher = new Publisher();
        publisher.setName("Scholastic Press");
        publisher.setStreet("222 Way Rd");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11226");
        publisher.setPhone("703-707-936");
        publisher.setEmail("publisherSP@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Publisher publisher1 = new Publisher();
        publisher1.setName("Cannon Gate");
        publisher1.setStreet("567 Wing Way");
        publisher1.setCity("Richmond");
        publisher1.setState("VA");
        publisher1.setPostalCode("22003");
        publisher1.setPhone("302-154-4478");
        publisher1.setEmail("publisher1@gmail.com");
        publisher1 = publisherDao.addPublisher(publisher1);

        Book book = new Book();
        book.setIsbn("978-0-439");
        book.setPublishDate(LocalDate.of(2012, 05, 14));
        book.setAuthorId(author.getId());
        book.setTitle("The Hunger Games");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookDao.addBook(book);

        book = new Book();
        book.setIsbn("978-1-84195");
        book.setPublishDate(LocalDate.of(2016, 07, 22));
        book.setAuthorId(author.getId());
        book.setTitle("Weight");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("15.99"));
        book = bookDao.addBook(book);

        List<Book> bList = bookDao.getAllBooks();

        assertEquals(bList.size(), 2);
    }

    @Test
    public void getBookByAuthor() {

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

//        Author author1 = new Author();
//        author1.setFirstName("Paulo");
//        author1.setLastName("Coelho");
//        author1.setStreet("235 Fair St");
//        author1.setCity("Florida City");
//        author1.setState("FL");
//        author1.setPostalCode("33225");
//        author1.setPhone("571-623-123");
//        author1.setEmail("pauloCoel@gmail.com");
//        author1 = authorDao.addAuthor(author1);

        Publisher publisher = new Publisher();
        publisher.setName("Scholastic Press");
        publisher.setStreet("222 Way Rd");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11226");
        publisher.setPhone("703-707-936");
        publisher.setEmail("publisherSP@gmail.com");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setIsbn("978-0-439");
        book.setPublishDate(LocalDate.of(2012, 05, 14));
        book.setAuthorId(author.getId());
        book.setTitle("The Hunger Games");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("12.99"));
        book = bookDao.addBook(book);

        book = new Book();
        book.setIsbn("978-1-84195");
        book.setPublishDate(LocalDate.of(2016, 07, 22));
        book.setAuthorId(author.getId());
        book.setTitle("Weight");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("15.99"));
        book = bookDao.addBook(book);

        book = new Book();
        book.setIsbn("978-0-06");
        book.setPublishDate(LocalDate.of(2005, 12, 19));
        book.setAuthorId(author.getId());
        book.setTitle("The Alchemist");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("18.50"));
        book = bookDao.addBook(book);

        List<Book> bList = bookDao.getBookByAuthor(author.getId());
        assertEquals(bList.size(), 3);

//        bList = bookDao.getBookByAuthor(author1.getId());
//        assertEquals(bList.size(), 2);

    }
}
