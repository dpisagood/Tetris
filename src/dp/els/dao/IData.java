package dp.els.dao;

import java.util.List;

import dp.els.dto.Player;

//���ݲ�ӿ�
public interface IData {
	//�������
	public List<Player> getData();
	public void saveData(Player players);
	
}
