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

    @Query(
            value =" SELECT IMDB_ID, TITLE,  BOX_OFFICE, AVG(CAST(RATE as double)) as AVG_RATE " +
                    "    FROM user_rate " +
                    "    GROUP BY IMDB_ID" +
                    "     , BOX_OFFICE , title" +
                    "    HAVING AVG(CAST(rate as double))>0 " +
                    "    ORDER BY  AVG(rate) DESC,  BOX_OFFICE DESC, BOX_OFFICE DESC limit 10",
            nativeQuery = true)
    List<Object[]> findTopTenOrderedByBoxOffice();
}