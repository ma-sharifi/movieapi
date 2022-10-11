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
@Data
@Builder
@Cacheable(value = false)
public class UserRate implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRate)) {
            return false;
        }
        return getId() != null && getId().equals(((UserRate) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
