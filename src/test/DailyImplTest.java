package test;

import dao.DailyImpl;
import model.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DailyImplTest {

    private DailyImpl trainingDiary;
    @BeforeEach
    void setUp() {
        trainingDiary = new DailyImpl();
    }

    @Test
    void addTraining() {
        Training training = new Training(1, "running", LocalDate.now(), 5.0, 0.5);
        trainingDiary.addTraining(training);
        assertEquals(1, trainingDiary.getAllTrainings().size());
    }

    @Test
    void deleteTraining() {
        Training training = new Training(1, "running", LocalDate.now(), 5.0, 0.5);
        trainingDiary.addTraining(training);
        trainingDiary.deleteTraining(1);
        assertEquals(0, trainingDiary.getAllTrainings().size());
    }
}