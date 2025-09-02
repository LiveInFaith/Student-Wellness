<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>BC Thrive Portal</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #74ebd5, #ACB6E5);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        h1 {
            margin-bottom: 30px;
            color: #333;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 12px 25px;
            font-size: 16px;
            background-color: #007BFF;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            transition: background 0.3s ease;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Wellness Management System</h1>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
    </div>
</body>
</html>