package com.sda.practicalproject.service;

import com.sda.practicalproject.EntityNotFoundException;
import com.sda.practicalproject.dao.AuthorDao;
import com.sda.practicalproject.dao.BookDao;
import com.sda.practicalproject.entities.AuthorModel;

import java.util.List;

public class AuthorService {

    private AuthorDao authorDao;
    private BookDao bookDao;

    public AuthorService(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    public void addAuthor(AuthorModel newAuthor) throws InvalidNameOfAuthor {
        List<AuthorModel> authors = authorDao.getAllAuthors();
        for (AuthorModel author : authors) {
            if (author.getLastName().equals(newAuthor.getLastName())) {
                throw new InvalidNameOfAuthor("The name of the author already exists!");
            }
        }
        authorDao.addAuthors(newAuthor);
    }

    public List<AuthorModel> getAuthors() {
        List<AuthorModel> authors = authorDao.getAllAuthors();
        return authors;
    }

    public void deleteAuthor(int id) throws EntityNotFoundException {
        AuthorModel authorModel = authorDao.findById(id);
        if (authorModel == null) {
            throw new EntityNotFoundException();
        }
        authorDao.delete(authorModel);
    }

    public void editNameOfAuthor(int id, String newFirstName, String newLastName) throws EntityNotFoundException, InvalidNameOfAuthor {
        AuthorModel authorModel = authorDao.findById(id);
        if (authorModel == null) {
            throw new EntityNotFoundException();
        }

        authorModel.setFirstName(newFirstName);
        authorModel.setLastName(newLastName);

        authorDao.updateAuthor(authorModel);
    }
}
