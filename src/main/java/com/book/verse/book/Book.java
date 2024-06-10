/*
package com.book.verse.book;

import com.book.verse.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    //@JsonIgnore
    private User author;

    @Lob
    private Byte[] pdfPath;

    @Lob
    private Byte[] coverImagePath;
}*/
