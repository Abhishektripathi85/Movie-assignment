package com.cinema_package.cinema_project;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Theatre")
public class TheatreController {

    private final TheatreService TheatreService;
    public TheatreController(TheatreService TheatreService) {
        this.TheatreService = TheatreService;
    }

    @GetMapping
    public List<Theatre> getTheatres(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String genre

    ) {return TheatreService.getAllTheatres(title, date, location, genre);}

    @GetMapping("/{id}")
    public Theatre getTheatreById(@PathVariable("id") Integer id) {
        return TheatreService.getTheatreById(id);
    }

    @PostMapping
    public void addTheatre(@RequestBody CinemaProjectApplication.NewTheatreRequest Theatre) {
        TheatreService.addTheatre(Theatre);
    }

    @DeleteMapping("/{TheatreId}")
    public void deleteTheatre(@PathVariable("TheatreId") Integer id) {
        TheatreService.deleteTheatre(id);
    }

    @PutMapping("/{TheatreId}")
    public void updateTheatre(@PathVariable("TheatreId") Integer id, @RequestBody CinemaProjectApplication.NewTheatreRequest Theatre) {
        TheatreService.updateTheatre(id, Theatre);
    }


}