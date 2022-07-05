package jdbc;

import java.sql.*;
import java.io.*;
public class myjdbcproj
{	
	
	
	public static void main(String [] prgrocks)throws IOException
	{
		int n;
		int ch,ch1;

		try
		{
			String user="sys as sysdba";
			String password="Deep@2631";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521/xe";
			Connection con=DriverManager.getConnection(url,user,password);
			Statement  stmt=con.createStatement();
			System.out.println("\n\n***** Menu Driven Banking Application *****\n\n");
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			do
			{
			System.out.println("1: Enter customer's details for account opening");
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
				System.out.println("enter the name");
				String name=br.readLine();
				System.out.println("enter the phone ");
				long phn=Long.parseLong(br.readLine());
				System.out.println("enter the address");
				String add=br.readLine();
				System.out.println("enter the city ");
				String city=br.readLine();
			
				System.out.println("enter the aadhar No.");
				String aadhar=br.readLine();
			
				System.out.println("enter the Pin code.");
				String pin=br.readLine();
				System.out.println("enter the initial balance");
				String bal=br.readLine();
			
				String AC_NO=pin+aadhar.substring(8,12);
//				((int)(Math.random()*9000)+1000)
				try {
				String insstr="insert into CUSTOMER values('"+ AC_NO+"','"+name+"',"+phn+",'"+ add+"','"+city+"','"+ aadhar+"','"+ pin+"', '"+bal+"')";// sql query
				
				n=stmt.executeUpdate(insstr);
				System.out.println("\n\n");
				System.out.println("Thankyou for opening acount "+name+" your Account  No is "+ AC_NO+ "\n"+ "please do not share your account no");
				System.out.println("\n\n");
				}catch(Exception e  ) {
					System.out.println("you have entered wrong details"+ e);
				}
				break;
	
			case 2: //Show Account Details of a Customer
				System.out.println("enter the Account No");
				String ACC=br.readLine(); 
				String sqlstr5="select * from CUSTOMER   where AC_NO='"+ACC+"'";
				ResultSet rs5=stmt.executeQuery(sqlstr5);
				System.out.println("\n");
				while(rs5.next())
				{	
					System.out.print("Ac No: "+rs5.getString(1)+"\t");
					System.out.print("Name :"+rs5.getString(2)+"\t");
					System.out.print("Mob :"+rs5.getString(3)+"\t");
					System.out.print("Add :"+rs5.getString(4)+" "+rs5.getString(5)+"\t");
					System.out.print("Total Balance :"+rs5.getString(8)+" R"+"\t"+"\n\n\n");
//					System.out.print("Aadhar:"+rs5.getString(6)+"\n");
					
				}
			break;
			case 3://Deposit Money to an Account
				System.out.println("enter the Account No");
				 ACC=br.readLine();
				System.out.println("enter the amount to be deposited");
				int amt7=Integer.parseInt(br.readLine());
				String sqlstr71="select TOTAL_BAL from CUSTOMER where AC_NO='"+ACC+"'";
				ResultSet rs71=stmt.executeQuery(sqlstr71);
				String updstr="update CUSTOMER set TOTAL_BAL=TOTAL_BAL+"+amt7+" where AC_NO='"+ACC+"'";// sql query
				n=stmt.executeUpdate(updstr);
				String sqlstr72="select TOTAL_BAL from CUSTOMER where AC_NO='"+ACC+"'";
				ResultSet rs72=stmt.executeQuery(sqlstr72);
				System.out.print("Updated balance is: \t");
				while(rs72.next())
					System.out.println(rs72.getString("TOTAL_BAL")+"\n");
			break;
			case 4://Withdraw Money from an Account
				int bal8=0;
				System.out.println("enter the Account No");
				String acc8=br.readLine();
				System.out.println("enter the amount to be withdraw");
				int amt8=Integer.parseInt(br.readLine());
				String sqlstr81="select TOTAL_BAL from CUSTOMER where AC_NO='"+acc8+"'";
				ResultSet rs81=stmt.executeQuery(sqlstr81);
				System.out.print("Previous balance is: \t");
				while(rs81.next())
				{
					System.out.println(rs81.getString("TOTAL_BAL")+"\n");
					bal8=Integer.parseInt(rs81.getString("TOTAL_BAL"));
				}
				if(bal8>=amt8)
				{
					updstr="update CUSTOMER set TOTAL_BAL=TOTAL_BAL-"+amt8+" where AC_NO='"+acc8+"'";// sql query
					n=stmt.executeUpdate(updstr);
					String sqlstr82="select TOTAL_BAL from CUSTOMER where AC_NO='"+acc8+"'";
					ResultSet rs82=stmt.executeQuery(sqlstr82);
					System.out.print("Updated balance is: \t");
					while(rs82.next())
					System.out.println(rs82.getString("TOTAL_BAL")+"\n");
				}
				else
					System.out.println("Insufficient Balance !!!!!\n");
			break;
			case 5: 
				stmt.close();
				con.close();
				System.out.println("\nThank you\n");
				System.exit(0);
				break;
			default:
				System.out.println("\nWrong choice\n");
				}
			}
			while(ch!=5);			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}