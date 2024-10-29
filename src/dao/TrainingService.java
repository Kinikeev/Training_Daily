package dao;

import model.Training;

import java.util.List;

public class TrainingService {

    private DailyImpl trainingDiary;

    public TrainingService(DailyImpl trainingDiary) {
        this.trainingDiary = trainingDiary;
    }

    public void addTraining(Training training) {
        trainingDiary.addTraining(training);
    }

    public void updateTraining(int id, Training updatedTraining) {
        trainingDiary.updateTraining(id, updatedTraining);
    }

    public void deleteTraining(int id) {
        trainingDiary.deleteTraining(id);
    }

    public List<Training> getAllTrainings() {
        return trainingDiary.getAllTrainings();
    }

    public Training getTrainingById(int id) {
        return trainingDiary.getTrainingById(id);
    }

    // Дополнительные методы для подсчета статистики или сортировки

}
