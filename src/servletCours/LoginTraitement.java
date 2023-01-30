package servletCours;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginTraitement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s;
		// Stockage des donnees envoyees par POST dans des String
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// le type de contenu envoyer a l'utilisateur
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Creation de connection de base de donnees
		Connection cn = new DBconnection().getConnection();
		// stocker les requetes d'obtention des données des étudiants dans des String
		String studentQuery = "select * from etudiant where username=\""+username+"\" AND password=\""+password+"\"";
		String adminQuery = "select * from admin where username=\""+username+"\" AND password=\""+password+"\"";
		try {
			// preparation de requetes
			PreparedStatement studentst = cn.prepareStatement(studentQuery);
			PreparedStatement adminst = cn.prepareStatement(adminQuery);
			// execution des requetes et stockage de resultat dans resultSet
			ResultSet studentresult = studentst.executeQuery();
			ResultSet adminresult = adminst.executeQuery();
			// Verification les donnees entrees par l'utilisateur
			if(studentresult.next()) {
				// Si l'utilisateur est un etudiant en va envoyer une page d'etudiant
				BufferedReader br = new BufferedReader(new FileReader(new File(this.getServletContext().getRealPath("/etudiant.html"))));
				while((s = br.readLine()) != null) {
					out.println(s);
				}
				br.close();
			}else if(adminresult.next()) {
				// Si l'utilisateur est un admin il va etre redireger vers la page d'admin
				response.sendRedirect("./admin");
			}else {
				// Si les donnees sont invalides on va envoyer une page de donnees invalides
				BufferedReader br = new BufferedReader(new FileReader(new File(this.getServletContext().getRealPath("/invalid.html"))));
				while((s = br.readLine()) != null) {
					out.println(s);
				}
				br.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
