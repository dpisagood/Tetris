package dp.els.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * ��oracle���ݿ�Ĳ���
 * @author DP
 *
 */
public abstract class DataBase implements IData{
	protected final String dbUrl ;
	protected final String dbUser ;
	protected final String dbPwd ;
	protected final String driver;
	//���ݿ����ӳ�
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
	 * �õ����ݿ�����
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
	 * �ر����ݿ�����
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
