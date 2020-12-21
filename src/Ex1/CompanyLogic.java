package Ex1;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CompanyLogic {
    private final static int EXIT = 0;
    private final static int INSERT_DATA = 1;
    private final static int READ_DATA = 2;

    private int option = -1;
    private boolean error = true;

    String fileName = "EmployeesList.txt";
    File file = new File(fileName);
    Scanner scanner = new Scanner(System.in);


    public void startProgramme(){
        checkFile();
        chooseOption();
    }

    private void checkFile() {              //metoda dopisana dla powtórzenia
        boolean fileExists = file.exists();
        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Nie udało się utworzyć pliku");
            }
        }
    }

    private void showOptions(){
        System.out.println("Plik " + fileName + " istnieje / lub udało się go utworzyć");       //informacja powtórkowa

        System.out.println("Wybierz akcję");
        System.out.println(EXIT + " - wyjście z programu");
        System.out.println(INSERT_DATA + " - wczytanie danych od użytkownika oraz zapis do pliku");
        System.out.println(READ_DATA + " - odczytanie danych z pliku");
    }

    private void checkInputData(){
        do {
            try {
                option = scanner.nextInt();

                if (option < 0 || option > 2) {
                    throw new OutOfRangeException("Podano wartość spoza zakresu");
                }
                error = false;
            } catch (InputMismatchException e) {
                System.err.println("Wybrano nieprawidłową wartość, spróbuj jeszcze raz");
            } catch (OutOfRangeException e) {
                System.err.println(e.getMessage());
            } finally {
                scanner.nextLine();
            }
        } while (error);
    }


    private void insertDataOption(){
        Company company = new Company();

        //napisane specjalnie bez obsługi błędów
        for (int i = 0; i < 3; i++) {
            System.out.println("Podaj imię: ");
            String name = scanner.nextLine();
            System.out.println("Podaj nazwisko: ");
            String surname = scanner.nextLine();
            System.out.println("Podaj zarobki");
            double sallary = scanner.nextDouble();

            company.add(new Employee(name, surname, sallary));          //dodanie nowego pracownika
            scanner.nextLine();
        }
        if (company != null)
            company.getInfo();

        try (
                FileOutputStream fs = new FileOutputStream(fileName);
                ObjectOutputStream writer = new ObjectOutputStream(fs);
        ) {
            writer.writeObject(company);
            System.out.println("Zapisano obiekt do pliku");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readDataOption(){
        Company companyRead = null;
        try (
                FileInputStream fs = new FileInputStream(fileName);
                ObjectInputStream reader = new ObjectInputStream(fs);
        ) {
            companyRead = (Company) reader.readObject();

        } catch (IOException e) {
            System.err.println("Nie udało się odczytać pliku");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Nie udało się znaleźć  klasy");
            e.printStackTrace();
        }

        if (companyRead != null) {
            companyRead.getInfo();
        }
    }

    private void chooseOption() {
        do {
            showOptions();
            checkInputData();

            switch (option) {
                case INSERT_DATA:
                    insertDataOption();
                    break;

                case READ_DATA:
                    readDataOption();
                default:
                    break;
            }

            System.out.println();
        } while (option != 0);
    }
}
