package LoginServlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    // Hash password match
    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder();
        // Convert hash byte to hex string
        for (byte b : hash) hex.append(String.format("%02x", b));
        return hex.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");

        try {
            // Load JDBC driver
            Class.forName("org.postgresql.Driver");

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/users", "postgres", "1234")) {

                // Hash password before querying
                String hashedPassword = hashPassword(password);

                // Parameterised query
                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, hashedPassword);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Creates session and stores user data
                    HttpSession session = request.getSession();
                    session.setAttribute("name", rs.getString("name"));
                    session.setAttribute("email", rs.getString("email"));
                    response.sendRedirect("dashboard.jsp");
                } else {
                    request.setAttribute("errorMessage", "Invalid email or password.");
                    // Forward to login (no url change)
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Error: " + ex.getMessage());
        }
    }
}
