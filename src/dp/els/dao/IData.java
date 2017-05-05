package dp.els.dao;

import java.util.List;

import dp.els.bean.Player;

//数据层接口
public interface IData{
	//获得数据
	public List<Player> getData();
	//保存数据0
	public void saveData(Player players);
	
}
