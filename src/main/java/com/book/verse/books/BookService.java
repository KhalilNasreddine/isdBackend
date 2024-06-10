package com.book.verse.books;

import com.book.verse.user.User;
import com.book.verse.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book, int authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book updateBook(int id, Book updatedBook) {
        Book book = getBookById(id);
        book.setDescription(updatedBook.getDescription());
        book.setType(updatedBook.getType());
        book.setTitle(updatedBook.getTitle());
        book.setRating(book.getRating());
        book.setPrice(updatedBook.getPrice());
        book.setCoverImageUrl(updatedBook.getCoverImageUrl());
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public Book incrementSoldCopies(int id) {
        Book book = getBookById(id);
        book.setSoldCopies(book.getSoldCopies() + 1);
        return bookRepository.save(book);
    }
    public List<Book> getBooksByType(String type) {
        return bookRepository.findAllByType(type);
    }
    public List<Book> getBooksByAuthor(Integer id) {

        return bookRepository.findAllByAuthorId(id);
    }
    private List<Book> getBestSellers(int limit) {
        List<Book> books = bookRepository.findAll();
        books.sort((b1, b2) -> b2.getSoldCopies() - b1.getSoldCopies());
        return books.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Book> getTopBestSellers() {
        return getBestSellers(15);
    }

    private List<Book> getTopRatedBooks(int limit) {
        List<Book> books = bookRepository.findAll();
        books.sort(Comparator.comparing(Book::getRating).reversed());
        return books.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Book> getTopRatedBooks() {
        return getTopRatedBooks(15);
    }
    public List<Book> getNewestBooks(int numBooks) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(0, numBooks, sort);
        Page<Book> page = bookRepository.findAll(pageable);
        return page.getContent();
    }



}

/*
package com.book.verse.books;

import com.book.verse.user.User;
import com.book.verse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

//    @Autowired
//    private FileStorageService fileStorageService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }


    public Book createBook(Book book, Integer authorId*/
/*, MultipartFile coverImageFile*//*
) throws IOException {
        Optional<User> author = userRepository.findById(authorId);
        author.ifPresent(book::setAuthor);

        // Save the book first to get its ID

        */
/*if (coverImageFile != null && !coverImageFile.isEmpty()) {

            String coverImagePath = fileStorageService.saveCoverImage(coverImageFile, book.getTitle(), savedBook.getId());
            savedBook.setCoverImagePath(coverImagePath);
            return bookRepository.save(savedBook); // Save the book again with the cover image path
        }*//*

        return bookRepository.save(book);
    }

    public Optional<Book> findAllByAuthorId(Integer authorId) {
        return bookRepository.findAllByAuthorId(authorId);
    }

    public Optional<Book> updateBook(Integer id, Book bookDetails*/
/*, MultipartFile coverImageFile*//*
) throws IOException {
        return bookRepository.findById(id).map(book -> {
            book.setDescription(bookDetails.getDescription());
            book.setType(bookDetails.getType());
            book.setTitle(bookDetails.getTitle());
            book.setPrice(bookDetails.getPrice());
            book.setCoverImagePath(bookDetails.getCoverImagePath());
            */
/*if (coverImageFile != null && !coverImageFile.isEmpty()) {
                try {
                    String coverImagePath = fileStorageService.saveCoverImage(coverImageFile, book.getTitle(), book.getId());
                    book.setCoverImagePath(coverImagePath);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload cover image file", e);
                }
            }*//*

            return bookRepository.save(book);
        });
    }
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getBooksByType(String type) {
        return bookRepository.findByType(type);
    }
}
*/



/*public Book createBook(Book book, Integer authorId, *//*MultipartFile pdfFile,*//* MultipartFile coverImageFile) throws IOException {
        Optional<User> author = userRepository.findById(authorId);
        author.ifPresent(book::setAuthor);

        //book.setPdfFile(pdfFile.getBytes());
        book.setCoverImageFile(coverImageFile.getBytes());

        return bookRepository.save(book);
    }

    public Optional<Book> updateBook(Integer id, Book bookDetails, *//*MultipartFile pdfFile,*//* MultipartFile coverImageFile) throws IOException {
        return bookRepository.findById(id).map(book -> {
            book.setDescription(bookDetails.getDescription());
            book.setType(bookDetails.getType());
            book.setTitle(bookDetails.getTitle());
            book.setPrice(bookDetails.getPrice());

            *//*if (pdfFile != null && !pdfFile.isEmpty()) {
                try {
                    book.setPdfFile(pdfFile.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload PDF file", e);
                }
            }*//*

            if (coverImageFile != null && !coverImageFile.isEmpty()) {
                try {
                    book.setCoverImageFile(coverImageFile.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload cover image file", e);
                }
            }

            return bookRepository.save(book);
        });
        }*/



/*
package com.book.verse.books;


import com.book.verse.user.User;
import com.book.verse.user.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;



    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book, Integer authorId) {
        Optional<User> author = userRepository.findById(authorId);
        author.ifPresent(book::setAuthor);
        return bookRepository.save(book);
    }

    public Optional<Book> updateBook(Integer id, Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setDescription(bookDetails.getDescription());
            book.setType(bookDetails.getType());
            book.setTitle(bookDetails.getTitle());
            book.setPrice(bookDetails.getPrice());
            book.setCoverImageFile(bookDetails.getCoverImageFile());
            book.setPdfFile(bookDetails.getPdfFile());
            return bookRepository.save(book);
        });
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
    public Optional<Book> getBooksByType(String type) {
        return bookRepository.findByType(type);
    }
}*/
