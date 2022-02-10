package com.sda.practicalproject.ui;

import com.sda.practicalproject.EntityNotFoundException;
import com.sda.practicalproject.entities.BookModel;
import com.sda.practicalproject.entities.ReviewsModel;
import com.sda.practicalproject.service.BookService;
import com.sda.practicalproject.service.ReviewService;

import java.util.List;
import java.util.Scanner;

public class PracticalProjectReviewUI {

    private ReviewService reviewService;
    private BookService bookService;

    Scanner scanner = new Scanner(System.in);

    public PracticalProjectReviewUI(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    public void startUI() {
        System.out.println("Review Management");
        int option = -1;
        while (option != 0) {
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                try {
                    addReview();
                } catch (EntityNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (option == 2) {
                viewAllReviews();
            }
            if (option == 3) {
                updateReview();
            }


        }
    }

    private void viewAllBooksUI() {
        List<BookModel> books = bookService.getBooks();
        for (BookModel book : books) {
            System.out.println(book.getId() + "." + book.getTitle() + " - " + book.getDescription() + " -> " + book.getAuthor().getFirstName() +
                    " " + book.getAuthor().getLastName());
        }
    }

    public void printMenu() {
        System.out.println("----------------------");
        System.out.println("0.Exit");
        System.out.println("1.Add a review");
        System.out.println("2.View all reviews");
        System.out.println("3.Update a review");
        System.out.println("----------------------");
    }

    public void addReview() throws EntityNotFoundException {
        viewAllBooksUI();
        System.out.println("Enter the id of the book witch you want to write a review:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the review for the chosen book:");
        String comment = scanner.nextLine();
        System.out.println("Enter the score:");
        double score = scanner.nextDouble();
        scanner.nextLine();
        ReviewsModel reviewsModel = new ReviewsModel();
        reviewsModel.setComment(comment);
        reviewsModel.setScore(score);
        reviewService.addNewReview(reviewsModel);
        reviewService.assignReviewToBook(bookId, reviewsModel);

    }

    public void viewAllReviews() {
        List<ReviewsModel> reviews = reviewService.getAllReviews();
        for (ReviewsModel review : reviews) {
            System.out.println(review.getId() + "." + " " + review.getComment() + " score " + review.getScore());
        }
    }

    public void updateReview() {
        System.out.println("Enter review id:");
        int reviewId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new comment:");
        String newComment = scanner.nextLine();
        System.out.println("Enter new score:");
        double newScore = scanner.nextDouble();
        scanner.nextLine();
        reviewService.updateReview(reviewId, newComment, newScore);

    }


}

