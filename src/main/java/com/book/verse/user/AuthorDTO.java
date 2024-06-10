package com.book.verse.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
public class AuthorDTO {
    private Integer id;
    private String fullName;
    private String profileImage;
}