package com.manifestcorp.techreads.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String author;
    String rating;
    String cover;
    public Book() {}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(rating, book.rating) && Objects.equals(cover, book.cover);
    }


    public Book(String title) {
        this.cover=cover;
        this.id=id;
        this.title = title;
        this.author= author;
        this.rating= rating;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    public String getCover() { return cover; }
    public void setCover(String cover) { this.cover = cover; }

}
