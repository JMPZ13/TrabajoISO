package dominio;

public class GestorUsuario {
	
	public static boolean autenticar(String login, String password) throws Exception{
		boolean autenticado = false;
		
		if(Usuario.read(login, password) != null)
			autenticado = true;
		return autenticado;
	}
	
	public static boolean nuevoUsuario(String login, String password) throws Exception{
		boolean insertado = false;
		
		Usuario u = new Usuario(login, password);
		if(u.insert(null) ==1) //donde va "null" debe ir la variable correspondiente
			insertado = true;
		return insertado;		
	}

}
