package com.example.movies_and_ratings.repository;

import com.example.movies_and_ratings.domains.Movies;
import com.example.movies_and_ratings.dto.MovieInput;
import com.example.movies_and_ratings.dto.TopRatedMoviesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies,Integer> {

    @Query(value = """
            SELECT tconst,primaryTitle,runtimeMinutes,genres
            FROM movies
            ORDER BY runtimeMinutes DESC
            LIMIT 10
            """,nativeQuery = true)
    public  List<Tuple> getLongestDurationMovies();


@Modifying
@Transactional
    @Query(value = "INSERT INTO movies (tconst, titleType, primaryTitle, runtimeMinutes, genres) " +
            "VALUES (:tconst, :titleType, :primaryTitle, :runtimeMinutes, :genres)",
            nativeQuery = true)

void saveMovie(@Param("tconst") String tconst, @Param("titleType") String titleType,
                   @Param("primaryTitle") String primaryTitle, @Param("runtimeMinutes") Integer runtimeMinutes,
                   @Param("genres") String genres);

@Query(value = """
        SELECT m.tconst AS tconst, m.primaryTitle AS primaryTitle, m.genres AS genres, r.averageRating AS averageRating
        FROM movies m
        INNER JOIN ratings r ON m.tconst = r.tconst
        WHERE r.averageRating > 6.0
        ORDER BY r.averageRating DESC
        """,nativeQuery = true)
    public List<Tuple> getTopRatedMovies();


    @Query(value = """ 
SELECT genres, primaryTitle, SUM(r.numVotes) AS numVotes FROM movies m
INNER JOIN ratings r ON m.tconst = r.tconst
   GROUP BY genres WITH ROLLUP """, nativeQuery = true)
  public List<Tuple> getGenreMoviesWithSubtotals();

    @Modifying
    @Transactional
    @Query(value = """
    UPDATE movies m 
    SET m.runtimeMinutes = CASE
    WHEN m.genres = 'Documentary' THEN m.runtimeMinutes + 15
    WHEN m.genres = 'Animation' THEN m.runtimeMinutes + 30
   ELSE m.runtimeMinutes + 45
   END; 
     """,nativeQuery = true)

    void updateRuntimeMinutes();


}
