package com.sda.practicalproject.entities;

import com.sda.practicalproject.entities.AuthorModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private AuthorModel author;

    @OneToMany(mappedBy = "book" , fetch = FetchType.EAGER)
    private List<ReviewsModel> reviews = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    public List<ReviewsModel> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewsModel> reviews) {
        this.reviews = reviews;
    }
}
