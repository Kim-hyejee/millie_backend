package com.millie.app.repository;

import com.millie.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * 제목으로 도서 검색 (대소문자 구분 없음)
     */
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Book> findByTitleContainingIgnoreCase(@Param("title") String title);

    /**
     * 저자로 도서 검색 (대소문자 구분 없음)
     */
    @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<Book> findByAuthorContainingIgnoreCase(@Param("author") String author);

    /**
     * ISBN으로 도서 검색 (대소문자 구분 없음)
     */
    @Query("SELECT b FROM Book b WHERE LOWER(b.isbn) LIKE LOWER(CONCAT('%', :isbn, '%'))")
    List<Book> findByIsbnContainingIgnoreCase(@Param("isbn") String isbn);

    /**
     * 통합 검색 (제목, 저자, ISBN에서 검색)
     */
    @Query("SELECT b FROM Book b WHERE " +
           "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> findByKeywordContainingIgnoreCase(@Param("keyword") String keyword);
}
