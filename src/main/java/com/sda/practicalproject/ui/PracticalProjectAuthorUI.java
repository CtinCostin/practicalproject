package com.sda.practicalproject.ui;

import com.sda.practicalproject.EntityNotFoundException;
import com.sda.practicalproject.entities.AuthorModel;
import com.sda.practicalproject.entities.BookModel;
import com.sda.practicalproject.service.AuthorService;
import com.sda.practicalproject.service.BookService;
import com.sda.practicalproject.service.InvalidNameOfAuthor;

import java.util.List;
import java.util.Scanner;

public class PracticalProjectAuthorUI {

    private AuthorService authorService;
    private BookService bookService;

    Scanner scanner = new Scanner(System.in);

    public PracticalProjectAuthorUI(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public void startUI() {
        System.out.println("Author Management");
        int option = -1;
        while (option != 0) {
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addAuthorUI();
            }
            if (option == 2) {
                viewAuthorsUI();
            }
            if (option == 3) {
                deleteAuthorUI();
            }
            if (option == 4) {
                updateAuthorUI();

            }
        }
    }

    private void updateAuthorUI() {
        viewAuthorsUI();
        System.out.println("Enter id of the author you want to update:");
        int idOfAuthor = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new first name of the author:");
        String newFirstName = scanner.nextLine();
        System.out.println("Enter the new last name of the author:");
        String newLastName = scanner.nextLine();
        try {
            authorService.editNameOfAuthor(idOfAuthor, newFirstName, newLastName);
        } catch (EntityNotFoundException e) {
            System.out.println("Id of the author does not exist!");
        } catch (InvalidNameOfAuthor exc) {
            System.out.println("Invalid name of the author!");
        }
    }

    private void deleteAuthorUI() {
        viewAuthorsUI();
        System.out.println("Enter id of the author you want to delete:");
        int idOfAuthor = scanner.nextInt();
        scanner.nextLine();
        try {
            authorService.deleteAuthor(idOfAuthor);
        } catch (EntityNotFoundException e) {
            System.out.println("The author with id:" + idOfAuthor + " does not exist!");
        }

    }

    private void viewAuthorsUI() {
        List<AuthorModel> authors = authorService.getAuthors();
        for (AuthorModel author : authors) {
            System.out.println(author.getId() + "." + author.getFirstName() + " " + author.getLastName());
        }
    }

    private void addAuthorUI() {
        System.out.println("Enter first name of the author:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name of the author:");
        String lastName = scanner.nextLine();
        AuthorModel authorModel = new AuthorModel();
        authorModel.setFirstName(firstName);
        authorModel.setLastName(lastName);
        try {
            authorService.addAuthor(authorModel);
        } catch (InvalidNameOfAuthor e) {
            System.out.println(e.getMessage());
        }
    }

    public void printMenu() {
        System.out.println("---------------------");
        System.out.println("0.Exit");
        System.out.println("1.Add author");
        System.out.println("2.View authors");
        System.out.println("3.Delete author");
        System.out.println("4.Update author");
        System.out.println("---------------------");
    }

}
