package com.company.Summative2VieraBryan.dao;

import com.company.Summative2VieraBryan.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date, author_id, title, publisher_id, price) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id =?";

    private static final String SELECT_ALL_BOOKS_SQL =
            "select * from book";

    private static final String SELECT_BOOK_BY_AUTHOR_SLQ =
            "select * from book where author_id =?";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn =?, publish_date = ?, author_id =?, title = ?, publisher_id = ?, price = ? where book_id = ?";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    @Autowired
    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book addBook(Book book) {
        jdbcTemplate.update(
                INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        book.setId(id);

        return book;
    }

    @Override
    public Book getBook(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(
                SELECT_ALL_BOOKS_SQL,
                this::mapRowToBook);
    }


    @Override
    public List<Book> getBookByAuthor(int authorId){
        return jdbcTemplate.query(
                SELECT_BOOK_BY_AUTHOR_SLQ,
                this::mapRowToBook, authorId);
    }

    @Override
    public void updateBook(Book book) {

        jdbcTemplate.update(
                UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice(),
                book.getId());
    }

    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublishDate(rs.getDate("publish_date").toLocalDate());
        book.setAuthorId(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setPrice(rs.getBigDecimal("price"));

        return book;
    }

}
