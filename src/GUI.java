import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.sun.glass.ui.Window.Level;
import javafx.scene.control.Button;

public class GUI implements Initializable{
	@FXML
	private WebView webView = new WebView();
	WebEngine webEngine = null;
	//must be public to fxml
	public Button insert, update, delete, select, search;
    
    public TextField insert1, insert2, insert3, insert4, insert5, insert6;
    
    public Label textLabel1, textLabel2, textLabel3, textLabel4, textLabel5, textLabel6, textLabel7, textLabel8, textLabel9, rest,
    			 item1, item2, item3, item4, item5, item6, item7, item8, item9, item10;
    public ListView<String> stock;
   
    /*@FXML
    public WebView viewWeb;
    public WebEngine engine;*/
    
   // String[] stockName = {"水泥", "紡織"};


	DBController con = new DBController();
    Connection connection = con.DBConnection();
	
    public void search(ActionEvent e)throws SQLException{
    	String className = insert6.getText();
    	Spider a = new Spider(className);
    	item1.setText(Spider.stockName);
    	item2.setText(Spider.stockPrice);
    	item3.setText(Spider.upsDowns);
    	item4.setText(Spider.upsDownsp);
    	item5.setText(Spider.opening);
    	item6.setText(Spider.closedYesterday);
    	item7.setText(Spider.highest);
    	item8.setText(Spider.lowest);
    	item9.setText(Spider.Volume);
    	item10.setText(Spider.time);
    	
    }
    
    public void insert(ActionEvent e)throws SQLException{



    	
    	Statement statement = connection.createStatement();
    	String query1 = insert1.getText();
    	String query2 = insert2.getText();
    	String query3 = insert3.getText();
    	String query4 = insert4.getText();
    	String query5 = insert5.getText();
    	
    	int income = Integer.parseInt(query4);
    	int outcome = Integer.parseInt(query5);
 		String rest = Integer.toString(income - outcome);
    
    	String query = "INSERT INTO " + " count " + " Values("+ query1 +", " + query2 + ", " + query3 + ", " + query4 + ", " + query5 + ", " + rest + ");";

    	System.out.println(query);
    	statement.executeUpdate(query);
    }
    
    public void update(ActionEvent e)throws SQLException{
    	Statement statement = connection.createStatement();
    	String query1 = insert1.getText();
    	String query2 = insert2.getText();
    	String query3 = insert3.getText();
    	String query4 = insert4.getText();
    	String query5 = insert5.getText();
    	
    	int income = Integer.parseInt(query4);
    	int outcome = Integer.parseInt(query5);
 		String rest = Integer.toString(income - outcome);
 		
    	String query = "UPDATE " + "count" + " SET income=" +query4+ ", outcome=" +query5+ ", rest=" + rest +" WHERE year=" +query1+" and month="+query2+ " and date=" +query3+ ";";

    	System.out.println(query);
    	statement.executeUpdate(query);
    }
    
    public void delete(ActionEvent e)throws SQLException{
    	Statement statement = connection.createStatement();
    	String query1 = insert1.getText();
    	String query2 = insert2.getText();
    	String query3 = insert3.getText();    	
    	String query = "DELETE FROM " + "count" + " WHERE year=" + query1 + " and month=" + query2 + " and date=" + query3 +";";
    	statement.executeUpdate(query);
    }

    
    public void select(ActionEvent e)throws SQLException{
    	String table = "", table2 = "" ,table3 = "", table4 = "", table5 = "", table6 = "";
    	int number = 0;
    	Statement statement = connection.createStatement();
    	
    	String query = "SELECT " + "*" + " FROM "+ "count" ;
     	ResultSet Select_Result = statement.executeQuery(query), Select_Result1;
     	
     	while(Select_Result.next()) {
     		
				table += Select_Result.getString("year")+"\n";
				table2 += Select_Result.getString("month")+"\n";
				table3 += Select_Result.getString("date")+"\n";
				table4 += Select_Result.getString("income")+"\n";
				table5 += Select_Result.getString("outcome")+"\n";
				table6 += Select_Result.getString("rest")+"\n";
				
			}
     	
    	query ="SELECT AVG(income)  FROM count;";
    	Select_Result1 = statement.executeQuery(query);    	
 		Select_Result1.next();
 		number = Select_Result1.getInt(1);
 		String avgincome = Integer.toString(number);
 		textLabel6.setText(avgincome);
 		
    	query ="SELECT AVG(outcome) FROM count;";
    	Select_Result1 = statement.executeQuery(query);    	
 		Select_Result1.next();
 		number = Select_Result1.getInt(1);
 		String avgoutcome = Integer.toString(number);
 		textLabel7.setText(avgoutcome);
 		
    	query ="SELECT SUM(income)  FROM count;";
    	Select_Result1 = statement.executeQuery(query);    	
 		Select_Result1.next();
 		number = Select_Result1.getInt(1);
 		String totalincome = Integer.toString(number);
 		textLabel8.setText(totalincome);
 		
    	query ="SELECT SUM(outcome) FROM count;";
    	Select_Result1 = statement.executeQuery(query);    	
 		Select_Result1.next();
 		number = Select_Result1.getInt(1);
 		String totaloutcome = Integer.toString(number);
 		textLabel9.setText(totaloutcome);
     	
     	textLabel1.setText(table);
     	textLabel2.setText(table2);
     	textLabel3.setText(table3);
     	textLabel4.setText(table4);
     	textLabel5.setText(table5);
     	rest.setText(table6);
     	
     	}

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	webView.setZoom(1.0);
    	webEngine = webView.getEngine();
    	webEngine.load("https://www.ptt.cc/bbs/Stock/index.html");
    	
	}
}







