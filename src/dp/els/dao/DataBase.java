package dp.els.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import dp.els.dto.Player;

public class DataBase implements IData {
	private final String dbUrl ;
	private final String dbUser ;
	private final String dbPwd ;
	private static final String GET_SQL = "select * from(select user_name,point from users where type_id=1 order by point desc) where rownum<=5";
	private static final String SAVE_SQL="insert into users (Id,user_name,point,type_id) values(?,?,?,?)";


	public DataBase(HashMap<String,String> param){
		this.dbUrl=param.get("dbUrl");
		this.dbUser=param.get("dbUser");
		this.dbPwd=param.get("dbPwd");
		try {
			Class.forName(param.get("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Player> getData() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Player> players = new ArrayList<Player>();
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			stmt = conn.prepareStatement(GET_SQL);
			rs = stmt.executeQuery();// 返回一个结果集
			while (rs.next()) {
				players.add(new Player(rs.getString(1), rs.getInt(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return players;
	}

	@Override
	public void saveData(Player players) {
		Random random=new Random();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			stmt=conn.prepareStatement(SAVE_SQL);
			stmt.setInt(1, random.nextInt(5));
			stmt.setString(2,players.getName());
			stmt.setInt(3, players.getPoint());
			stmt.setInt(4, 1);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	 public static void main(String[] args) throws Exception {
//	 DataBase db=new DataBase();
////	 List<Player> players=db.getData();
////	 for (Player p:players) {
////	 System.out.println("姓名："+p.getName()+"----"+p.getPoint());
////	 }
//	 	db.saveData(new Player("丁鹏",9999));
//	 }

}
