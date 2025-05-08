

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/hello.do")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html"); // MIME 
		 PrintWriter out = response.getWriter();
		 System.out.print("begin servlet response");
	     out.println("<html>");
	     out.println("<head>");
	     out.println("<title>Hello World!</title>");
	     out.println("</head>");
	     out.println("<body>");
	     out.println("<h1>Hello World!, Using Servlet API</h1>");
	     out.println("</body>");
	     out.println("</html>");

	}

}
