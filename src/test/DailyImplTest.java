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


    // добавил два теста


    // корректно обновляет существующую запись
    @Test
    void updateTraining() {
        Training training = new Training(1, "running", LocalDate.now(), 5.0, 0.5);
        trainingDiary.addTraining(training);

        Training updatedTraining = new Training(1, "walking", LocalDate.now().plusDays(1), 10.0, 1.0);
        trainingDiary.updateTraining(1, updatedTraining);

        Training result = trainingDiary.getTrainingById(1);
        assertEquals("walking", result.getType());
        assertEquals(10.0, result.getDistance());
        assertEquals(1.0, result.getDuration());
    }

    // возвращает нужный объект, если он существует, и null, если объект с таким id не найден
    @Test
    void getTrainingById() {
        Training training = new Training(1, "running", LocalDate.now(), 5.0, 0.5);
        trainingDiary.addTraining(training);

        Training result = trainingDiary.getTrainingById(1);
        assertNotNull(result);
        assertEquals(training, result);

        Training notFound = trainingDiary.getTrainingById(2);
        assertNull(notFound);
    }


}