package com.sda.practicalproject.ui;

import com.sda.practicalproject.EntityNotFoundException;
import com.sda.practicalproject.InvalidBookTitleException;
import com.sda.practicalproject.entities.AuthorModel;
import com.sda.practicalproject.entities.BookModel;
import com.sda.practicalproject.service.AuthorService;
import com.sda.practicalproject.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PracticalProjectBookUI {

    private BookService bookService;
    private AuthorService authorService;

    Scanner scanner = new Scanner(System.in);

    public PracticalProjectBookUI(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void startUI() {
        System.out.println("Book Management");
        int option = -1;
        while (option != 0) {
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addBookUI();
            }
            if (option == 2) {
                viewAllBooksUI();
            }
            if (option == 3) {
                editBookNameUI();
            }
            if (option == 4) {
                deleteBookUI();
            }
            if (option == 5) {
                assignBookToAnAuthorUI();
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

    private void assignBookToAnAuthorUI() {
        viewAllBooksUI();
        System.out.println("Enter the id of the book you want to assign to an author:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        viewAuthorsUI();
        System.out.println("Enter the id of the author who you want to assign book:");
        int authorId = scanner.nextInt();
        scanner.nextLine();
        try {
            bookService.assignBookToAnAuthor(bookId, authorId);
        } catch (EntityNotFoundException e) {
            System.out.println("Book id or author id are invalid!");
        }
    }


    private void viewAuthorsUI() {
        List<AuthorModel> authors = authorService.getAuthors();
        for (AuthorModel author : authors) {
            System.out.println(author.getId() + "." + author.getFirstName() + " " + author.getLastName());
        }
    }


    private void deleteBookUI() {
        viewAllBooksUI();
        System.out.println("Enter the id of the book you want to delete:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        try {
            bookService.deleteBook(bookId);
        } catch (EntityNotFoundException e) {
            System.out.println("The id of the book you want to delete - " + bookId + " - does not exist!");
        }
    }

    private void editBookNameUI() {
        viewAllBooksUI();
        System.out.println("Enter id of the book you want to change title:");
        int idOfBook = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new title of the book:");
        String newNameOfBook = scanner.nextLine();
        try {
            bookService.editNameOfBook(idOfBook, newNameOfBook);
        } catch (EntityNotFoundException e) {
            System.out.println("Id of the book does not exist!");
        }
    }


    private void addBookUI() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter book description:");
        String description = scanner.nextLine();
        BookModel bookModel = new BookModel();
        bookModel.setTitle(title);
        bookModel.setDescription(description);
        try {
            bookService.addBook(bookModel);
        } catch (InvalidBookTitleException e) {
            System.out.println("The book already exists!");
        }

    }

    public void printMenu() {
        System.out.println("--------------------------");
        System.out.println("0.Exit");
        System.out.println("1.Add book");
        System.out.println("2.Show books with authors");
        System.out.println("3.Edit book");
        System.out.println("4.Delete book");
        System.out.println("5.Assign book to an author");
        System.out.println("--------------------------");

    }

}