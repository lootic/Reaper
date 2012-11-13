package com.reaper.server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.reaper.shared.Topic;


/**
 * Create as Singleton class.
 * 
 * @author lootic
 * 
 */
public class DatabaseManagement {

	private volatile static boolean isInitiated = false;

	public static void initIfNotInitiated() {
		if (!isInitiated) {
			isInitiated = true;
			try {
				Class.forName ("org.sqlite.JDBC");
				Statement statement = DriverManager.getConnection(
						"jdbc:sqlite:reaper.db").createStatement();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static boolean createTopic(String title, String firstpost,
			String user, int tag) {
		try {
			initIfNotInitiated();
			Statement statement = DriverManager.getConnection(
					"jdbc:sqlite:data.db").createStatement();

			statement.execute("INSERT INTO topic (title, tag) VALUES (" + title
					+ "," + tag + ");");
			ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM topic;");
			System.out.println(rs.getInt(0));
			statement
					.execute("INSERT INTO post (content,date,user,topic) VALUES ("
							+ firstpost
							+ ", datetime('now')"
							+ user
							+ ","
							+ rs.getInt(0) + ");");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	public static ArrayList<Topic> getTopicsMatching() {
		initIfNotInitiated();
		ArrayList<Topic> topics = new ArrayList<Topic>();
		Topic topic;
		Statement statement;
		
		try {
			statement = DriverManager.getConnection(
					"mysql:reaper").createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM topic;");
			while(rs.next()){
				topic = new Topic();
				System.out.println(rs.getInt(0) + " " + rs.getString(1) + rs.getInt(2));
				topic.setTitle(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return topics;
	}
}
