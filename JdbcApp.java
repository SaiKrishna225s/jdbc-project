package jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;

public class JdbcApp {
	static Scanner sc = new Scanner(System.in);

	static void insertRecord(Connection con) {
		try {
			PreparedStatement stmt = con.prepareStatement("insert into employee values(?,?,?)");
			System.out.println("enter employee id:");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println("enter employee name:");
			String name = sc.nextLine();
			System.out.println("enter employee phoneno:");
			int phone = sc.nextInt();

			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setInt(3, phone);
			int n = stmt.executeUpdate();
			if (n != 0) {
				System.out.println("record updated" + n);
			} else {
				System.out.println("no record updated" + n);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	static void deleteRecord(Connection con) {
		try {
			PreparedStatement stmt = con.prepareStatement("delete from employee where employeeId=(?) ");
			System.out.println("enter employee id to delete:");
			int id = sc.nextInt();
			sc.nextLine();

			stmt.setInt(1, id);

			int n = stmt.executeUpdate();
			if (n != 0) {
				System.out.println("record deleted " + n);
			} else {
				System.out.println("no record deleted " + n);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	static void updateRecord(Connection con) {
		try {
			System.out.println("enter what you want to enter (1.name),(2.phone");
			int opn = sc.nextInt();

			if (opn == 1) {
				PreparedStatement stmt = con.prepareStatement("update  employee set name=(?) where employeeId=(?) ");
				System.out.println("enter employee id to update:");
				int id = sc.nextInt();
				System.out.println("enter employee name to update:");
				String name = sc.nextLine();
				sc.nextLine();
				stmt.setString(1, name);
				stmt.setInt(2, id);

				int n = stmt.executeUpdate();
				if (n != 0) {
					System.out.println("record updated " + n);
				} else {
					System.out.println(" record not updated " + n);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	static void getAllRecord(Connection con) {
		try {
			Statement stmt = con.createStatement();
			ResultSet st = stmt.executeQuery("select * from employee");
			while (st.next()) {
				System.out.println("--------------------------------------------------------");
				System.out.println("    " + st.getInt(1) + "     " + st.getString(2) + "      " + st.getInt(3));
				System.out.println("----------------------------------------------------------");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		Connection con = null;
		int op = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajpanudip", "root", "Krish225s@");

		} catch (Exception e) {
			System.out.println(e);
		}

		do {
			System.out.println(
					"select Operation\n1.insert record\n2.update record\n3.getall record \n4. delete record \n5.exit");
			op = sc.nextInt();
			switch (op) {
			case 1:
				insertRecord(con);
				break;
			case 4:
				deleteRecord(con);
				break;
			case 3:
				getAllRecord(con);
				break;
			case 2:
				updateRecord(con);
				break;
			}

		} while (op != 5);

	}

}
