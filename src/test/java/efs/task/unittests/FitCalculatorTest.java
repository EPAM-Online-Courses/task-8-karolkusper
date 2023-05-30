package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test

    void shouldReturnFalse_whenDietNotRecommended(){
        //given  (np. wzrost 1.72, waga 69.5)
        double weight=69.5;
        double height=1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void shouldThrowException_whenHeightIsZero() {
        // given
        double weight = 75.0;
        double height = 0.0;

        // when / then
        assertThrows(IllegalArgumentException.class, () -> {
            FitCalculator.isBMICorrect(weight, height);
        });
    }

    @ParameterizedTest(name = "weight={0}")
    @ValueSource(doubles = {77.8, 90.7, 80.8}) // przykładowe wartości wagi
    void shouldReturnTrue_whenDietRecommended_forDifferentWeights(double weight) {
        // given
        double height = 1.60; // przykładowy wzrost

        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertTrue(recommended);
    }

    @ParameterizedTest(name="height={0},weight={1}")
    @CsvSource({
            "1.65, 60.0",
            "1.70, 65.0",
            "1.75, 70.0"
    }) // przykładowe pary wartości wzrostu i wagi
    void shouldReturnFalse_forDifferentHeightAndWeight(double height, double weight) {
        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(recommended);
    }

    @ParameterizedTest(name="weight={0},height={1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_forDifferentHeightAndWeightFromFile(double weight, double height) {
        // when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        // then
        assertFalse(recommended);
    }


    @Test
    void shouldReturnUserWithWorstBMI() {
        // given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double expectedWeight = 97.3;
        double expectedHeight = 1.79;

        // when
        User result = FitCalculator.findUserWithTheWorstBMI(users);

        // then
        assertEquals(expectedHeight,result.getHeight());
        assertEquals(expectedWeight,result.getWeight());
    }

    @Test
    void shouldReturnNullWhenEmptyUserList() {
        // given
        List<User> users = Collections.emptyList();

        // when
        User result = FitCalculator.findUserWithTheWorstBMI(users);

        // then
        assertNull(result);
    }

    @Test
    void shouldReturnBMIScoreForUserList() {
        // given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double[] expectedScores = TestConstants.TEST_USERS_BMI_SCORE;

        // when
        double[] result = FitCalculator.calculateBMIScore(users);

        // then
        assertArrayEquals(expectedScores, result);
    }
}