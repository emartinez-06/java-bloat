package com.baylorcsi3372.labexam1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
    // Videogame Id is a Long
    // JPARepository gives us acces to findAll(), findById(), save(), deleteById(), and count()
    @Query("SELECT v FROM VideoGame v ORDER BY v.globalSales DESC")
    List<VideoGame> sortGlobalSales();

    List<VideoGame> findByNameContainingIgnoreCase(String name);

    @Query("SELECT new com.baylorcsi3372.labexam1.GenreStats(" +
            "v.genre, COUNT(v), AVG(v.globalSales)) " +
            "FROM VideoGame v GROUP BY v.genre " +
            "ORDER BY AVG(v.globalSales) DESC, v.genre ASC")
    List<GenreStats> findSalesByGenre();
}
