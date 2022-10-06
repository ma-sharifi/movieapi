package com.example.movieapi.repository;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */

@Repository
public interface UserRateRepository extends CrudRepository<UserRate, UserMovieId> {

    @Query("SELECT ur.id.imdbId , ur.title,ur.boxOffice ,avg (ur.rate), ur.rate,ur.id.username FROM UserRate AS ur " +
            "GROUP BY ur.title,ur.boxOffice HAVING avg (ur.rate) > 0 ORDER BY avg (ur.rate) DESC,ur.boxOffice DESC")
    List<Object[]> findTopTenOrderedByBoxOffice(Pageable pageable);
}