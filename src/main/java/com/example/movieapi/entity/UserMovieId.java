package com.example.movieapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserMovieId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "IMDB_ID" ,length = 10)
    private String imdbId; //tt1375666
    @Column(name = "USERNAME" ,length = 30)
    private String username;
}
