package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static void main(String [] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String username = "sys as sysdba";
			String  password = "Deep@2631";
			Connection con  = DriverManager.getConnection(url,username , password);
//			Statement stmt =  con.createStatement();
			if(con != null) {
			System.out.println("connection succesfully");
			}else {
				System.out.println("connection failed");
				
			}
			
			
			
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

//create table CUSTOMER (AC_NO varchar2(15) primary key , NAME VARCHAR2(25), PHONE_NO number(10),CITY VARCHAR2(25) ,TOTAL_BAL NUMBER(10,2), aadhar_No varchar2(12) );

