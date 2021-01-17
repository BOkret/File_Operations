package Ex2;

import com.sun.source.util.DocTreePathScanner;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShopLogic {
    final String fileName = "clients.csv";

    private File file = new File(fileName);
    private boolean fileExists = false;
    private int lines = 0;              //number of lines in input file

    Client bestClient = null;
    Client[] clients;

    public void startProgramme() {
        System.out.println(checkFile());
        if (fileExists) {        //programme goes on when file exists
            readFile();
            System.out.println(rememberBestClient());
            searchingClients(getDataFromUser());
        }
    }

    private String checkFile() {
        if (file.exists()) {
            fileExists = true;
            return "File loaded";
        } else {
            return "Check if file exists";
        }
    }

/*      not chosen option of reading file
    void readFileByScanner(){
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                System.out.println(scanner.next());
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
*/

    private void readFile() {
        checkNumberOfLines();
        clients = createClientsList();

/* checking if it works properly //REMOVABLE
//        System.out.println("Liczba wierszy w pliku: " + lines);
//        for (Client client : clients) {
//            System.out.println(client.toString());
//        }
*/
    }

    private void checkNumberOfLines() {
        try (
                var fileReader = new FileReader(file);
                var reader = new BufferedReader(fileReader);
        ) {
            reader.readLine();                                                          //skipping first line

            while (reader.readLine() != null) {                                          //reading number of lines in file - MIGHT BE DONE BETTER
                lines++;
            }

        } catch (IOException e) {
            System.err.println("Loading file ERROR");
            e.printStackTrace();
        }
    }

    private Client[] createClientsList() {
        clients = new Client[lines];
        try (
                var fileReader = new FileReader(file);
                var reader = new BufferedReader(fileReader);
        ) {

            reader.readLine();                                                          //skipping first line

//            while ((nextLine = reader.readLine()) != null){
//                String[] splitedLine = nextLine.split(",");
//                String s = splitedLine[0];
////                System.out.println(nextLine);
//                lines++;
//            }

            for (int i = 0; i < lines; i++) {
                String nextLine = reader.readLine();
                clients[i] = createClientFromCsv(nextLine);
            }
        } catch (IOException e) {
            System.err.println("Loading file ERROR");
            e.printStackTrace();
        }
        return clients;
    }

    private Client createClientFromCsv(String nextLine) {
        String[] splitedLine = nextLine.split(",");                       //spliting line

        int splitID = Integer.parseInt(splitedLine[0]);                         //changing String into Int
        String splitName = splitedLine[1];
        String splitLastname = splitedLine[2];
        String splitCountry = splitedLine[3];
        double splitSpentAmount = Double.parseDouble(splitedLine[4]);

        return new Client(splitID, splitName, splitLastname, splitCountry, splitSpentAmount);     //building table with all client
    }

    private String rememberBestClient() {
        bestClient = clients[0];
        for (Client client : clients) {
            if (client.getShoppingAmount() > bestClient.getShoppingAmount()) {
                bestClient = client;
            }
        }
        return "Najlepszy klient to: " + bestClient.toString();
    }


    private void searchingClients(String country) {
        System.out.println("Klienci z kraju: " + country);
        int i = 0;                                                  //number of clients from the same country
        double averageShoppingAmount = 0;

        for (Client client : clients) {
            if (client.getCountry().toLowerCase().equals(country.toLowerCase())) {
                System.out.println(client.toString());
                averageShoppingAmount += client.getShoppingAmount();
                i++;
            }
        }
        if (i == 0)
            System.out.println("Brak klientów");
        else {
            System.out.printf("Średnia wartość klienta z %s to %.2f", country, averageShoppingAmount / i);
        }
    }

    private String getDataFromUser() {
        boolean corectInput = false;
        String inputCountry = null;

        while (!corectInput) {
            System.out.println("Podaj nazwę kraju, z którego chciałbyś wyszukać klientów: ");
            try (Scanner scanner = new Scanner(System.in)) {                                   //with this construction it is not necessary to close scanner
                inputCountry = scanner.nextLine();
                corectInput = true;
            } catch (InputMismatchException | NullPointerException e) {
                System.err.println("Wrong input. Try again");
                System.out.println("Podaj nazwę kraju, z którego chciałbyś wyszukać klientów: ");
            }
        }
        return inputCountry;
    }

}
