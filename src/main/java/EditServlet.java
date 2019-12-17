import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    UserDBService userDBService = new UserDBService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDBService.getUserById(Long.valueOf(request.getParameter("id")));
        if(user == null) {
            request.setAttribute("message", "User edit error");
        } else  {
            user.setAge(Integer.valueOf(request.getParameter("age")));
            user.setName(request.getParameter("name"));
            userDBService.updateUser(user);
            request.setAttribute("message", "User edit ok");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/result.jsp");
        requestDispatcher.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
        request.setAttribute("user", userDBService.getUserById(Long.valueOf(request.getParameter("id"))));
        requestDispatcher.forward(request, response);
    }
}
