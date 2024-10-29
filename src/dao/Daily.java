package dao;

import model.Training;

import java.util.List;

public interface Daily {


    void addTraining(Training training);

    void updateTraining(int id, Training updatedTraining);

    void deleteTraining(int id);

    List<Training> getAllTrainings();

    Training getTrainingById(int id);

}