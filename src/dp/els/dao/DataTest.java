package dp.els.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dp.els.dto.Player;

public class DataTest implements IData {
	
	public DataTest(HashMap<String,String> param){}

	@Override
	public List<Player> getData() {
		List<Player> players=new ArrayList<Player>();
		players.add(new Player("��ѧ��",1000));
		players.add(new Player("��˳˳",2000));
		players.add(new Player("���ڷ�",3020));
		players.add(new Player("�κ콨",4020));
		return players;
	}

	@Override
	public void saveData(Player players) {
		
	}

}
