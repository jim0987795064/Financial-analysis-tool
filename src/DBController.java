import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;*/

import com.sun.glass.ui.Window.Level;


public class DBController {
	
	public Connection connection;
	
	public  Connection DBConnection() {

		String DBname = "bookkeeping";
		String userName = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookkeeping", userName, password);
			System.out.println("connect ok");
		}
		catch (ClassNotFoundException | SQLException e) {
			System.out.println("fail");
			e.printStackTrace();
		}


		return connection;
	}
}

