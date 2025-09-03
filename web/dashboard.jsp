<%@ page language="java" session="true" %>
<%@ page import="java.util.*" %>

<%
    String name = (String) session.getAttribute("name");
    if (name == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%
    String[] tips = {
        "Drink water and stay hydrated",
        "Take regular study breaks",
        "Talk to someone you trust",
        "Get enough sleep",
        "Practice deep breathing"
    };
    String tip = tips[new Random().nextInt(tips.length)];
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f7f9fb;
            margin: 0;
        }

        .header {
            background-color: #3F51B5;
            color: white;
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header .welcome {
            font-weight: bold;
            font-size: 1.1em;
        }

        a.logout {
            color: white;
            text-decoration: none;
            background: linear-gradient(to right, #74ebd5, #ACB6E5);
            padding: 8px 15px;
            border-radius: 6px;
            transition: background 0.3s ease;
        }

        a.logout:hover {
            background-color: #0056b3;
        }

        .content {
            padding: 40px;
            text-align: center;
        }

        .nav-links {
            margin-top: 30px;
            display: flex;
            justify-content: center;
            gap: 20px;
            flex-wrap: wrap;
        }

        .nav-links a {
            text-decoration: none;
            background: linear-gradient(to right, #74ebd5, #ACB6E5);
            color: white;
            padding: 12px 25px;
            border-radius: 8px;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .nav-links a:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }
        
        .tip-box {
            margin: 40px auto;
            padding: 25px;
            width: 60%;
            background: linear-gradient(to right, #e0c3fc, #8ec5fc);
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
            font-family: 'Segoe UI', sans-serif;
            text-align: center;
            transition: transform 0.3s ease;
        }

        .tip-box:hover {
            transform: scale(1.02);
        }

        .tip-box h4 {
            color: #ffffff;
            font-size: 1.4em;
            margin-bottom: 10px;
        }

        .tip-box p {
            color: #333;
            font-size: 1.1em;
            line-height: 1.6;
            margin: 0;
        }
        
    </style>
    
</head>
<body>
    <div class="header">
        <div class="welcome">Welcome, <%= name %></div>
        <a class="logout" href="LogoutServlet">Logout</a>
    </div>

    <div class="content">
        <h2>Dashboard</h2>
        <p>Welcome to your wellness dashboard! Take control of your journey - book appointments, connect with counselors, share your thoughts, and manage your profile with ease.</p>

        <div class="nav-links">
            <a href="appointments.jsp">Appointments</a>
            <a href="counselors.jsp">Counselors</a>
            <a href="feedback.jsp">Feedback</a>
            <a href="profile.jsp">Profile</a>
        </div>
    </div>
        
    <div class="tip-box">
        <h4>Wellness Tip of the Day</h4>
        <p><%= tip %></p>
    </div>
</body>
</html>
