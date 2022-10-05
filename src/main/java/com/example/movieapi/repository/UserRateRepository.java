package com.example.movieapi.repository;

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
public interface UserRateRepository extends CrudRepository<UserRate, Long> {
    Optional<UserRate> findByTitleAndUsername(String title, String username);

    @Query("SELECT ur.title,ur.boxOffice,avg (ur.rate) FROM UserRate AS ur " +
            "GROUP BY ur.title,ur.boxOffice HAVING avg (ur.rate) = 10 ORDER BY avg (ur.rate) DESC,ur.boxOffice DESC")
    List<Object[]> topTenMovieOrderedByBoxOffice(Pageable pageable);
}