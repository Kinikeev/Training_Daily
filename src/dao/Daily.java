package dao;

import model.Training;

import java.util.List;

public interface Daily {



    public interface TrainingService {

        // Метод для добавления новой тренировки
        void addTraining(Training training);

        // Метод для обновления существующей тренировки по ID
        void updateTraining(int id, Training updatedTraining);

        // Метод для удаления тренировки по ID
        void deleteTraining(int id);

        // Метод для получения списка всех тренировок
        List<Training> getAllTrainings();

        // Метод для получения тренировки по ID
        Training getTrainingById(int id);
    }
}