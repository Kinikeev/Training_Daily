package dao;

import model.Training;

import java.util.ArrayList;
import java.util.List;

public class DailyImpl implements Daily{

    private List<Training> trainings = new ArrayList<>();

    @Override
    public void addTraining(Training training) {
        trainings.add(training);
    }

    @Override
    public void updateTraining(int id, Training updatedTraining) {
        for (int i = 0; i < trainings.size(); i++) {
            if (trainings.get(i).getId() == id){
                trainings.set(i,updatedTraining);
                return;
            }
        }
    }

    @Override
    public void deleteTraining(int id) {
        trainings.removeIf(training -> training.getId() == id);
    }

    @Override
    public List<Training> getAllTrainings() {
        return new ArrayList<>(trainings);
    }

    @Override
    public Training getTrainingById(int id) {
        return trainings.stream()
                .filter(training -> training.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Новый метод для подсчета общего количества километров
    public double getTotalDistance() {
        return trainings.stream().mapToDouble(Training::getDistance).sum();
    }
}
