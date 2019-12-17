import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    UserDBService userDBService = new UserDBService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(userDBService.deleteUser(Long.valueOf(request.getParameter("id")))) {
            request.setAttribute("message", "User delete ok");
        } else  {
            request.setAttribute("message", "User delete error");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/result.jsp");
        requestDispatcher.forward(request, response);
        requestDispatcher.forward(request, response);
    }
}
