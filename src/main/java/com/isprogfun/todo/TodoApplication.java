package com.isprogfun.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);

		// Create tables
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://database:5432/todo-db", "admin", "secret")) {
			Statement statement = connection.createStatement();

			String usersTableSql = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL);";
			String tasksTableSql = "CREATE TABLE IF NOT EXISTS tasks (id SERIAL PRIMARY KEY, title VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, status VARCHAR(255) NOT NULL);";

			statement.execute(usersTableSql);
			statement.execute(tasksTableSql);
		} catch (SQLException exception) {
			System.out.println("Error creating table: " + exception.getMessage());
		}
	}

}
