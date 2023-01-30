package servletCours;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Envoi de contenu de la page admin avant le tableau
		BufferedReader br = new BufferedReader(new FileReader(new File(this.getServletContext().getRealPath("/adminAvantTable.txt"))));
		String s;
		while((s = br.readLine()) != null) {
			out.println(s);
		}
		br.close();
		// Creation d'une connection à la base de donnees
		Connection cn = new DBconnection().getConnection();
		String requete = "select * from etudiant";
		try {
			PreparedStatement st = cn.prepareStatement(requete);
			// Recuperer les comptes des etudiants de la base de donnees
			ResultSet result = st.executeQuery();
			// Affichage des lignes de la table des etudiants
			while(result.next()) {
				out.println("<tr><td>"+result.getString("nomEtudiant")+"</td><td>"+result.getString("username")+"</td><td>"+result.getString("password")+"</td><td>"+result.getString("filiere")+"</td></tr>");
			}
			// Envoi de contenu de la page admin apres le tableau
			br = new BufferedReader(new FileReader(new File(this.getServletContext().getRealPath("/adminApresTable.txt"))));
			while((s = br.readLine()) != null) {
				out.println(s);
			}
			br.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
