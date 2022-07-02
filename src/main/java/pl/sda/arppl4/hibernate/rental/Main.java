package pl.sda.arppl4.hibernate.rental;

import pl.sda.arppl4.hibernate.rental.PArser.ProductCommandLineParser;
import pl.sda.arppl4.hibernate.rental.dao.CarDao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarDao carDao = new CarDao();

        ProductCommandLineParser parser = new ProductCommandLineParser(scanner, carDao);
        parser.parse();
    }
}
