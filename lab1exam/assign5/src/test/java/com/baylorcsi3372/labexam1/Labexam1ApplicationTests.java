package com.baylorcsi3372.labexam1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class Labexam1ApplicationTests {

	@Autowired
	private VideoGameController controller;

	@Autowired
	private VideoGameRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void deleteReturnsTheRemovedGame() {
		VideoGame game = repository.save(new VideoGame(201, "Delete Test", "PC", 2025,
				"Action", "Test Studio", 1.0, 0.2, 0.1, 0.2, 1.5));

		var response = controller.deleteVideoGame(game.getId());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(game.getId(), response.getBody().getId());
		assertEquals("Delete Test", response.getBody().getName());
		assertFalse(repository.existsById(game.getId()));
	}

	@Test
	void salesByGenreGroupsAndSortsAllGames() {
		repository.deleteAllInBatch();
		assertEquals(0, controller.salesByGenre().size());

		repository.saveAll(List.of(
				new VideoGame(1, "A1", "PC", 2025, "Action", "Studio", 0.0, 0.0, 0.0, 0.0, 2.0),
				new VideoGame(2, "A2", "PC", 2025, "Action", "Studio", 0.0, 0.0, 0.0, 0.0, 4.0),
				new VideoGame(3, "B1", "PC", 2025, "Adventure", "Studio", 0.0, 0.0, 0.0, 0.0, 3.0),
				new VideoGame(4, "C1", "PC", 2025, "Puzzle", "Studio", 0.0, 0.0, 0.0, 0.0, 1.0)));

		assertEquals(List.of(
				new GenreStats("Action", 2L, 3.0),
				new GenreStats("Adventure", 1L, 3.0),
				new GenreStats("Puzzle", 1L, 1.0)), controller.salesByGenre());
	}

}
