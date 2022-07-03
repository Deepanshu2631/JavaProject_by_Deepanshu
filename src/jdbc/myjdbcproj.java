package jdbc;

import java.sql.*;
import java.io.*;
public class myjdbcproj
{	
	
	
	public static void main(String [] prgrocks)throws IOException
	{
		int n; // n stores the number of rows affected by insert, update or delete
		int ch,ch1;// for user choice switch case
		Connection con=null;// connection object
		Statement stmt=null;// statement object, helps to execute sql statements through java 
		try
		{
			String uid="sys as sysdba",pwd="Deep@2631";
			// Load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Create the connection object
			String conurl="jdbc:oracle:thin:@localhost:1521/xe";// predefined
			  con=DriverManager.getConnection(conurl,uid,pwd);
			stmt=con.createStatement();
			System.out.println("\n\n***** Banking Management System*****\n\n");
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			do
			{
			System.out.println("1: insert a customer's details");
            System.out.println("2: show account details of a customer");
            System.out.println("3: Deposit Money to an Account");
			System.out.println("4: Withdraw Money from an Account");
			System.out.println("5: EXIT\n");
			System.out.println("enter your choice");
			ch=Integer.parseInt(br.readLine());
			switch(ch)
			{
//			
			case 1://inserting records in customer table
//				
				System.out.println("enter the name of the customer");
				String name=br.readLine();
				System.out.println("enter the phone no. of the customer");
				long phn=Long.parseLong(br.readLine());
				System.out.println("enter the city of the customer");
				String city=br.readLine();
				System.out.println("enter the initial balance");
				String bal=br.readLine();
				System.out.println("enter the aadhar no of the customer");
				String aadhar=br.readLine();

				String AC_NO="10000"+aadhar.substring(7, 12);
				
				try {
				String insstr="insert into CUSTOMER values('"+ AC_NO+"','"+name+"',"+phn+",'"+city+"', '"+bal+"','"+ aadhar+"')";// sql query
				
				
				
				n=stmt.executeUpdate(insstr);// n returns the number of rows added2
				
				System.out.println("Thankyou for opening acount "+name+" your Account  No is "+ AC_NO+ "\n"+ "please do not share your account no"+"\n");
				}catch(Exception e  ) {
					System.out.println("you have entered wrong details"+ e);
				}
				
				
				break;
//		
			case 2: //Show Account Details of a Customer
				System.out.println("enter the Account No of the customer");
				String ACC=br.readLine(); 
				String sqlstr5="select * from CUSTOMER   where AC_NO='"+ACC+"'";
				ResultSet rs5=stmt.executeQuery(sqlstr5);// ResultSet reference is a matrix
//				System.out.println("\nCUST_NO\tNAME\t\tPHONE_NO\tCITY\tACC_NO\tTYPE\tBALANCE\tBR_CODE\tBRANCH_NAME\tBRANCH_CITY\n");
				while(rs5.next())
				{	
					System.out.print(rs5.getString(1)+"\t");// these indices are the column number of the column
					System.out.print(rs5.getString(2)+"\t");
					System.out.print(rs5.getString(3)+"\t");
					System.out.print(rs5.getString(4)+"\t");
					System.out.print(rs5.getString(5)+"\t");
					System.out.print(rs5.getString(6)+"\n");
					
				}
			break;
			case 3://Deposit Money to an Account
				System.out.println("enter the account number");
				 ACC=br.readLine();
				System.out.println("enter the amount to be deposited");
				int amt7=Integer.parseInt(br.readLine());
				String sqlstr71="select TOTAL_BAL from CUSTOMER where AC_NO='"+ACC+"'";
				ResultSet rs71=stmt.executeQuery(sqlstr71);// ResultSet reference is a matrix
				
				
				
				
				String updstr="update CUSTOMER set TOTAL_BAL=TOTAL_BAL+"+amt7+" where AC_NO='"+ACC+"'";// sql query
				n=stmt.executeUpdate(updstr);// n returns the number of rows added
				//System.out.println("\n"+n+" rows updated\n");
				String sqlstr72="select TOTAL_BAL from CUSTOMER where AC_NO='"+ACC+"'";
				ResultSet rs72=stmt.executeQuery(sqlstr72);// ResultSet reference is a matrix
				System.out.print("Updated balance is: \t");
				while(rs72.next())
					System.out.println(rs72.getString("TOTAL_BAL")+"\n");
			break;
			case 4://Withdraw Money from an Account
				int bal8=0;
				System.out.println("enter the account number");
				String acc8=br.readLine();
				System.out.println("enter the amount to be withdraw");
				int amt8=Integer.parseInt(br.readLine());
				String sqlstr81="select TOTAL_BAL from CUSTOMER where AC_NO='"+acc8+"'";
				ResultSet rs81=stmt.executeQuery(sqlstr81);// ResultSet reference is a matrix
				System.out.print("Previous balance is: \t");
				while(rs81.next())
				{
					System.out.println(rs81.getString("TOTAL_BAL")+"\n");
					bal8=Integer.parseInt(rs81.getString("TOTAL_BAL"));
				}
				if(bal8>=amt8)
				{
					updstr="update CUSTOMER set TOTAL_BAL=TOTAL_BAL-"+amt8+" where AC_NO='"+acc8+"'";// sql query
					n=stmt.executeUpdate(updstr);// n returns the number of rows added
					//System.out.println("\n"+n+" rows updated\n");
					String sqlstr82="select TOTAL_BAL from CUSTOMER where AC_NO='"+acc8+"'";
					ResultSet rs82=stmt.executeQuery(sqlstr82);// ResultSet reference is a matrix
					System.out.print("Updated balance is: \t");
					while(rs82.next())
					System.out.println(rs82.getString("TOTAL_BAL")+"\n");
				}
				else
					System.out.println("Insufficient Balance !!!!!\n");
			break;
			case 5: //exit case
				stmt.close();
				con.close();
				System.out.println("\nThank you\n");
				System.exit(0);
				break;
			default:
				System.out.println("\nWrong choice\n");
				}// end of switch case
			}// end of do block
			while(ch!=9);			
		}// end of try block
		catch(Exception e)
		{
			System.out.println(e);
		}
	}// end of main method
}// end of public class