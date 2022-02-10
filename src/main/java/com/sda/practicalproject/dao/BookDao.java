package com.sda.practicalproject.dao;

import com.sda.practicalproject.entities.AuthorModel;
import com.sda.practicalproject.entities.BookModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookDao {

    private SessionFactory sessionFactory;

    public BookDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addBook(BookModel book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    public List<BookModel> getAllBooks() {
        Session session = sessionFactory.openSession();
        List<BookModel> books = session.createQuery("from BookModel").getResultList();
        session.close();
        return books;
    }

    public BookModel findById(int id) {
        Session session = sessionFactory.openSession();
        BookModel bookModel = session.find(BookModel.class, id);
        session.close();
        return bookModel;
    }

    public void updateBook(BookModel bookModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(bookModel);
        transaction.commit();
        session.close();
    }

    public void deleteBook(BookModel bookModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(bookModel);
        transaction.commit();
        session.close();
    }

}
