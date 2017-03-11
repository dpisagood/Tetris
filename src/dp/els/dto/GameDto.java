 package dp.els.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dp.els.config.GameConfig;
import dp.els.entity.GameAct;
import dp.els.util.GameFunction;
//数据类
public class GameDto {
	//游戏宽度
	public static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	//游戏高度
	public static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
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
     private boolean start;//游戏是否是开始状态
     private boolean showShadow;//是否显示阴影
     private boolean pause;//暂停
     private long sleepTime;//线程休眠时间
     
     public long getSleepTime() {
		return sleepTime;
	}

	public boolean isPause() {
		return pause;
	}

	public void changePause() {
			this.pause = !this.pause;
	}

	public boolean isShowShadow() {
		return showShadow;
	}

	public void changeShowShadow() {
		this.showShadow=!this.showShadow;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public GameDto(){
    	 dtoInit();
     }
     
	/**
	 * 输了之后重新开始初始化游戏的各个参数
	 */
     public  void dtoInit() {
		this.gameMap=new boolean[GAMEZONE_W][GAMEZONE_H];
		this.nowlevel=1;
		this.nowPoint=0;
		this.nowRemoveLine=0;
		this.pause=false;
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowlevel);
	}

	public int getNowlevel() {
		return nowlevel;
	}

	public void setNowlevel(int nowlevel) {
		this.nowlevel = nowlevel<16?nowlevel:16;
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowlevel);
	}

	
	public List<Player> getDbRecode() {
		return dbRecode;
	}
	
	public void setDbRecode(List<Player> dbRecode) {
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
