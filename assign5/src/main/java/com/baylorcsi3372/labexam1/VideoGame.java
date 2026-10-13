package com.baylorcsi3372.labexam1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.PostMapping;

@Entity
@Table(name = "video_game")
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull(message = "Rank is required")
    private Integer rank;
    @NotBlank(message = "Name is required")
    @Size(max = 50)
    private String name;
    @Size(max = 50)
    @NotBlank(message = "Platform is required")
    private String platform;
    @Min(1900) @Max(2050)
    @NotNull(message = "Release Year is required")
    private Integer releaseYear;
    @NotBlank(message = "Genre is required")
    @Size(max = 50)
    private String genre;
    @NotBlank(message = "Publisher is required")
    @Size(max = 50)
    private String publisher;
    @PositiveOrZero
    @NotNull(message = "NA Sales is required")
    private Double naSales;
    @PositiveOrZero
    @NotNull(message = "EU Sales is required")
    private Double euSales;
    @PositiveOrZero
    @NotNull(message = "JP Sales is required")
    private Double jpSales;
    @PositiveOrZero
    @NotNull(message = "Other Sales is required")
    private Double otherSales;
    @PositiveOrZero
    @NotNull(message = "Global Sales is required")
    private Double globalSales;

    // no-argument constructor is required for JPA
    protected VideoGame() {
    }
    // This constructor helps with the CSV loader
    public VideoGame(Integer rank, String name, String platform, Integer releaseYear,
                     String genre, String publisher, Double naSales, Double euSales,
                     Double jpSales, Double otherSales, Double globalSales) {
        this.rank = rank;
        this.name = name;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.publisher = publisher;
        this.naSales = naSales;
        this.euSales = euSales;
        this.jpSales = jpSales;
        this.otherSales = otherSales;
        this.globalSales = globalSales;
    }

    public void updateForm(VideoGame changes){
        this.rank = changes.rank;
        this.name = changes.name;
        this.platform = changes.platform;
        this.releaseYear = changes.releaseYear;
        this.genre = changes.genre;
        this.publisher = changes.publisher;
        this.naSales = changes.naSales;
        this.euSales = changes.euSales;
        this.jpSales = changes.jpSales;
        this.otherSales = changes.otherSales;
        this.globalSales = changes.globalSales;
    }

    public Long getId() {
        return id;
    }

    public Integer getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public Double getNaSales() {
        return naSales;
    }

    public Double getEuSales() {
        return euSales;
    }

    public Double getJpSales() {
        return jpSales;
    }

    public Double getOtherSales() {
        return otherSales;
    }

    public Double getGlobalSales() {
        return globalSales;
    }
}
