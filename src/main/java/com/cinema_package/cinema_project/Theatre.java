package com.cinema_package.cinema_project;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table
public class Theatre {
    @Id
    @SequenceGenerator(
            name = "theatre_id_sequence",
            sequenceName = "theatre_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "theatre_id_sequence"
    )
    private int id;
    private String name;
    private String description;
    private LocalDate date;
    private String address;
    private int totalSeats;
    private int availableSeats;

    public Theatre(int id, String name, String description, LocalDate date,
                   String address, int totalSeats, int availableSeats, int price) {
            this.id = id;
            this.description = description;
            this.date = date;
            this.totalSeats = totalSeats;
            this.availableSeats = availableSeats;
        }

    public Theatre() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public int getTotalSeats() {return totalSeats;}

    public void setTotalSeats(int totalSeats) {this.totalSeats = totalSeats;}

    public int getAvailableSeats() {return availableSeats;}

    public void setAvailableSeats(int availableSeats) {this.availableSeats = availableSeats;}
}
