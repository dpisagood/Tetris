package dp.els.service;

import java.util.List;

import dp.els.dto.Player;

public interface GameService {
	//方向键上
	public void keyUp();
	//方向键下
	public void ketDown();
	//方向键左
	public void keyLeft();
	//方向键右
	public void keyRight();
	
	public void keyFunUp();
	public void keyFunDown();
	public void keyFunLeft();
	public void keyFunRight();
	public void setRecondeDataBase(List<Player> databaseplayers);
	public void setRecondeDisk(List<Player> diskplayers);
	
}
