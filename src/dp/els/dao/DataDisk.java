package dp.els.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import dp.els.dto.Player;

/**
 * �Ա���Ӳ�����ݽ��в����ķ���
 * @author DP
 *
 */
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
		//��ȡ������
		List<Player> players =this.getData();
		//׷���¼�¼
		players.add(pla);
		//����д��
		ObjectOutputStream oos=null;
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
