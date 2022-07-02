package pl.sda.arppl4.hibernate.rental.dao;



    import pl.sda.arppl4.hibernate.rental.model.Car;

    import java.util.List;

import java.util.Optional;
    public interface ICarDao {

        // Create
        public void dodajCar(Car car);
        // Delete
        public void usunCar(Car car);
        // Read
        public Optional<Car> zwrocCar(Long id);
        // Read (select * from student)
        public List<Car> zwrocListeCar();

        public void updateCar(Car car);
    }

