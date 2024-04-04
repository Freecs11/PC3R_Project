import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/demo")
public class ServletDemo extends HttpServlet {


    DataSource dataSource;

    @Override
    public void init(){
        Context ctx = new InitialContext();
        ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mkyongdb");
    }


    // init
    @Override
    public void init() {
        System.out.println("ServletDemo initialized");
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello from jffjijijrir\n");
        // test db connection
        try {
            dataSource.getConnection();
            resp.getWriter().write("Connection successful");
        } catch (Exception e) {
            resp.getWriter().write("Connection failed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello from saiksakj\n");

        try {
            dataSource.getConnection();
            resp.getWriter().write("Connection successful");
        } catch (Exception e) {
            resp.getWriter().write("Connection failed\n");
            resp.getWriter().write(e.getMessage());
        }

        resp.getWriter().write("Hello from ServletDemo");
    }


}
