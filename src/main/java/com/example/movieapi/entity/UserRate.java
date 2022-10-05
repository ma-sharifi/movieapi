package com.example.movieapi.entity;

import lombok.*;

import javax.persistence.*;
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
@Getter @Setter @ToString
public class UserRate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "RATE")
    private Integer rate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BOX_OFFICE")
    private BigDecimal boxOffice;

    @Column(name = "CREATED_AT")
    private LocalDateTime create;

    @PrePersist
    public void prePersist() {
        this.create = LocalDateTime.now();
    }

}
