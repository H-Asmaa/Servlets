package servletCours;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjouterTraitement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// stocker les donnees envoyees par la methode post dans des Strings
		String nomEtudiant = request.getParameter("nomEtudiant");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String filiere = request.getParameter("filiere");
		// requete d'insertion des donnees dans la base de donnees
		String query = "insert into `etudiant` values('"+nomEtudiant+"','"+username+"','"+password+"','"+filiere+"')";
		// Creation d'une connection
		Connection cn = new DBconnection().getConnection();
		try {
			//execution de requete d'insertion de donnees dans la base de donnees
			PreparedStatement st = cn.prepareStatement(query);
			st.executeUpdate();
			// redireger l'utilisateur vers la page d'admin
			response.sendRedirect("./admin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
