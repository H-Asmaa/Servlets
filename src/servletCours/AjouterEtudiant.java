package servletCours;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjouterEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Envoi de formulaire d'ajout d'un nouveau etudiant
		BufferedReader br = new BufferedReader(new FileReader(new File(this.getServletContext().getRealPath("/ajouterEtudiant.html"))));
		String s;
		while((s = br.readLine()) != null) {
			out.println(s);
		}
		br.close();
	}
}
