package dp.els.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import dp.els.dto.Player;

public class DataDisk implements IData{
	private  final String filepath;
	@SuppressWarnings("unchecked")
	@Override
	
	public List<Player> getData() {
		ObjectInputStream ois=null;
		List<Player> players=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(filepath));
			players=(List<Player>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return players;
	}
	
public DataDisk(HashMap<String,String> param){
		this.filepath=param.get("path");
	}

	@Override
	public void saveData(Player pla) {
		//先取出数据
		List<Player> players =this.getData();
		//TODO 判断数据记录是否超过5条，超过的话，去掉分数低的。
//		if(players.size()>5){
//			players.sort(null);
//		}
		//追加新纪录
		players.add(pla);
		//重新写入
		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream("save/recode.dat"));
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
