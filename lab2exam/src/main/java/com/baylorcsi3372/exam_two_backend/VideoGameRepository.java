package com.baylorcsi3372.exam_two_backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
    // Videogame Id is a Long
    // JPARepository gives us acces to findAll(), findById(), save(), deleteById(), and count()
    @Query("SELECT v FROM VideoGame v ORDER BY v.globalSales DESC")
    List<VideoGame> sortGlobalSales();

    List<VideoGame> findByNameContainingIgnoreCase(String name);

    public record ConciseGame(Long id, String name) {}

}
