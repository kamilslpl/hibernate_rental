package pl.sda.arppl4.hibernate.rental.util;

        import org.hibernate.SessionFactory;
        import org.hibernate.boot.Metadata;
        import org.hibernate.boot.MetadataSources;
        import org.hibernate.boot.registry.StandardServiceRegistry;
        import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    public static final HibernateUtil INSTANCE = new HibernateUtil();

    public SessionFactory sessionFactory;

    private HibernateUtil() {
        loadConfiguration();
    }

    private void loadConfiguration() {
        // Załadowanie "Registry" jako kolekcji parametrów konfiguracyjnych do rejestru.
        // Stworzenie obiektu zawierającego zestaw ustawień.
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        // Stworzenie obiektu metadata - dane opisujące połączenie z bazą danych.
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();

        // Wykorzystujemy metadane do skonfigurowania/parametryzacji fabryki. Tworzymy fabrykę.
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}