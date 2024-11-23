User Access Management System
Overview
The User Access Management System is a web-based application designed to manage access to software applications based on user roles. The system supports three types of users:

Employee: Can request access to software and track the status of their requests.
Manager: Can view, approve, or reject pending access requests from employees.
Admin: Can create new software applications and manage the list of software.
The system features secure login, session management, and role-based access control.

Features
Authentication: Login and signup for users with secure password storage.
Role-Based Access: Employee, Manager, and Admin functionalities tailored to their roles.
Access Request Management: Employees can request software access, and managers can approve/reject these requests.
Software Management: Admins can create new software with access levels and descriptions.
Session Management: Secure sessions for user authentication with a logout functionality.

Database Setup
Create a database in PostgreSQL:

sql
Copy code
CREATE DATABASE user_access_management;
Create the necessary tables:

sql
Copy code
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE software (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    access_levels VARCHAR(50) NOT NULL
);

CREATE TABLE requests (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    software_id INT REFERENCES software(id),
    access_type VARCHAR(50) NOT NULL,
    reason TEXT NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending' NOT NULL
);
Add sample data to test the application:

sql
Copy code
INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'Admin'),
('manager', 'manager123', 'Manager'),
('employee', 'employee123', 'Employee');
How to Run the Project
1. Clone the Repository
bash
Copy code
git clone https://github.com/Anees02/assignment1.git
cd assignment1

2. Goto the helper folder and update the database connection details.

3. open the project in your favourate IDE and make sure that you have tomcat server installed.

4. run the maven (mvn clean install) or simply run it.

5. Access the Application
Open your browser and navigate to:
bash
Copy code
http://localhost:8080/assignment1
