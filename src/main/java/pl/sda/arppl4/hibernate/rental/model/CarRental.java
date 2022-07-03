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

public class CarRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientSurname;
    private LocalDateTime  rentDataTime;
    private LocalDateTime  returnDataTime;

    @ManyToOne
    private Car car;

    public CarRental(String clientName, String clientSurname, LocalDateTime rentDataTime, Car car) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.rentDataTime = rentDataTime;
        this.car = car;
    }
}
