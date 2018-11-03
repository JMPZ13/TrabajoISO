package dominio;

import persistencia.Agente;
import persistencia.variables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Usuario {
	
	public String mLogin;
	public String mPassword;
	
	public String getmLogin() {
		return mLogin;
	}

	public void setmLogin(String mLogin) {
		this.mLogin = mLogin;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}


	
	
	//Constructor para la creaci?n de un objeto Usuario vacio
	public Usuario(){
		this.mLogin = null;
		this.mPassword = null;
	}
	
	//Constructor para la creaci?n de un Usuario
	public Usuario(String login, String password){
		this.mLogin = login;
		this.mPassword = password;
	}
	
	//Selecci?n de un usuario de la base de datos a partir del login y el password
	public static Usuario read(String login, String password) throws Exception{
		Usuario u = null;
		Vector<Object> aux = null;
		String SQL_Consulta = "SELECT login, password FROM usuario WHERE login = '"+login+"' AND pass = '"+password+"';";
		
		Agente a = Agente.getAgente();
		Vector<Object> res = a.select(SQL_Consulta);
		
		if (res.size() == 1){
			aux = (Vector<Object>) res.elementAt(0);
			u = new Usuario((String) aux.elementAt(0), (String) aux.elementAt(1));
		}
		return u;
	}
	
	//Metodo para realizar una insercion en la base de datos
    public int insert(String SQL) throws SQLException, Exception{ 
     	Agente.conectar();
     	Connection mBD = DriverManager.getConnection("jdbc:mysql://localhost:"+ variables.DBPORT+"/practicabd?user="+variables.DBUSER+"&password="+variables.DBPASS);
    	PreparedStatement stmt = mBD.prepareStatement(SQL);
    	int res=stmt.executeUpdate();
    	stmt.close();
    	mBD.close();
    	Agente.desconectar();
    	return res;
    }
  	//metodo para eliminar un usuario
  	public int delete () throws Exception{
  		//esto lo haremos...pero hoy no...ma?ana
  		
  	Class.forName("com.mysql.jdbc.Driver").newInstance();
  	Connection mBD = DriverManager.getConnection("jdbc:mysql://localhost:"+ variables.DBPORT+"/practicabd?user="+variables.DBUSER+"&password="+variables.DBPASS);
  	PreparedStatement stmt = mBD.prepareStatement("DELETE FROM Usuario WHERE('"+this.mLogin+"' = login);");
  	int res=stmt.executeUpdate();
  	stmt.close();
  	mBD.close();
  	return res;
  		
  		//return 0;
  	}
	

}







