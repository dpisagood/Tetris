 package dp.els.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.GameConfig;
import dp.els.entity.GameAct;
//数据类
public class GameDto {
	private static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	private static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
	//数据库记录
    private List<Player> dbRecode;
    //本地硬盘记录
    private List<Player> disRecode;
     private boolean[][] gameMap;
     private  GameAct gameAct;//下落方块
     private int next;//下一个方块
     private int nowlevel;//等级
     private int nowPoint ;//现在的分数
     private int nowRemoveLine;//消行
     
     
     public GameDto(){
    	 dtoInit();
     }
     
     public  void dtoInit() {
		this.gameMap=new boolean[GAMEZONE_W][GAMEZONE_H];
	}

	public int getNowlevel() {
		return nowlevel;
	}

	public void setNowlevel(int nowlevel) {
		this.nowlevel = nowlevel;
	}

	
	public List<Player> getDbRecode() {
		return dbRecode;
	}
	
	public void setDbRecode(List<Player> dbRecode) {
//		if(dbRecode==null){
//			dbRecode=new ArrayList<Player>();
//		}
//		while(dbRecode.size()<5){
//			dbRecode.add(new Player("No Data",0));
//		}
//		Collections.sort(dbRecode);
		this.Sort(dbRecode);
		this.dbRecode = dbRecode;
	}
	
	public List<Player> getDisRecode() {
		return disRecode;
	}
	
	public void setDisRecode(List<Player> disRecode) {
		this.Sort(disRecode);
		this.disRecode = disRecode;
	}
	
	/**
	 * 排序和当数据库中没有玩家数据时提供一个0分数player
	 * @param pla  需要排序的或者可能有空的用户数组
	 */
	private void Sort(List<Player> pla){
		//如果进来的是空，那么就创建
		if(pla==null){
			pla=new ArrayList<Player>();
		}
		//如果记录数小于5，那么就加到5条为止 
		while(pla.size()<5){
			pla.add(new Player("No Data",0));
		}
		Collections.sort(pla);
	}
	
	public boolean[][] getGameMap() {
		return gameMap;
	}
	
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	
	public GameAct getGameAct() {
		return gameAct;
	}
	
	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}
	
	public int getNext() {
		return next;
	}
	
	public void setNext(int next) {
		this.next = next;
	}
	
	
	public int getNowPoint() {
		return nowPoint;
	}
	
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	
	public int getNowRemoveLine() {
		return nowRemoveLine;
	}
	
	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}
	  
}
