package com.inixindo.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.inixindo.library.model.Books;
import com.inixindo.library.model.Loans;

public interface BookRepository extends JpaRepository<Books, Integer> {
	//List<Books> findAll();
	@Modifying
	@Query
		(value = "select * from books where no_of_copies != 0", nativeQuery = true)
	List<Books> getAvailableBook();
	
	@Transactional
	@Modifying
	@Query
	(value ="update books set no_of_copies = no_of_copies - 1 where bookid = ?1",nativeQuery = true)
	void minStock(int id);
	
	@Transactional
	@Modifying
	@Query
	(value ="update books set no_of_copies = no_of_copies + 1 where bookid = ?1",nativeQuery = true)
	void returnStock(int id);
	
	@Modifying
	@Query(value = "SELECT * "
			+ "FROM books b "
			+ "inner join authors a on (a.authorid = b.author_id) "
			+ "inner join publisher p on (p.publisherid = b.publisher_id) "
			+ "WHERE (b.bookid LIKE %?1% "
			+ "OR b.title LIKE %?1% "
			+ "OR a.author_name LIKE %?1% "
			+ "OR b.no_of_copies LIKE %?1% "
			+ "OR p.name LIKE %?1%)", nativeQuery = true)

//	(value="SELECT * FROM books WHERE CONCAT(bookid, title, no_of_copies)LIKE %?1% ",nativeQuery = true)
	List<Books> search(String keyword);
}
