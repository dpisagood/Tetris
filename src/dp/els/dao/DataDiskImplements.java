package dp.els.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dp.els.bean.Player;

public class DataDiskImplements extends DataDisk implements Serializable{
	private static final long serialVersionUID = 1L;


	public DataDiskImplements(HashMap<String, String> param) {
		super(param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getData() {
		ObjectInputStream ois=null;
		List<Player> players=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(filepath));
			players=(List<Player>)ois.readObject();
			if(players==null){
				players=new ArrayList<Player>();
			}
			//如果记录数小于5，那么就加到5条为止 
			while(players.size()<5){
				players.add(new Player("No Data",0));
			}
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}finally{
			try {
				if(ois!=null){
					ois.close();	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return players;
	}
	

	@Override
	public void saveData(Player player) {
		//重新写入
		ObjectOutputStream oos=null;
		List<Player> players=getData();
		players.add(player);
		try {
			oos=new ObjectOutputStream(new FileOutputStream("data/recode.dat"));
			oos.writeObject(players);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
