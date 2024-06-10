package com.book.verse.books;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book, @RequestParam int authorId) {
        Book createdBook = bookService.createBook(book, authorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
//test
    @GetMapping("/getByType/{type}")
    public ResponseEntity<List<Book>> getBooksByType(@PathVariable String type) {
        List<Book> books = bookService.getBooksByType(type);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/increment/{id}")
    public ResponseEntity<Book> incrementSoldCopies(@PathVariable int id) {
        Book updatedBook = bookService.incrementSoldCopies(id);
        return ResponseEntity.ok(updatedBook);
    }
    @GetMapping("/getByAuthor/{authorId}")
    public ResponseEntity<List<Book>> getByAuthorId(@PathVariable int authorId) {
        List<Book> books=bookService.getBooksByAuthor(authorId);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/getTopRated")
    public ResponseEntity<List<Book>> getTopRatedBooks() {
        List<Book> books=bookService.getTopRatedBooks();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/getBestSellers")
    public ResponseEntity<List<Book>> getBestSellers() {
        List<Book> books=bookService.getTopBestSellers();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/getNewest")
    public List<Book> getNewestBooks(int limit) {
        return bookService.getNewestBooks(limit);
    }
}




/*package com.book.verse.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookController {

    *//*@Autowired
    private FileStorageService fileStorageService;*//*
    @Autowired
    private BookService bookService;

    @GetMapping("/getAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String type,
            @RequestParam double price,
            @RequestParam Integer authorId,
            @RequestParam() *//*MultipartFile*//*String coverImageFile) throws IOException {

        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setType(type);
        book.setPrice(price);

        Book createdBook = bookService.createBook(book, authorId);
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String type,
            @RequestParam double price,
            @RequestParam(required = false) MultipartFile coverImageFile) throws IOException {

        Book bookDetails = new Book();
        bookDetails.setTitle(title);
        bookDetails.setDescription(description);
        bookDetails.setType(type);
        bookDetails.setPrice(price);

        Optional<Book> updatedBook = bookService.updateBook(id, bookDetails*//*, coverImageFile*//*);
        return updatedBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Book> getBooksByType(@PathVariable String type) {
        Optional<Book> book = bookService.getBooksByType(type);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    *//*@GetMapping("/get/{id}/cover-image")
    public ResponseEntity<Resource> getBookCoverImage(@PathVariable Integer id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        String coverImagePath = book.getCoverImagePath();
        Resource resource = fileStorageService.loadResourceAsResource(coverImagePath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }*//*

}*/








/*
package com.book.verse.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(
            @RequestParam("description") String description,
            @RequestParam("type") String type,
            @RequestParam("title") String title,
            @RequestParam("price") double price,
            @RequestParam("authorId") Integer authorId,
            //@RequestParam("pdfFile") MultipartFile pdfFile,
            @RequestParam("coverImageFile") MultipartFile coverImageFile) throws IOException {

        Book book = Book.builder()
                .description(description)
                .type(type)
                .title(title)
                .price(price)
                .build();

        Book createdBook = bookService.createBook(book, authorId*/
/*, pdfFile*//*
, coverImageFile);
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @RequestParam("description") String description,
            @RequestParam("type") String type,
            @RequestParam("title") String title,
            @RequestParam("price") double price,
            @RequestParam(value = "pdfFile", required = false) MultipartFile pdfFile,
            @RequestParam(value = "coverImageFile", required = false) MultipartFile coverImageFile) throws IOException {

        Book bookDetails = Book.builder()
                .description(description)
                .type(type)
                .title(title)
                .price(price)
                .build();

        Optional<Book> updatedBook = bookService.updateBook(id, bookDetails,*/
/* pdfFile,*//*
 coverImageFile);
        return updatedBook.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type")
    public ResponseEntity<List<Book>> getBooksByType(@RequestParam String type) {
        Optional<Book> books = bookService.getBooksByType(type);
        return books.map(bookList -> ResponseEntity.ok(List.of(bookList)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}*/
