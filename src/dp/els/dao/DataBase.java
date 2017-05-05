package dp.els.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 对oracle数据库的操作
 * @author DP
 *
 */
public abstract class DataBase implements IData{
	protected final String dbUrl ;
	protected final String dbUser ;
	protected final String dbPwd ;
	protected final String driver;
	//数据库连接池
	private  ThreadLocal<Connection> connContainer=new ThreadLocal<Connection>();;
	public DataBase(HashMap<String,String> param){
		this.dbUrl=param.get("dbUrl");
		this.dbUser=param.get("dbUser");
		this.dbPwd=param.get("dbPwd");
		this.driver=param.get("driver");
		try {
			Class.forName(param.get("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 得到数据库连接
	 * @return
	 * @throws SQLException 
	 */
	protected Connection getConnection() throws SQLException{
		Connection conn = connContainer.get();
		if(conn==null){
				conn=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			connContainer.set(conn);
		}
			return conn;
	}
	
	/**
	 * 关闭数据库连接
	 */
	protected void closeConnection(){
		Connection con=connContainer.get();
		try {
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connContainer.remove();
		}
	}
}
