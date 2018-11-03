package persistencia;

import java.sql.*;
import java.util.Vector;

import dominio.Usuario;

public class Agente {

   
    //instancia del agente
    private static Agente m_instancia=null;
    //Conexion con la base de datos
    private static Connection m_bd;
   
    //Constructor
    private Agente() throws Exception {
    	conectar();
    		
    }
    
    //Implementacion del patron singleton
    //Este patron de dise?o permite implementar clases de las cuales
    //solo existir una instancia
    //http://es.wikipedia.org/wiki/Singleton
     public static Agente getAgente() throws Exception{
          if (m_instancia==null){
          m_instancia=new Agente();
        }
        return m_instancia;
     }
 
    //Metodo para realizar la conexion a la base de datos 
    public static void conectar() throws Exception {
         Class.forName(variables.DRIVER);
         m_bd=DriverManager.getConnection(variables.CONNECTION_STRING);
    }

    
    //Metodo para desconectar de la base de datos
    public static void desconectar() throws Exception{
    	m_bd.close();
    }

    //Metodo para realizar una query en la base de datos (Read)
    public Vector<Object> query(String SQL) throws SQLException, Exception{ 
     	conectar();
    	PreparedStatement stmt = m_bd.prepareStatement(SQL);
    	ResultSet res=stmt.executeQuery();
    	Vector<Object> result = new Vector<Object>();
    	while(res.next()) {
    		result.add(res.getRow());
    	}
    	stmt.close();
    	desconectar();
    	return result;
    }
    
    //Metodo para realizar una non query en la base de datos (Create, Update, Remove)
    public boolean non_query(String SQL) throws SQLException,Exception{
    	PreparedStatement stmt = m_bd.prepareStatement(SQL);
    	boolean res=stmt.execute();
    	stmt.close();
    	desconectar();
    	return res;
    }
	
public Vector<Object> select(String SQL) throws SQLException,Exception{
		
		/*Metodo para realizar una busqueda o seleccion de informacion enla base de datos
	    *El método select develve un vector de vectores, donde cada uno de los vectores
	    *que contiene el vector principal representa los registros que se recuperan de la base de datos.
	    */	
		return null;
	}

  	
}