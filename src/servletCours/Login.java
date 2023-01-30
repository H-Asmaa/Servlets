package servletCours;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Generic servlet est une servlet qui ne depend a aucun protocole
// httpServlet est une servlet qui utilise le protocole http
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// init() est utilisée pour faire un traitement lors de l'initialisation de la servlet
	public void init() throws ServletException {}
	// doGet() est utilisée pour interpreter la demande envoyée par la methode GET
	// doPost() est utilisée pour interpreter la demande envoyée par la methode POST
	// httpServletRequest presente la demande enovoyée par l'utilisateur au serveur
	// httpServletResponse presente la réponse envoyee par le serveur à l'utilisateur
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declarer le type de contenu de response
		response.setContentType("text/html");
		// response.getWriter() retourne un objet de printWriter qui permet d'envoyer du text a l'utilisateur
		PrintWriter out = response.getWriter();
		// Envoyer le contenu de page de login.html en utilisant bufferedReader
		//getServletContext() retourne un objet de class ServletContext qui contient les informations de configuration de notre servlet
		//getRealPath() retourne le lien absolu de notre login.html
		BufferedReader br = new BufferedReader(new FileReader(new File(this.getServletContext().getRealPath("/login.html"))));
		String s;
		// envoyer le contenu de BufferedReader ligne par ligne
		while((s = br.readLine()) != null) {
			out.println(s);
		}
		br.close();
	}
}
