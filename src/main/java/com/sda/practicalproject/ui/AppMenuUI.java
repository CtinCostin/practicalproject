package com.sda.practicalproject.ui;

import java.util.Scanner;

public class AppMenuUI {

    Scanner scanner = new Scanner(System.in);

    private PracticalProjectAuthorUI practicalProjectAuthorUI;

    private PracticalProjectBookUI practicalProjectBookUI;

    private PracticalProjectReviewUI practicalProjectReviewUI;

    public AppMenuUI(PracticalProjectAuthorUI practicalProjectAuthorUI, PracticalProjectBookUI practicalProjectBookUI,
                     PracticalProjectReviewUI practicalProjectReviewUI1) {
        this.practicalProjectAuthorUI = practicalProjectAuthorUI;
        this.practicalProjectBookUI = practicalProjectBookUI;
        this.practicalProjectReviewUI = practicalProjectReviewUI1;
    }

    public void startApp() {
        System.out.println("Book Management App");
        int option = -1;
        while (option != 0) {
            System.out.println("0.Exit");
            System.out.println("1.Author Management");
            System.out.println("2.Book Management");
            System.out.println("3.Review Management");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                practicalProjectAuthorUI.startUI();
            }
            if(option == 2){
                practicalProjectBookUI.startUI();
            }
            if(option == 3){
                practicalProjectReviewUI.startUI();
            }
        }
    }
}
