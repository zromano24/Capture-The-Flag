package edu.jhu.cloudsec.ctf.services;

import edu.jhu.cloudsec.ctf.entities.InsecureVoter;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class UnsafeH2DatabaseConnection {
    private UnsafeH2DatabaseConnection() {
    }

    public static List<InsecureVoter> findAllUsersByFirstName(String firstName) {
        Statement stmt = null;
        String query = "SELECT ID, USERNAME, FIRST_NAME, LAST_NAME " +
                "FROM VOTERS WHERE FIRST_NAME = '" + firstName + "'";

        Connection conn = null;
        try {
            String url = "jdbc:h2:mem:testdb;USER=sa;PASSWORD=password";
            conn = DriverManager.getConnection(url);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<InsecureVoter> insecureVoters = new LinkedList<>();
            while (rs.next()) {
                long id = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String first = rs.getString("FIRST_NAME");
                String last = rs.getString("LAST_NAME");
                InsecureVoter insecureVoter = InsecureVoter.builder().id(id).firstName(first).lastName(last).username(username).build();
                insecureVoters.add(insecureVoter);
            }

            return insecureVoters;

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new Error("Problem", ex);
            }
        }
    }
}
