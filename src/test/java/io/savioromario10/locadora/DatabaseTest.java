package io.savioromario10.locadora;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseTest {

  static Connection connection;

  @BeforeAll
  static void setupDatabase() throws SQLException {
    
    connection = DriverManager.getConnection("jdbc:h2:mem:testdb","sa","");
    connection.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR)");
  }
  @BeforeEach
  void insertUserTest() throws SQLException {

    connection.createStatement().execute("INSERT INTO users (id, name) VALUES (1, 'John Doe')");
  }
  
  @AfterAll
  static void closeDatabase() throws SQLException {
    connection.close();
  }

  @Test
  void testUserExists() throws SQLException {
    var result = connection
                  .createStatement()
                  .executeQuery("SELECT * FROM users WHERE id = 1");

    assertTrue(result.next());
  }
}