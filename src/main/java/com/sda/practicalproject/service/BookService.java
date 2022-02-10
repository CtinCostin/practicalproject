package com.sda.practicalproject.service;

import com.sda.practicalproject.EntityNotFoundException;
import com.sda.practicalproject.InvalidBookTitleException;
import com.sda.practicalproject.dao.AuthorDao;
import com.sda.practicalproject.dao.BookDao;
import com.sda.practicalproject.entities.AuthorModel;
import com.sda.practicalproject.entities.BookModel;

import java.util.List;

public class BookService {

    private BookDao bookDao;
    private AuthorDao authorDao;

    public BookService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public void addBook(BookModel newBook) throws InvalidBookTitleException {
        List<BookModel> books = bookDao.getAllBooks();
        for (BookModel book : books) {
            if (book.getTitle().equals(newBook.getTitle())) {
                throw new InvalidBookTitleException();
            }
        }
        bookDao.addBook(newBook);
    }

    public List<BookModel> getBooks() {
        List<BookModel> books = bookDao.getAllBooks();
        return books;
    }

    public void editNameOfBook(int id, String newNameOfBook) throws EntityNotFoundException {
        BookModel bookModel = bookDao.findById(id);
        if (bookModel == null) {
            throw new EntityNotFoundException();
        }
        bookModel.setTitle(newNameOfBook);
        bookDao.updateBook(bookModel);
    }

    public void deleteBook(int id) throws EntityNotFoundException {
        BookModel bookModel = bookDao.findById(id);
        if (bookModel == null) {
            throw new EntityNotFoundException();
        }
        bookDao.deleteBook(bookModel);
    }

    public void assignBookToAnAuthor(int bookId, int authorId) throws EntityNotFoundException {
        AuthorModel authorModel = authorDao.findById(authorId);
        if (authorModel == null) {
            throw new EntityNotFoundException();
        }
        BookModel bookModel = bookDao.findById(bookId);
        if (bookModel == null) {
            throw new EntityNotFoundException();
        }
        bookModel.setAuthor(authorModel);
        bookDao.updateBook(bookModel);
    }




}
