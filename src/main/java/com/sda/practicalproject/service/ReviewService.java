package com.sda.practicalproject.service;

import com.sda.practicalproject.EntityNotFoundException;
import com.sda.practicalproject.dao.BookDao;
import com.sda.practicalproject.dao.ReviewDao;
import com.sda.practicalproject.entities.BookModel;
import com.sda.practicalproject.entities.ReviewsModel;

import java.util.List;

public class ReviewService {

    private ReviewDao reviewDao;

    private BookDao bookDao;

    public ReviewService(ReviewDao reviewDao, BookDao bookDao) {
        this.reviewDao = reviewDao;
        this.bookDao = bookDao;
    }

    public void addNewReview(ReviewsModel newReview) {
        List<ReviewsModel> reviews = reviewDao.getReviews();
        for (ReviewsModel review : reviews) {
            reviewDao.addReview(newReview);
        }
    }

    public List<ReviewsModel> getAllReviews() {
        List<ReviewsModel> reviews = reviewDao.getReviews();
        return reviews;
    }

    public void assignReviewToBook(int bookId, ReviewsModel review) throws EntityNotFoundException {
        BookModel bookModel = bookDao.findById(bookId);
        if (bookModel == null) {
            throw new EntityNotFoundException();
        }
        ReviewsModel reviewsModel = reviewDao.addReview(review);
        reviewsModel.setBook(bookModel);
        reviewDao.updateReview(reviewsModel);
    }

    public void updateReview(int reviewId, String newComment, double newScore){
        ReviewsModel reviewModel = reviewDao.findById(reviewId);
        reviewModel.setComment(newComment);
        reviewModel.setScore(newScore);
        reviewDao.updateReview(reviewModel);
    }
}
