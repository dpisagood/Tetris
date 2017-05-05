package dp.els.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import dp.els.bean.Player;

public class DataBaseImplements extends DataBase implements Serializable{
	private static final long serialVersionUID = 1L;
	protected   final String GET_SQL = "select * from(select user_name,point from users where type_id=1 order by point desc) where rownum<=5";
	protected   final String SAVE_SQL="insert into users (Id,user_name,point,type_id) values(?,?,?,?)";
	public DataBaseImplements(HashMap<String, String> param) {
		super(param);
	}
	
	@Override
	public List<Player> getData() {
		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = getConnection().prepareStatement(GET_SQL);
			rs = stmt.executeQuery();// 返回一个结果集
			ArrayList<Player> players=new ArrayList<>();
			while (rs.next()) {
				players.add(new Player(rs.getString(1), rs.getInt(2)));
			}
			return players;
		} catch (SQLException e) {
			System.out.println("没有检测到可用的数据库");
			return null;
		}finally{
			closeConnection();
		}
	}

	@Override
	public void saveData(Player players) {
		Random random=new Random();
		PreparedStatement stmt = null;
		try {
			stmt=getConnection().prepareStatement(SAVE_SQL);
			stmt.setInt(1, random.nextInt(5));
			stmt.setString(2,players.getName());
			stmt.setInt(3, players.getPoint());
			stmt.setInt(4, 1);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("没有检测到可用的数据库");
		}finally{
				closeConnection();
			}
		}
	}

