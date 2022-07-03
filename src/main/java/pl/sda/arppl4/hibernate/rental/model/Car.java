package pl.sda.arppl4.hibernate.rental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
//    @ToString.Exclude
//      fetch type zamiast @toString.Exclude
    private Set<CarRental> carRentals;

    public Car(String nazwa, String marka, LocalDate localDate, int iloscPasazerow, Double pojemnoscSilnika, TypNadwozia typNadwozia, SkrzyniaBiegow skrzyniaBiegow) {
        this.nazwa = nazwa;
        this.marka = marka;
        this.localDate = localDate;
        this.iloscPasazerow = iloscPasazerow;
        this.pojemnoscSilnika = pojemnoscSilnika;
        this.typNadwozia = typNadwozia;
        this.skrzyniaBiegow = skrzyniaBiegow;
    }
}
