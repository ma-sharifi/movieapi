package com.example.movieapi.repository;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */

@Repository
public interface UserRateRepository extends CrudRepository<UserRate, UserMovieId> {

    /**
     * 10 top-rated movies ordered by box office value.
     */
    @Query("SELECT ur.id.imdbId, ur.title,ur.boxOffice,avg (ur.rate) FROM UserRate AS ur " +
            "GROUP BY ur.id.imdbId , ur.title,ur.boxOffice HAVING avg (ur.rate) > 0 ORDER BY avg (ur.rate) DESC,ur.boxOffice DESC")
    List<Object[]> findTopTenOrderedByBoxOffice();
}