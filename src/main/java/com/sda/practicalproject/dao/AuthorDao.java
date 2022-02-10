package com.sda.practicalproject.dao;

import com.sda.practicalproject.entities.AuthorModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AuthorDao {

    private SessionFactory sessionFactory;

    public AuthorDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addAuthors(AuthorModel author) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(author);
        transaction.commit();
        session.close();

    }

    public List<AuthorModel> getAllAuthors() {
        Session session = sessionFactory.openSession();
        List<AuthorModel> authors = session.createQuery("from AuthorModel").getResultList();
        session.close();
        return authors;
    }

    public AuthorModel findById(int id) {
        Session session = sessionFactory.openSession();
        AuthorModel authorModel = session.find(AuthorModel.class, id);
        session.close();
        return authorModel;
    }

    public void delete(AuthorModel authorModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(authorModel);
        transaction.commit();
        session.close();
    }

    public void updateAuthor(AuthorModel authorModel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(authorModel);
        transaction.commit();
        session.close();
    }



}
