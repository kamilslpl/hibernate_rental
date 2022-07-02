package pl.sda.arppl4.hibernate.rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private TypNadwozia typ_nadwozia;

    private LocalDate localDate;
    private int ilosc_pasazerow;
    private Double pojemnosc_silnika;
    private SkrzyniaBiegow skrzyniaBiegow;

}
