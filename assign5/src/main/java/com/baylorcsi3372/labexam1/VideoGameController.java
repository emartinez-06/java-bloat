package com.baylorcsi3372.labexam1;

import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
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

    @PostMapping("/videogame")
    public VideoGame createVideoGame(@Valid @RequestBody VideoGame vg) {
        VideoGame videoGame = videoGameRepository.save(vg);
        return videoGame;
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

    @PutMapping("/videogame/{id}")
    public ResponseEntity<VideoGame> updateVideoGame(
        @PathVariable Long id,
        @Valid @RequestBody VideoGame changes){
        VideoGame existing = videoGameRepository.findById(id).orElse(null);

        if(existing == null){
            return ResponseEntity.notFound().build();
        }
        existing.updateForm(changes);
        return ResponseEntity.ok(videoGameRepository.save(existing));
    }

    @DeleteMapping("/videogame/{id}")
    public ResponseEntity<Void> deleteVideoGame(@PathVariable Long id){
        if(!videoGameRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        videoGameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }   
}

