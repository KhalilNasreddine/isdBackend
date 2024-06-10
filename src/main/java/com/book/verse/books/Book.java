package com.book.verse.books;

import com.book.verse.user.AuthorDTO;
import com.book.verse.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "books")
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String type;
    private String title;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User author;
    @Transient
    private AuthorDTO authorDTO;
    private String coverImageUrl;
    private int soldCopies = 0;
    private double rating;
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate=LocalDate.now();
    public AuthorDTO getAuthorDTO() {
        if (author != null) {
            return AuthorDTO.builder()
                    .id(author.getId())
                    .fullName(author.getFullName())
                    .build();
        }
        return null;
    }
}


/*
package com.book.verse.books;

import com.book.verse.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "books")
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private String type;

    private String title;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private User author;

    @Lob

    private byte[] pdfFile;

    @Lob
    @Column(length = 1000)
    private byte[] coverImageFile;
}









package com.book.verse.books;

import com.book.verse.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "books")
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String type;
    private String title;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private User author;

    @NotBlank
    @Pattern(regexp = "^.*\\.pdf$", message = "Cover image path must be a valid PDF file path")
    private Byte[] pdfPath;


    private Byte[] coverImagePath;
}*/
