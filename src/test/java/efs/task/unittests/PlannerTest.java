package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PlannerTest {

    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldCalculateDailyCaloriesDemandForUser(ActivityLevel activityLevel) {
        // given
        User user = TestConstants.TEST_USER;
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);

        // when
        int result = planner.calculateDailyCaloriesDemand(user, activityLevel);

        // then
        Assertions.assertEquals(expectedCalories, result);
    }

    @Test
    void shouldCalculateDailyIntakeForUser() {
        // given
        User user = TestConstants.TEST_USER;

        // when
        DailyIntake dailyIntake = planner.calculateDailyIntake(user);

        // then
        Assertions.assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getCalories(), dailyIntake.getCalories());
        Assertions.assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getProtein(), dailyIntake.getProtein());
        Assertions.assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getFat(), dailyIntake.getFat());
        Assertions.assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getCarbohydrate(), dailyIntake.getCarbohydrate());
    }

}