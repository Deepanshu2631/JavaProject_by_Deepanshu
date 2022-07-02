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
			String uid="cseb1741012297",pwd="cseb1741012297";
			// Load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Create the connection object
			String conurl="jdbc:oracle:thin:@172.17.144.110:1521:ora11g";// predefined
			con=DriverManager.getConnection(conurl,uid,pwd);
			stmt=con.createStatement();
			System.out.println("\n\n***** Banking Management System*****\n\n");
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			do
			{
			System.out.println("1: display the customer details");
			System.out.println("2: insert a customer's details");
			System.out.println("3: delete a customer's details");
			System.out.println("4: update a customer's details");
			System.out.println("5: show account details of a customer");
			System.out.println("6: show loan details of a customer");
			System.out.println("7: Deposit Money to an Account");
			System.out.println("8: Withdraw Money from an Account");
			System.out.println("9: EXIT\n");
			System.out.println("enter your choice");
			ch=Integer.parseInt(br.readLine());
			switch(ch)
			{
			case 1://displaying customer records
			int c=0;
				String sqlstr="select * from CUSTOMER order by CUST_NO";// sql query
				ResultSet rs=stmt.executeQuery(sqlstr);// ResultSet reference is a matrix
				while(rs.next())
				{
					System.out.print(rs.getString(1)+"\t\t");// these indices are the column number of the column
					System.out.print(rs.getString(2)+"\t\t");
					System.out.print(rs.getString(3)+"\t\t");
					System.out.println(rs.getString(4)+"\t\t\n");
					c++;
				}
				System.out.println(c+" rows selected !!!\n\n");
				break;
			case 2://inserting records in customer table
				System.out.println("enter the cust_no of the customer");
				String cno=br.readLine(); // readLine() is used to take string type user input
				System.out.println("enter the name of the customer");
				String name=br.readLine();
				System.out.println("enter the phone no. of the customer");
				long phn=Long.parseLong(br.readLine());
				System.out.println("enter the city of the customer");
				String city=br.readLine();
				String insstr="insert into CUSTOMER values('"+cno+"','"+name+"',"+phn+",'"+city+"')";// sql query
				n=stmt.executeUpdate(insstr);// n returns the number of rows added
				System.out.println(n+" \nrows inserted\n\n");
				break;
			case 3:// delete the details of a customer
				System.out.println("enter the cust_no of the customer");
				String cn=br.readLine(); // readLine() is used to take string type user input
				String delstr="delete from CUSTOMER  where CUST_NO='"+cn+"'";// sql query
				n=stmt.executeUpdate(delstr);// n returns the number of rows added
				System.out.println("\n"+n+" rows deleted\n");
				break;
			case 4: // update the record's  of a customer
				System.out.println("enter the cust_no of the customer");
				String cn4=br.readLine(); 
				String updstr="";// since it is getting modified inside switch case
				System.out.println("1: Update name of the customer");
				System.out.println("2: Update phone_no of the customer");
				System.out.println("3: Update city of the customer");
				System.out.println("enter your choice\n");
				ch1=Integer.parseInt(br.readLine());
				switch(ch1)
				{
					case 1:
						System.out.println("enter the name of the customer");
						String name4=br.readLine();
						updstr="update CUSTOMER set NAME='"+name4+"' where CUST_NO='"+cn4+"'";// sql query
					break;
					case 2:
						System.out.println("enter the phone no. of the customer");
						long phn4=Long.parseLong(br.readLine());
						updstr="update CUSTOMER set PHONE_NO="+phn4+" where CUST_NO='"+cn4+"'";// sql query
					break;
					case 3:
						System.out.println("enter the city of the customer");
						String city4=br.readLine();
						updstr="update CUSTOMER set CITY='"+city4+"' where CUST_NO='"+cn4+"'";// sql query
					break;
				}
				n=stmt.executeUpdate(updstr);// n returns the number of rows added
				System.out.println("\n"+n+" rows updated\n");
				break;
			case 5: //Show Account Details of a Customer
				System.out.println("enter the cust_no of the customer");
				String cn5=br.readLine(); 
				String sqlstr5="select * from CUSTOMER  natural join DEPOSITOR natural join  ACCOUNT natural join BRANCH where CUST_NO='"+cn5+"'";
				ResultSet rs5=stmt.executeQuery(sqlstr5);// ResultSet reference is a matrix
				System.out.println("\nCUST_NO\tNAME\t\tPHONE_NO\tCITY\tACC_NO\tTYPE\tBALANCE\tBR_CODE\tBRANCH_NAME\tBRANCH_CITY\n");
				while(rs5.next())
				{
					System.out.print(rs5.getString("cust_no")+"\t");// these indices are the column number of the column
					System.out.print(rs5.getString("name")+"\t");
					System.out.print(rs5.getString("phone_no")+"\t");
					System.out.print(rs5.getString("city")+"\t");
					System.out.print(rs5.getString("account_no")+"\t");
					System.out.print(rs5.getString("type")+"\t");
					System.out.print(rs5.getString("balance")+"\t");
					System.out.print(rs5.getString("branch_code")+"\t");
					System.out.print(rs5.getString("branch_name")+"\t");
					System.out.println(rs5.getString("branch_city")+"\n");
				}
			break;
			case 6: //Show Loan Details of a Customer
				int z=0;
				System.out.println("enter the cust_no of the customer");
				String cn6=br.readLine(); 
				String sqlstr6="select * from CUSTOMER  natural join LOAN natural join  BRANCH where CUST_NO='"+cn6+"'";
				ResultSet rs6=stmt.executeQuery(sqlstr6);// ResultSet reference is a matrix
				System.out.println("\nCUST_NO\tNAME\t\tPHONE_NO\tCITY\tLOAN_NO\tAMOUNT\tBR_CODE\tBRANCH_NAME\tBRANCH_CITY\n");
				while(rs6.next())
				{
					System.out.print(rs6.getString("cust_no")+"\t");// these indices are the column number of the column
					System.out.print(rs6.getString("name")+"\t");
					System.out.print(rs6.getString("phone_no")+"\t");
					System.out.print(rs6.getString("city")+"\t");
					System.out.print(rs6.getString("loan_no")+"\t");
					System.out.print(rs6.getString("amount")+"\t");
					System.out.print(rs6.getString("branch_code")+"\t");
					System.out.print(rs6.getString("branch_name")+"\t");
					System.out.println(rs6.getString("branch_city")+"\n");
					z++;
				}
				if(z==0)
					System.out.println("congrats !!! you have no loan\n\n");
			break;
			case 7://Deposit Money to an Account
				System.out.println("enter the account number");
				String acc7=br.readLine();
				System.out.println("enter the amount to be deposited");
				int amt7=Integer.parseInt(br.readLine());
				String sqlstr71="select BALANCE from ACCOUNT where ACCOUNT_NO='"+acc7+"'";
				ResultSet rs71=stmt.executeQuery(sqlstr71);// ResultSet reference is a matrix
				System.out.print("Previous balance is: \t");
				while(rs71.next())
					System.out.println(rs71.getString("balance")+"\n");
				updstr="update ACCOUNT set BALANCE=BALANCE+"+amt7+" where ACCOUNT_NO='"+acc7+"'";// sql query
				n=stmt.executeUpdate(updstr);// n returns the number of rows added
				//System.out.println("\n"+n+" rows updated\n");
				String sqlstr72="select BALANCE from ACCOUNT where ACCOUNT_NO='"+acc7+"'";
				ResultSet rs72=stmt.executeQuery(sqlstr72);// ResultSet reference is a matrix
				System.out.print("Updated balance is: \t");
				while(rs72.next())
					System.out.println(rs72.getString("balance")+"\n");
			break;
			case 8://Withdraw Money from an Account
				int bal8=0;
				System.out.println("enter the account number");
				String acc8=br.readLine();
				System.out.println("enter the amount to be withdraw");
				int amt8=Integer.parseInt(br.readLine());
				String sqlstr81="select BALANCE from ACCOUNT where ACCOUNT_NO='"+acc8+"'";
				ResultSet rs81=stmt.executeQuery(sqlstr81);// ResultSet reference is a matrix
				System.out.print("Previous balance is: \t");
				while(rs81.next())
				{
					System.out.println(rs81.getString("balance")+"\n");
					bal8=Integer.parseInt(rs81.getString("balance"));
				}
				if(bal8>=amt8)
				{
					updstr="update ACCOUNT set BALANCE=BALANCE-"+amt8+" where ACCOUNT_NO='"+acc8+"'";// sql query
					n=stmt.executeUpdate(updstr);// n returns the number of rows added
					//System.out.println("\n"+n+" rows updated\n");
					String sqlstr82="select BALANCE from ACCOUNT where ACCOUNT_NO='"+acc8+"'";
					ResultSet rs82=stmt.executeQuery(sqlstr82);// ResultSet reference is a matrix
					System.out.print("Updated balance is: \t");
					while(rs82.next())
					System.out.println(rs82.getString("balance")+"\n");
				}
				else
					System.out.println("Insufficient Balance !!!!!\n");
			break;
			case 9: //exit case
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