package com.baylorcsi3372.exam_two_backend;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class VideoGameController {

    private final VideoGameRepository videoGameRepository;

    public VideoGameController(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    @GetMapping("/initialize")
    public List<VideoGame> initialize() throws IOException {
        List<VideoGame> videoGames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("video_game_sales.csv"),
                StandardCharsets.UTF_8))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);
                videoGames.add(new VideoGame(
                        Integer.valueOf(values[0]),
                        values[1],
                        values[2],
                        Integer.valueOf(values[3]),
                        values[4],
                        values[5],
                        Double.valueOf(values[6]),
                        Double.valueOf(values[7]),
                        Double.valueOf(values[8]),
                        Double.valueOf(values[9]),
                        Double.valueOf(values[10])));
            }
        }

        videoGameRepository.deleteAllInBatch();
        return videoGameRepository.saveAll(videoGames);
    }

    @GetMapping("/top_games")
    public List<VideoGame> getTopGames(@RequestParam("count") Integer number) {
        List<VideoGame> sortedGames= videoGameRepository.sortGlobalSales();

        List<VideoGame> topGames = new ArrayList<>();

        for(int i=0; i < number && i < sortedGames.size(); i++){
            topGames.add(sortedGames.get(i));
        }
        return topGames;
    }

    @GetMapping("/videogame/search")
    public List<VideoGameRepository.ConciseGame> searchVideoGames(@RequestParam String name){
        List<VideoGameRepository.ConciseGame> filteredGames;

        // returns onlt the id and name of each mathcing object
        filteredGames = videoGameRepository.findByNameContainingIgnoreCase(name).stream()
        .map(game -> new VideoGameRepository.ConciseGame(game.getId(), game.getName()))
        .toList();

        // sorts by name ASC 

        return filteredGames;
    }


    @PostMapping("/api/videogame")
    public VideoGame createGame(@Valid @RequestBody VideoGame payload){
        VideoGame createdGame = new VideoGame(
            payload.getRank(),
            payload.getName(),
            payload.getPlatform(),
            payload.getReleaseYear(),
            payload.getGenre(),
            payload.getPublisher(),
            payload.getNaSales(),
            payload.getEuSales(),
            payload.getJpSales(),
            payload.getOtherSales(),
            payload.getGlobalSales()
        );

        return createdGame;
    }

    @GetMappign("api/videogame/{id}")
    public ResponseEntity<VideoGame> searchbyID(@PathVariable Long id){
        VideoGame game = videoGameRepository.findById(id).orElse(null);

        if(game == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

}
