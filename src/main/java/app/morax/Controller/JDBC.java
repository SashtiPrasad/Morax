package app.morax.Controller;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
    // used to create queries
    private Statement statement;

    // this will be who is currently logged in
    private String user;

    public JDBC() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Morax", "root", "370morax");

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            while (resultSet.next()) {
                System.out.print("Username: ");
                System.out.println(resultSet.getString("username"));
                System.out.print("Email: ");
                System.out.println(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean login(String uname, String pass) {
        try {
            String query = String.format("SELECT * FROM user WHERE username = '%s'", uname);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (BCrypt.checkpw(pass, resultSet.getString("password"))) {
                    user = uname;
                    return true;
                }
                else {
                    System.out.println("Incorrect username or password.");
                    return false;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTask() {
        try {
            String query = String.format("INSERT INTO task (username, task, category, location, notes) VALUES ('%s', '%s', '%s', '%s', '%s')", user, "new task", "category1", "home", "note");
            statement.execute(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get all tasks for logged in user
    public void getTasks() {
        try {
            String query = String.format("SELECT * FROM task WHERE username = '%s'", user);
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.print("Task: ");
                System.out.println(resultSet.getString("task"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTasksAscending() {
        try {
            String query = String.format("SELECT * FROM task WHERE username = '%s' ORDER BY task", user);
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.print("Task: ");
                System.out.println(resultSet.getString("task"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTasksDescending() {
        try {
            String query = String.format("SELECT * FROM task WHERE username = '%s' ORDER BY task DESC", user);
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.print("Task: ");
                System.out.println(resultSet.getString("task"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFriend() {
        try {
            String query = String.format("INSERT INTO added (username, added) VALUES ('%s', '%s')", user, "added user");
            statement.execute(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCategory() {
        try {
            String query = String.format("INSERT INTO category (username, name) VALUES ('%s', '%s')", user, "new category");
            statement.execute(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCategory() {
        try {
            String query = String.format("SELECT * FROM category WHERE username = '%s'", user);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.print("Category: ");
                System.out.println(resultSet.getString("name"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
