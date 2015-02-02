package foo;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import foo.FormBean;

public class DBHandler extends HttpServlet {
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {
            FormBean f = (FormBean) request.getAttribute("formHandler");
            boolean userExists = false;
            //obtain a db connection and perform a db query
            //ensuring that the username does not exist
            //set userExists=true if user is found in db
            //for a simple test, you can disallow the registration
            //of the username "rogerm" as:
            //if (f.getUserName().equals("rogerm")) userExists=true;
            if (userExists) {
                f.setErrors("userName","Duplicate User: Try a different username");
                getServletConfig().getServletContext().
                        getRequestDispatcher("/jsp/forms/retry.jsp").
                        forward(request, response);
            } else {
                //retrieve the bean properties and store them
                // into the database.
                getServletConfig().getServletContext().
                        getRequestDispatcher("/jsp/forms/success.jsp").
                        forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}