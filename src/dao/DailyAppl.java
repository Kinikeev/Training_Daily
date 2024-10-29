package dao;

import model.Training;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DailyAppl {

    private TrainingService trainingService = new TrainingService(new DailyImpl());
    private Scanner scanner = new Scanner(System.in);
    private final String FILE_NAME = "trainings.csv";

    public static void main (String[] args) {

        new DailyAppl().start();

    } // end of main

    public void start() {
        loadFromFile();
        while (true) {
            showMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> addTraining();
                    case 2 -> updateTraining();
                    case 3 -> deleteTraining();
                    case 4 -> viewAllTrainings();
                    case 5 -> saveToFile();
                    case 0 -> {
                        System.out.println("Exiting application.");
                        saveToFile();
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("--- Training Diary ---");
        System.out.println("1. Add new training");
        System.out.println("2. Edit training");
        System.out.println("3. Delete training");
        System.out.println("4. View all trainings");
        System.out.println("5. Save data to file");
        System.out.println("0. Exit");
        System.out.print("Please select an option: ");
    }

    private void addTraining() {
        try {
            System.out.println("Enter training type: (walking, running, jumping)");
            String type = scanner.nextLine();
            System.out.println("Enter date (yyyy-mm-dd):");

            // заменил разделители (пробел, запятая...)
            String inputDate = scanner.nextLine();
            // Замена любых разделителей (точка, запятая, тире, подчеркивание) на дефис
            inputDate = inputDate.replaceAll("[ .,_-]+", "-");
            LocalDate date = LocalDate.parse(inputDate);

            //LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.println("Enter distance (km):");
            double distance = scanner.nextDouble();
            System.out.println("Enter duration (hours):");
            double duration = scanner.nextDouble();

            Training training = new Training(trainingService.getAllTrainings().size() + 1, type, date, distance, duration);
            trainingService.addTraining(training);
            System.out.println("Training added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid number format. Please try again.");
            scanner.nextLine(); // clear the invalid input

            // добавил
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in format yyyy-mm-dd.");

        } catch (Exception e) {
            System.out.println("An error occurred while adding training. Please try again.");
        }
    }

    private void updateTraining() {
        try {

            // добавил (walking, running, jumping)
            System.out.println("Enter training ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Training existingTraining = trainingService.getTrainingById(id);
            if (existingTraining != null) {
                System.out.println("Enter new training type: (walking, running, jumping)");
                String type = scanner.nextLine();

                // заменил разделители (пробел, запятая...)
                System.out.println("Enter new date (yyyy-mm-dd):");
                String inputDate = scanner.nextLine();
                // Замена любых разделителей (точка, запятая, тире, подчеркивание) на дефис
                inputDate = inputDate.replaceAll("[ ._,-]+", "-");
                LocalDate date = LocalDate.parse(inputDate);
              //  LocalDate date = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter new distance (km):");
                double distance = scanner.nextDouble();
                System.out.println("Enter new duration (hours):");
                double duration = scanner.nextDouble();

                Training updatedTraining = new Training(id, type, date, distance, duration);
                trainingService.updateTraining(id, updatedTraining);
                System.out.println("Training updated successfully.");
            } else {
                System.out.println("Training not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid number format. Please try again.");
            scanner.nextLine(); // clear the invalid input

            // добавил
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in format yyyy-mm-dd.");


        } catch (Exception e) {
            System.out.println("An error occurred while updating training. Please try again.");
        }
    }

    private void deleteTraining() {
        try {
            System.out.println("Enter training ID to delete:");
            int id = scanner.nextInt();
            Training existingTraining = trainingService.getTrainingById(id);

            if (existingTraining != null) {
                trainingService.deleteTraining(id);
                System.out.println("Training deleted successfully.");
            } else {
                System.out.println("Training not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid ID format. Please try again.");
            scanner.nextLine(); // clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred while deleting training. Please try again.");
        }
    }

    private void viewAllTrainings() {
        System.out.println("All trainings:");
        for (Training training : trainingService.getAllTrainings()) {
            System.out.println(training);
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Training training : trainingService.getAllTrainings()) {
                writer.write(training.getId() + "," + training.getType() + "," + training.getDate() + "," +
                        training.getDistance() + "," + training.getDuration());
                writer.newLine();
            }
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving data to file.");
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    int id = Integer.parseInt(data[0]);
                    String type = data[1];
                    LocalDate date = LocalDate.parse(data[2]);
                    double distance = Double.parseDouble(data[3]);
                    double duration = Double.parseDouble(data[4]);

                    Training training = new Training(id, type, date, distance, duration);
                    trainingService.addTraining(training);
                }
            }
            System.out.println("Data loaded from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty list.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading data from file.");
        } catch (Exception e) {
            System.out.println("Invalid data format in file. Starting with an empty list.");
        }
    }

}
