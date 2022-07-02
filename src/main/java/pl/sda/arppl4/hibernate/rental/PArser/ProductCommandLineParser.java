package pl.sda.arppl4.hibernate.rental.PArser;

import pl.sda.arppl4.hibernate.rental.dao.CarDao;
import pl.sda.arppl4.hibernate.rental.model.Car;
import pl.sda.arppl4.hibernate.rental.model.SkrzyniaBiegow;
import pl.sda.arppl4.hibernate.rental.model.TypNadwozia;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


    public class ProductCommandLineParser {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        private final Scanner scanner;
        private final CarDao dao;

        public ProductCommandLineParser(Scanner scanner, CarDao dao) {
            this.scanner = scanner;
            this.dao = dao;
        }

        public void parse() {
            String command;
            do {
                System.out.println("Polecenia: [dodaj, lista, edytuj, usun, quit]");
                command = scanner.next();

                if (command.equalsIgnoreCase("dodaj")) {
                    handleAddCommand();
                } else if (command.equalsIgnoreCase("lista")) {
                    handleListCommand();
                } else if (command.equalsIgnoreCase("usun")) {
                    handleDeleteCommand();
                } else if (command.equalsIgnoreCase("edytuj")) {
                    handleUpdateCommand();
                }

            } while (!command.equals("quit"));
        }

        private void handleUpdateCommand() {
            System.out.println("Podaj id car do edycji:");
            Long id = scanner.nextLong();

            Optional<Car> carOptional = dao.zwrocCar(id);
            if (carOptional.isPresent()) {
                Car car = carOptional.get();

                System.out.println("Co robimy [nazwa, marka, typNadwozia, ilosc pasazerow, pojemnosc silnika, skrzynia biegow]");
                String output = scanner.next();
                switch (output) {
                    case "nazwa":
                        System.out.println("Podaj nazwe:");
                        String nazwa = scanner.next();

                        car.setNazwa(nazwa);
                        break;

                    case "marka":
                        System.out.println("Podaj marke:");
                        String marka = scanner.next();

                        car.setMarka(marka);
                        break;

                    case "typNadwozia":
                        System.out.println("Podaj typ Nadwozia: (SEDAN, CABRIO, SUV)");
                        TypNadwozia typNadwozia = TypNadwozia.valueOf(scanner.next());

                        car.setTypNadwozia(typNadwozia);
                        break;

                    case "ilosc pasazerow":
                        System.out.println("Podaj ilosc pasazerow:");
                        int iloscPasazerow = Integer.parseInt(scanner.next());

                        car.setIloscPasazerow(iloscPasazerow);
                        break;

                    case "skrzyniaBiegow":
                        System.out.println("Podaj typ skrzyni: (AUTO, MANUAL)");
                        SkrzyniaBiegow skrzyniaBiegow = SkrzyniaBiegow.valueOf((scanner.next()));

                        car.setSkrzyniaBiegow(skrzyniaBiegow);
                        break;

                    default:
                        System.out.println("Blad, powtorz.");
                }

                dao.updateCar(car);
                System.out.println("Car zaktualizowany.");
            } else {
                System.out.println("Car nie znaleziony");
            }
        }

        private void handleDeleteCommand() {
            System.out.println("Podaj id car ktory chcesz usunac:");
            Long id = scanner.nextLong();

            Optional<Car> carOptional = dao.zwrocCar(id);
            if (carOptional.isPresent()) {
                Car car = carOptional.get();
                dao.usunCar(car);

                System.out.println("Car usuniety");
            } else {
                System.out.println("Car nie znaleziono");
            }
        }

        private void handleListCommand() {
            List<Car> carList = dao.zwrocListeCar();
            for (Car car : carList) {
                System.out.println(car);
            }

            System.out.println();
        }

        private void handleAddCommand() {


            System.out.println("Podaj nazwe:");
            String nazwa = scanner.next();

            System.out.println("Podaj marke");
            String marka = scanner.next();

            LocalDate localDate = loadLocalDateFromUser();

            System.out.println("Podaj ilosc pasazerow:");
            int ilosc_pasazerow = Integer.parseInt(scanner.next());

            System.out.println("Podaj pojemnosc silnika: z ,");
            Double pojemnoscSilnika = scanner.nextDouble();

            TypNadwozia typNadwozia = loadTypNadwoziaFromUser();

            SkrzyniaBiegow skrzyniaBiegow = loadSkrzyniaBiegowFromUser();

            Car car = new Car(null,nazwa,marka,localDate,ilosc_pasazerow,pojemnoscSilnika,typNadwozia,skrzyniaBiegow);
            dao.dodajCar(car);
        }

        private SkrzyniaBiegow loadSkrzyniaBiegowFromUser() {
            SkrzyniaBiegow skrzyniaBiegow = null;
            do {
                try {
                    System.out.println("Podaj skrzynie biegow: (AUTO, MANUAL)");
                    skrzyniaBiegow = SkrzyniaBiegow.valueOf(scanner.next());

                    skrzyniaBiegow = SkrzyniaBiegow.valueOf(String.valueOf(skrzyniaBiegow));
                } catch (IllegalArgumentException iae) {
                    System.err.println("Zly typ, podaj poprawny");
                }
            } while (skrzyniaBiegow == null);
            return skrzyniaBiegow;

        }

        private TypNadwozia loadTypNadwoziaFromUser() {
            TypNadwozia typNadwozia = null;
            do {
                try {
                    System.out.println("Podaj typ nadwozia: (SEDAN, CABRIO, SUV)");
                    typNadwozia = TypNadwozia.valueOf(scanner.next());

                    typNadwozia = TypNadwozia.valueOf(String.valueOf(typNadwozia));
                } catch (IllegalArgumentException iae) {
                    System.err.println("Zly typ, podaj poprawny (SEDAN, CABRIO, SUV)");
                }
            } while (typNadwozia == null);
            return typNadwozia;
        }

        private LocalDate loadLocalDateFromUser() {
            LocalDate localDate = null;
            do {
                try {
                    System.out.println("Podaj date: yyyy-MM-dd");
                    String localDateString = scanner.next();

                    localDate = LocalDate.parse(localDateString, FORMATTER);

                    LocalDate today = LocalDate.now();
                    if (localDate.isBefore(today)) {

                        throw new IllegalArgumentException("To juz historia, podaj prawidlowa date.");
                    }

                } catch (IllegalArgumentException | DateTimeException iae) {
                    localDate = null;
                    System.err.println("Zla data, podaj prawidlowa date: yyyy-MM-dd");
                }
            } while (localDate == null);
            return localDate;
        }
    }