
package com.vso;

import java.sql.Connection; // a connection to the DB
import java.sql.DriverManager; // handles communication with the DB
import java.sql.ResultSet; // a table of rows generated from an SQL query
import java.sql.Statement; // an SQL statement for the DB to execute
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties; // key/value pairs

import com.vso.models.Book;

public class MySqlTest {
	private Connection conn;

	public MySqlTest() {
		setupDbConnection();
		createTable();
		// insertData();
		readData();
	}

	public void setupDbConnection() {
		try {
			String uri = "jdbc:mysql://localhost:3306/library";
			Properties prop = setLoginForDB();
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(uri, prop);
		} catch (Exception e) {
			System.out.print("Opa");
			e.printStackTrace();
		}
	}

	public Properties setLoginForDB() {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "root");
		return props;
	}

	public void createTable() {
		try {
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE books (" + "id SERIAL PRIMARY KEY NOT NULL," + "name TEXT NOT NULL,"
					+ "author TEXT NOT NULL," + "date_of_publishing DATE NOT NULL," + "isbn TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertData() {
		for (int i = 0; i < 10; i++) {
			insertRow("Kniga" + i, "Avtor" + i, i + "");
		}
	}

	public void insertRow(String name, String author, String isbn) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO books (name, author, date_of_publishing, isbn) VALUES('" + name + "', '" + author
					+ "', '" + new java.sql.Date(new Date().getTime()) + "', '" + isbn + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readData() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books;");

			while (rs.next()) {
				int id = rs.getInt("id");
				String bookName = rs.getString("name");
				String bookAuthor = rs.getString("author");
				String bookIsbn = rs.getString("isbn");
				System.out.format("%d %s %s %s\n", id, bookName, bookAuthor, bookIsbn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> getBookInfo(String bookName) {
		List<Book> books = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books WHERE name LIKE '%" + bookName + "%';");

			while (rs.next()) {
				Book currentBook = new Book();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String bookAuthor = rs.getString("author");
				String bookIsbn = rs.getString("isbn");
				Date dateOfPublishing = rs.getDate("date_of_publishing");

				currentBook.setId(id);
				currentBook.setName(name);
				currentBook.setBookAuthor(bookAuthor);
				currentBook.setBookIsbn(bookIsbn);
				currentBook.setDateOfPublishing(dateOfPublishing);
				books.add(currentBook);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
}