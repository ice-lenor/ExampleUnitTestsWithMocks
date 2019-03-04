package user_age;

import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.Date;

public class UserAgeCalculatorTest2 {

    @Test
    public void userBabyAge() {

        UsersDatabase usersDatabase = Mockito.mock(UsersDatabase.class);
        int userId = 42;
        User user = new User(userId, "Baby", DateHelpers.parseDate("2019-01-01"));
        Mockito.when(usersDatabase.getUserById(userId)).thenReturn(user);

        UserAgeCalculator calculator = new UserAgeCalculator(usersDatabase);

        int resultAge = calculator.calculateUserAge(userId);

        assertEquals(56, resultAge);
    }

    @Test
    public void userAge365Days() {

        UsersDatabase usersDatabase = Mockito.mock(UsersDatabase.class);
        int userId = 42;
        User user = new User(userId, "Baby", DateHelpers.parseDate("2018-02-26"));
        Mockito.when(usersDatabase.getUserById(userId)).thenReturn(user);

        UserAgeCalculator calculator = new UserAgeCalculator(usersDatabase);

        int resultAge = calculator.calculateUserAge(userId);

        assertEquals(365, resultAge);
    }

    @Test
    public void userIsNull() {

        UsersDatabase usersDatabase = Mockito.mock(UsersDatabase.class);
        int userId = 42;
        User user = null;
        Mockito.when(usersDatabase.getUserById(userId)).thenReturn(user);

        UserAgeCalculator calculator = new UserAgeCalculator(usersDatabase);

        assertThrows(IllegalArgumentException.class, () -> {
            int resultAge = calculator.calculateUserAge(userId);
        });
    }

    @Test
    public void userIsFromTheFuture() {

        UsersDatabase usersDatabase = Mockito.mock(UsersDatabase.class);
        int userId = 42;
        User user = new User(userId, "Someone", DateHelpers.parseDate("2019-02-27"));
        Mockito.when(usersDatabase.getUserById(userId)).thenReturn(user);

        UserAgeCalculator calculator = new UserAgeCalculator(usersDatabase);
        int resultAge = calculator.calculateUserAge(userId);

        assertEquals(-1, resultAge);
    }

}
