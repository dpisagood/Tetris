package dp.els.service;

import java.util.List;

import dp.els.dto.Player;

public interface GameService {
	//�������
	public void keyUp();
	//�������
	public void ketDown();
	//�������
	public void keyLeft();
	//�������
	public void keyRight();
	
	public void keyFunUp();
	public void keyFunDown();
	public void keyFunLeft();
	public void keyFunRight();
	public void setRecondeDataBase(List<Player> databaseplayers);
	public void setRecondeDisk(List<Player> diskplayers);
	
}
