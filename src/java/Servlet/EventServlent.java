import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventName = request.getParameter("event_name");
        String eventDate = request.getParameter("event_date");
        String venue = request.getParameter("venue");
        String description = request.getParameter("description");
        String organizer = request.getParameter("organizer");

        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO events (event_name, event_date, venue, description, organizer) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, eventName);
            ps.setDate(2, Date.valueOf(eventDate));
            ps.setString(3, venue);
            ps.setString(4, description);
            ps.setString(5, organizer);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            try {
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM events");
                ArrayList<Event> eventList = new ArrayList<>();

                while (rs.next()) {
                    Event event = new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getString("event_date"),
                        rs.getString("venue"),
                        rs.getString("description"),
                        rs.getString("organizer")
                    );
                    eventList.add(event);
                }
                con.close();

                String json = new Gson().toJson(eventList);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Event {
    private int event_id;
    private String event_name;
    private String event_date;
    private String venue;
    private String description;
    private String organizer;

    public Event(int event_id, String event_name, String event_date, String venue, String description, String organizer) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_date = event_date;
        this.venue = venue;
        this.description = description;
        this.organizer = organizer;
    }

    // Getters and setters
}
