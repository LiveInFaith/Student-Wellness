package RegisterServlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    // Validates email
    private boolean isEmailValid(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }

    // Validates phone number (10 to 15 digits)
    private boolean isPhoneValid(String phone) {
        return phone != null && phone.matches("^\\d{10,15}$");
    }

    // Checks strong password
    private boolean isPasswordStrong(String password) {
        return password != null && password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*");
    }

    // Hashes password using SHA-256
    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) hex.append(String.format("%02x", b));
        return hex.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form parameters
        String studentNumber = request.getParameter("student_number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        //Validate
        if (!isEmailValid(email)) {
            request.setAttribute("error", "Invalid email format.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!isPhoneValid(phone)) {
            request.setAttribute("error", "Invalid phone number.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!isPasswordStrong(password)) {
            request.setAttribute("error", "Password must be at least 8 characters long and include upper/lowercase letters and numbers.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users", "postgres", "1234");

            // Check if email or student number already exists
            stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? OR student_number = ?");
            stmt.setString(1, email);
            stmt.setString(2, studentNumber);
            rs = stmt.executeQuery();
            
            // Duplicates
            if (rs.next()) {
                request.setAttribute("error", "Email or student number already exists.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            stmt.close();

            // Hash the password before storing it
            String hashedPassword = hashPassword(password);

            // Insert new user
            stmt = conn.prepareStatement("INSERT INTO users (student_number, name, surname, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, studentNumber);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, hashedPassword);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Server error: " + ex.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } finally {
            // Close resources
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }
}
