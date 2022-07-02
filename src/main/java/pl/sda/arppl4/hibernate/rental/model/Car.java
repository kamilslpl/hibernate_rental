package pl.sda.arppl4.hibernate.rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Car {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
        private Long id;

    private String nazwa;
    private String marka;

    private LocalDate localDate;
    private int iloscPasazerow;
    private Double pojemnoscSilnika;
    @Enumerated(EnumType.STRING)
    private TypNadwozia typNadwozia;
    @Enumerated(EnumType.STRING)
    private SkrzyniaBiegow skrzyniaBiegow;


}
