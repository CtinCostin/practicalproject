package com.sda.practicalproject.dao;

import com.sda.practicalproject.entities.ReviewsModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ReviewDao {

    private SessionFactory sessionFactory;

    public ReviewDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ReviewsModel addReview(ReviewsModel review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(review);
        transaction.commit();
        session.close();
        return review;
    }

    public List<ReviewsModel> getReviews() {
        Session session = sessionFactory.openSession();
        List<ReviewsModel> reviews = session.createQuery("from ReviewsModel").getResultList();
        session.close();
        return reviews;
    }

    public ReviewsModel findById(int id) {
        Session session = sessionFactory.openSession();
        ReviewsModel reviews = session.find(ReviewsModel.class, id);
        session.close();
        return reviews;
    }

    public void updateReview(ReviewsModel review){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(review);
        transaction.commit();
        session.close();
    }

    public void deleteReview(ReviewsModel review){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(review);
        transaction.commit();
        session.close();
    }
}
