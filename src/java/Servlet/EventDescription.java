package src;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Events");
        
        while (resultSet.next()) {
            Event event = new Event(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDate("date"),
                resultSet.getString("location"),
                resultSet.getString("organizer")
            );
            events.add(event);
        }
        
        connection.close();
        return events;
    }
}
