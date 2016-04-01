package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class DatabaseInteraction {

  Connection conn;
  Statement st;
  ResultSet rs;

  void connectToDatabase() throws SQLException{
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/missionControl", "postgres", "novustemp");
      st = conn.createStatement();
  }

  void getUsers() throws SQLException{
      rs = st.executeQuery("SELECT username FROM users");
  }

  void getPassword(String userID) throws SQLException{
      rs = st.executeQuery("SELECT password FROM users WHERE user_id = " + userID);
      rs.next();
  }
}
