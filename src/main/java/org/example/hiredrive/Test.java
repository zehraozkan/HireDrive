package org.example.hiredrive;

import org.example.hiredrive.Connection.UserConnection;
import org.example.hiredrive.message.Message;
import org.example.hiredrive.users.Company;
import org.example.hiredrive.users.Driver;
import org.example.hiredrive.users.User;

import java.sql.Date;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Company company = new Company("ee", "ee", "123456", "cail");
        Driver driver = new Driver("eee", "eee", "123456", "ljail");
        Message message = new Message( driver, company, "asd");
        driver.sendMessage(message);

        UserConnection.retrieveUser(17).sendMessage(message);
        //System.out.println(UserConnection.getUserID(company.getMail()));

        User user = driver;

       // ArrayList<User> users = new ArrayList<Driver>();



    }

    static class UserConnectionTest {

        @BeforeAll
        static void setUp() {
            // You can initialize database connection or perform other setup operations here
        }

        @AfterAll
        static void tearDown() {
            // You can close database connection or perform cleanup operations here
        }

        @Test
        void testAddUser() {
            // Test adding a new user
            UserConnection.addUser("Test", "User", "test@example.com", "password", "driver", new Date(System.currentTimeMillis()));
            assertTrue(true); // Placeholder assertion, you may need additional logic to verify insertion
        }

        @Test
        void testDeleteUser() {
            // Test deleting a user
            UserConnection.deleteUser(1);
            assertTrue(true); // Placeholder assertion, you may need additional logic to verify deletion
        }

        @Test
        void testUpdateUserNameAndSurname() {
            // Test updating user name and surname
            boolean result = UserConnection.updateUserNameAndSurname(1, "NewName", "NewSurname");
            assertTrue(result); // Placeholder assertion, you may need additional logic to verify update
        }

        @Test
        void testGetUser() {
            // Test getting a user by ID
            User user = UserConnection.getUser(1);
            assertNotNull(user); // Placeholder assertion, you may need additional logic to verify user retrieval
        }

        @Test
        void testCheckPassword() {
            // Test checking user password
            boolean result = UserConnection.checkPassword("test@example.com", "password");
            assertTrue(result); // Placeholder assertion, you may need additional logic to verify password check
        }

        @Test
        void testGetAllUsers() {
            // Test getting all users
            ArrayList<User> users = UserConnection.getAllUsers();
            assertNotNull(users); // Placeholder assertion, you may need additional logic to verify user retrieval
        }

        @Test
        void testGetAllUsersWithType() {
            // Test getting all users of a specific type
            ArrayList<User> users = UserConnection.getAllUsers("driver");
            assertNotNull(users); // Placeholder assertion, you may need additional logic to verify user retrieval
        }

        @Test
        void testGetUserType() {
            // Test getting user type by ID
            String userType = UserConnection.getUserType(1);
            assertNotNull(userType); // Placeholder assertion, you may need additional logic to verify user type retrieval
        }

        @Test
        void testGetUserID() {
            // Test getting user ID by email
            int userId = UserConnection.getUserID("test@example.com");
            assertTrue(userId > 0); // Placeholder assertion, you may need additional logic to verify user ID retrieval
        }

        @Test
        void testGetAssociatedDrivers() {
            // Test getting associated drivers for a company
            ArrayList<Driver> drivers = UserConnection.getAssociatedDrivers(1);
            assertNotNull(drivers); // Placeholder assertion, you may need additional logic to verify driver retrieval
        }

        @Test
        void testAddWorksWithRelation() {
            // Test adding a new works with relation
            UserConnection.addWorksWith(1, 2, new Date(System.currentTimeMillis()));
            assertTrue(true); // Placeholder assertion, you may need additional logic to verify insertion
        }

        @Test
        void testDeleteWorksWithRelation() {
            // Test deleting a works with relation
            UserConnection.deleteWorksWithRelation(1, 2);
            assertTrue(true); // Placeholder assertion, you may need additional logic to verify deletion
        }

        // You can write more test methods to cover other functionalities of the UserConnection class
    }
}
