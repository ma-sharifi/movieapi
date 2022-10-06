package com.example.movieapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
@Entity
@Table(name = "USER_RATE")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID" ,length = 10)
    @EmbeddedId
    private UserMovieId id;

    @Column(name = "RATE")
    private Integer rate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BOX_OFFICE")
    private Long boxOffice;

    public UserRate(UserMovieId id) {
        this.id = id;
    }

    //    @Column(name = "UPDATED_AT")
//    private LocalDateTime updatedAt;
//
//    @PrePersist @PreUpdate
//    public void prePersist() {
//        this.updatedAt = LocalDateTime.now();
//    }

}
