package com.cinema_package.cinema_project;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    private final TheatreRepository TheatreRepository;
    public TheatreService(TheatreRepository TheatreRepository) {
        this.TheatreRepository = TheatreRepository;
    }

    public List<Theatre> filterTheatres(String title, LocalDate date, String location, String genre) {
        List<Theatre> Theatres = TheatreRepository.findAll();
        List<Theatre> filteredTheatres = new ArrayList<>();

        for (Theatre Theatre : Theatres) {
            boolean match = true;

            if (date != null && !Theatre.getDate().isEqual(date)) {
                match = false;
            }
            if (location != null && !Theatre.getAddress().toLowerCase().contains(location.toLowerCase())) {
                match = false;
            }
            if (match) {
                filteredTheatres.add(Theatre);
            }
        }

        return filteredTheatres;
    }
    @GetMapping
    public List<Theatre> getAllTheatres(String title, LocalDate date, String location, String genre) {
        if (title == null && date == null && location == null && genre == null) {
            return TheatreRepository.findAll();
        } else {
            return filterTheatres(title, date, location, genre);
        }
    }

    @GetMapping("/{id}")
    public Theatre getTheatreById(@PathVariable("id") Integer id) {
        return TheatreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Theatre ID: " + id));
    }

    @PostMapping
    public void addTheatre(@RequestBody CinemaProjectApplication.NewTheatreRequest request) {
        Theatre Theatre = new Theatre();
        Theatre.setDescription(request.description());
        Theatre.setName(request.name());
        Theatre.setDate(request.date());
        Theatre.setAddress(request.address());
        Theatre.setTotalSeats(request.totalSeats());
        Theatre.setAvailableSeats(request.availableSeats());
        TheatreRepository.save(Theatre);
    }

        @DeleteMapping("{TheatreId}")
    public void deleteTheatre(@PathVariable("TheatreId") Integer id){
        Theatre Theatre = TheatreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Theatre ID: " + id));

        TheatreRepository.delete(Theatre);
    }

    @PutMapping("{TheatreId}")
    public void updateTheatre(@PathVariable("TheatreId") Integer id,
                            @RequestBody CinemaProjectApplication.NewTheatreRequest request){

        Theatre Theatre = TheatreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Theatre ID: " + id));

        Theatre.setDescription(request.description());
        Theatre.setName(request.name());
        Theatre.setDate(request.date());
        Theatre.setAddress(request.address());
        Theatre.setTotalSeats(request.totalSeats());
        Theatre.setAvailableSeats(request.availableSeats());
        TheatreRepository.save(Theatre);
    }
}