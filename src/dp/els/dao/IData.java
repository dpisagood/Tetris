package dp.els.dao;

import java.util.List;

import dp.els.bean.Player;

//���ݲ�ӿ�
public interface IData{
	//�������
	public List<Player> getData();
	//��������0
	public void saveData(Player players);
	
}
