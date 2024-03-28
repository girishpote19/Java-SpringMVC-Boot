package DummyMvc.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import DummyMvc.Model.Book;


@Repository
public class BooksDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	// createOrUpdate - if id is present then it will update data or in case of new id it will save to DB
	@Transactional
	public void createBook(Book book) {
		this.hibernateTemplate.saveOrUpdate(book);
	}

	// getAllBooks
	public List<Book> getBooks() {
		List<Book> books = this.hibernateTemplate.loadAll(Book.class);
		//fetch all the record from DB using HB temp method and return it to Controller
		return books;
	}

	// delete book
	@Transactional
	public void deleteBook(int bid) {
		Book b = this.hibernateTemplate.load(Book.class, bid);
		this.hibernateTemplate.delete(b);
	}

	// get the single book
	public Book getBook(int bid) {
		return this.hibernateTemplate.get(Book.class, bid);
	}
}
