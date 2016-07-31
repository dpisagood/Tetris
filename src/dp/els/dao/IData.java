package dp.els.dao;

import java.util.List;

import dp.els.dto.Player;

//数据层接口
public interface IData {
	//获得数据
	public List<Player> getData();
	public void saveData(Player players);
	
}
