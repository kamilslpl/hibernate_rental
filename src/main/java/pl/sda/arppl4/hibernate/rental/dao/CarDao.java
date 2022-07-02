package pl.sda.arppl4.hibernate.rental.dao;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.sda.arppl4.hibernate.rental.model.Car;
import pl.sda.arppl4.hibernate.rental.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDao implements ICarDao {

    @Override
    public void dodajCar(Car car) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        // Try with resources
        try (Session session = fabrykaPolaczen.openSession()) {

            transaction = session.beginTransaction();

            session.merge(car);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null){
                transaction.rollback();
            }
        }


    }

    @Override
    public void usunCar(Car car) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()){
            Transaction transaction = session.beginTransaction();

            session.remove(car);

            transaction.commit();
        }
    }

    @Override
    public Optional<Car> zwrocCar(Long id) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            Car obiektCar = session.get(Car.class, id);

            return Optional.ofNullable(obiektCar);
        }
    }

    @Override
    public List<Car> zwrocListeCar() {
        List<Car> carList = new ArrayList<>();

        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();
        try (Session session = fabrykaPolaczen.openSession()) {
            // Tworzymy "zapytanie" do bazy o obiekty typu Student
            TypedQuery<Car> zapytanie = session.createQuery("from Car", Car.class);
            List<Car> wynikZapytania = zapytanie.getResultList();

            carList.addAll(wynikZapytania);
        } catch (SessionException sessionException) {
            System.err.println("Błąd wczytywania danych.");
        }

        return carList;
    }

    @Override
    public void updateCar(Car car) {
        SessionFactory fabrykaPolaczen = HibernateUtil.getSessionFactory();

        Transaction transaction = null;
        try (Session session = fabrykaPolaczen.openSession()) {
            transaction = session.beginTransaction();

            session.merge(car);

            transaction.commit();
        } catch (SessionException sessionException) {
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

}
