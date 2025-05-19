package com.LiveScore.controller;

import com.LiveScore.dao.UserDAO;
import com.LiveScore.model.UserModel;
import com.LiveScore.service.RegisterService;
import com.LiveScore.util.PasswordUtil;
import com.LiveScore.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@WebServlet("/register")
@MultipartConfig
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RegisterService registerService;
    private ValidationUtil validationUtil;

    @Override
    public void init() throws ServletException {
        this.registerService = new RegisterService();
        this.validationUtil = new ValidationUtil();
    }

    // Render registration page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    // Handle form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        

        // Default user role ID
        int roleId = 2;

        
        String errorMessage = validateInputs(firstName, lastName, username, email, password);

        if (errorMessage != null) {
            request.setAttribute("errorMessages", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        // Check if username or email already exists
        if (registerService.usernameExists(username)) {
            request.setAttribute("errorMessages", "Username already exists");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        if (registerService.emailExists(email)) {
            request.setAttribute("errorMessages", "Email already registered");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        // Handle profile picture upload
        Part filePart = request.getPart("profilePicture");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            
            String uploadPath = getServletContext().getRealPath("/resources/images");

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Save file
            filePart.write(uploadPath + File.separator + fileName);
        }

        // Encrypt password
        String encryptedPassword = PasswordUtil.encrypt(username, password);

        // Create user model
        UserModel user = new UserModel();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encryptedPassword);
        user.setRoleId(roleId);
        user.setProfilePicture(fileName);  // filename or null

        boolean success = registerService.registerUser(user);

        if (success) {
            request.getSession().setAttribute("registrationSuccess", true);
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("errorMessages", "Registration failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        }
    }

    private String validateInputs(String firstName, String lastName, String username,
            String email, String password) {

        if (validationUtil.isNullOrEmpty(firstName) || validationUtil.isNullOrEmpty(lastName) || 
            validationUtil.isNullOrEmpty(username) || validationUtil.isNullOrEmpty(email) ||
            validationUtil.isNullOrEmpty(password)) {
            return "All fields are required";
        }

        if (!validationUtil.isAlphabetic(firstName)) {
            return "First name should contain only letters";
        }

        if (!validationUtil.isAlphabetic(lastName)) {
            return "Last name should contain only letters";
        }

        if (!validationUtil.isAlphanumericStartingWithLetter(username)) {
            return "Username must be 4-20 alphanumeric characters and start with a letter";
        }

        if (!validationUtil.isValidEmail(email)) {
            return "Invalid email format";
        }

        if (!validationUtil.isValidPassword(password)) {
            return "Password must be at least 8 characters long, with uppercase, lowercase, a digit, and a special character";
        }

        

        return null;
    }
}
