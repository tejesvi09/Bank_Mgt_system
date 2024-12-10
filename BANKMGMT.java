package com.tut.mysqlcon;
import java.util.*;
import java.sql.*;
public class BANKMGMT 

{
	void Add_Customer()
	{
		int Acc_no;
		String Name,Type;
		float Bal;
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the Account Number:");
		Acc_no=sc.nextInt();
		System.out.print("Enter the Name:");
		Name=sc.next();
		System.out.print("Enter the type of Account:");
		Type=sc.next();
		System.out.print("Enter the Initial Balance:");
		Bal=sc.nextFloat();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK","root","root");
			String query="insert into CUSTOMER(Account_Number,Name,Type,Balance) values(?,?,?,?)";
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setInt(1, Acc_no);
			pstm.setString(2, Name);
			pstm.setString(3,Type);
			pstm.setFloat(4,Bal);
			pstm.executeUpdate();
			System.out.println("Record has been Updated....!!!");
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
	}
	void Delete_Customer()
	{
		
		int A_no,a,flag=0;
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the account number to be deleted: ");
		A_no=sc.nextInt();
		try
		{	Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK","root","root");
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from CUSTOMER");
			while (rs.next())
			{
				a=rs.getInt(1);
				if(a==A_no)
				{
					flag=1;
					String query="delete from CUSTOMER where Account_Number=?";
					PreparedStatement pstm=con.prepareStatement(query);
					pstm.setInt(1,A_no);
					pstm.executeUpdate();
					System.out.println("Record has been deleted");
				}
			}
			if(flag==0)
			{
				System.out.println("Record does not exists..!!");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	void withdraw()
	{
		int Ac_no,amt,flag=0,b;
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the account number: ");
		Ac_no=sc.nextInt();
		System.out.print("Enter the amount to be withdrawed: ");
		amt=sc.nextInt();
		
		try
		{	Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK","root","root");
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from CUSTOMER");
			while (rs.next())
			{	b=rs.getInt(1);
				if(b==Ac_no)
				{
					flag=1;
					String query="Update Customer set Balance=Balance-? where Account_Number=?";
					PreparedStatement pstm=con.prepareStatement(query);
					pstm.setInt(1,amt);
					pstm.setInt(2,Ac_no);
					pstm.executeUpdate();
					System.out.print("Amount has been withdrawed");
					
					
				}
				
			}
			if(flag==0)
			{
				System.out.print("Record does not exist");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	void Deposit()
	{
	int Ac_n,amt1,flag=0,c;
	
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter the account number: ");
	Ac_n=sc.nextInt();
	System.out.print("Enter the amount to be deposited: ");
	amt1=sc.nextInt();
	
	try
	{	Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK","root","root");
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("select * from CUSTOMER");
		while (rs.next())
		{	c=rs.getInt(1);
			if(c==Ac_n)
			{
				flag=1;
				String query="Update Customer set Balance=Balance+? where Account_Number=?";
				PreparedStatement pstm=con.prepareStatement(query);
				pstm.setInt(1,amt1);
				pstm.setInt(2,Ac_n);
				pstm.executeUpdate();
				System.out.print("Amount has been deposited");
				
				
			}
			
		}
		if(flag==0)
		{
			System.out.print("Record does not exist");
		}
		
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	}
	void Update_customer() 
	{
	int Accc_no,d,choice;
	String nm;
	String tp;
	int bl;
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter the account number to be updated: ");
	Accc_no=sc.nextInt();
	try
	{	Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK","root","root");
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("select * from CUSTOMER");
		while (rs.next())
		{	d=rs.getInt(1);
			if(d==Accc_no)
			{
				System.out.println("Enter the account number to be udpated: ");
				System.out.println("1.Name ");
				System.out.println("2. Type ");
				System.out.println("3.Balance");
				System.out.print("Enter your choice:");
				choice=sc.nextInt();
				switch(choice)
				{
				case 1: System.out.print("Enter the new name:");
						nm=sc.next();
						String query="Update Customer set Name=? where Account_Number=?";
						PreparedStatement pstm=con.prepareStatement(query);
						pstm.setString(1,nm);
						pstm.setInt(2,Accc_no);
						pstm.executeUpdate();
						
						
						break;
				case 2: System.out.print("Enter the new type of account: ");
						tp=sc.next();
						String query1="Update Customer set Type=? where Account_Number=?";
						PreparedStatement pstm1=con.prepareStatement(query1);
						pstm1.setString(1,tp);
						pstm1.setInt(2,Accc_no);
						pstm1.executeUpdate();
						
				
						break;
				case 3:System.out.print("Enter the balance to be updated: ");
						bl=sc.nextInt();
						String query2="Update Customer set Balance=? where Account_Number=?";
						PreparedStatement pstm2=con.prepareStatement(query2);
						pstm2.setInt(1,bl);
						pstm2.setInt(2,Accc_no);
						pstm2.executeUpdate();
						
					
						break;
				
				default: System.out.println("Enter correct choice:");
				}
				
			}			
				
		}
		
	
	
		
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	
	
}
	void Add_Personal() 
	{
		long Ac_number,phone_no;
		String Address,f;

		try
		{	Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK","root","root");
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from CUSTOMER");
			while (rs.next())
			{	Ac_number=rs.getInt(1);
				f=rs.getString(2);
				Scanner sc= new Scanner(System.in);
				System.out.print("Enter the phone number of the account holder: ");
				phone_no=sc.nextLong();
				System.out.print("Enter the address of the account holder: ");
				Address=sc.next();
				String query="insert into PERSONAL(Account_number,Name,Address,phone_number) values(?,?,?,?)";
				PreparedStatement pstm=con.prepareStatement(query);
				pstm.setLong(1,Ac_number);
				pstm.setString(2,f);
				pstm.setString(3,Address);
				pstm.setLong(4,phone_no);
				pstm.executeUpdate();
				System.out.println("Record has been Updated....!!!");
				
			}
		}	
			
		catch(Exception e)
		{
			System.out.println(e);
		}	
			
		
			
		
	}
void show_Customer()
{
	int acc_no,acc,flag=0;
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter the account number whose record you want to show: " );
	acc_no=sc.nextInt();
	try 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK","root","root");
		Statement stm=con.createStatement();
		String query="select * from CUSTOMER";
		ResultSet rs=stm.executeQuery(query);
		System.out.println("Account_Number\t\tName\t\tType\t\tBalance");
		while (rs.next())
		{
			acc=rs.getInt(1);
			if(acc_no==acc)
			{
				flag=1;
				System.out.println(rs.getInt(1)+"\t\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getInt(4));
			}
		}
		if (flag==0)
		{
			System.out.print("Account does not exist");
		}
	}
	

	catch(Exception e)
	{
		System.out.println(e);
	}


		
	
}
void Show_Personal()
{
	int ac_number,flag=0,ac;
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the account number whose personal info you want to show: ");
	ac_number=sc.nextInt();
	try
	{	Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK","root","root");
		Statement stm=con.createStatement();
		String query="select * from PERSONAL";
		ResultSet rs=stm.executeQuery(query);
		System.out.println("Account_Number\t\tName\t\tAddress\t\tPhone_Number");
		while (rs.next())
		{
			ac=rs.getInt(1);
			if(ac_number==ac)
			{
				flag=1;
				System.out.println(rs.getInt(1)+"\t\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getLong(4));
			}
			
		}
		if (flag==0)
		{
			System.out.print("Account does not exist");
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
}
	
	public static void main(String []args)
	{
		int flag=0;
		char ch='y';
		int choice;
		BANKMGMT obj=new BANKMGMT();
		Scanner sc=new Scanner(System.in);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
			try {
			ResultSet rs=con.getMetaData().getCatalogs();
			while(rs.next())
			{
				String dbname=rs.getString(1);
				if(dbname.equals("BANK"))
				{
					System.out.print("Database Already Exists");
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				Statement stm=con.createStatement();
				stm.executeUpdate("CREATE DATABASE BANK");
			}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			Statement stm=con.createStatement();
			stm.executeUpdate("use BANK");
			String tb1="Create Table if not exists CUSTOMER"
			+"(Account_number  int(10) Primary Key,"
			+"Name varchar(50),"
			+"Type char(1),"
			+"Balance int)";
			stm.executeUpdate(tb1);
			String tb2="Create Table if not exists PERSONAL"
			+"(Account_number int(10),"
			+"Name varchar(50),"
			+"Address varchar(10),"
			+"phone_number int(10),"
			+"Primary Key (Account_number),"
			+"FOREIGN KEY (Account_number) references Customer(Account_number))";
			stm.executeUpdate(tb2);		
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		while(ch=='Y'||ch=='y')
		{
			System.out.println("******WELCOME TO XYZ BANK*******");
			System.out.println("1.ADD CUSTOMER");
			System.out.println("2.DELETE CUSTOMER");
			System.out.println("3.DEPOSIT AMOUNT");
			System.out.println("4.WITHDRAW AMOUNT");
			System.out.println("5.UPDATE CUSTOMER");
			System.out.println("6.Add personal info");
			System.out.println("7.Show personal info");
			System.out.println("8.Show Customer");
			System.out.print("Enter your choice:");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1: obj.Add_Customer();
					break;
			case 2: obj.Delete_Customer();
					break;
			case 3:obj.Deposit();
					break;
			case 4:obj.withdraw();
					break;
			case 5:obj.Update_customer();
					break;
			case 6:obj.Add_Personal();
					break;
			case 7:obj.Show_Personal();
					break;
			case 8:obj.show_Customer();
				 break;
			default: System.out.println("Enter correct choice:");
			}
		}
		
	}
}
