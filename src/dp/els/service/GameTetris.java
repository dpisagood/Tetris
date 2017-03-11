package dp.els.service;

import java.awt.Point;
import java.util.Random;

import dp.els.config.GameConfig;
import dp.els.dto.GameDto;
import dp.els.entity.GameAct;

public class GameTetris implements GameService{
	//游戏数据对象
	private GameDto dto;
	//随机数产生器
    private Random random =new Random();
    //方块种类个数
    private static final int   MAX_TYPE=GameConfig.getSystemConfig().getTypeConfig().size()-1;

    public GameTetris(GameDto dto) {
		this.dto = dto;
	}

	
	//方块操作（上，旋转）
	public boolean keyUp() {
	//如果按了暂停。这个键就不能再按
	if(this.dto.isPause()){
			return true;
	}
	//线程锁
	  synchronized (this.dto) {
		  this.dto.getGameAct().round(this.dto.getGameMap());
	}
      return true;
	}
	
	
	//方块操作（下）
	public boolean keyDown() {
		//如果按了暂停。这个键就不能再按
		if(this.dto.isPause()){
				return true;
		}
		  synchronized (this.dto) {//同步控制块，保护dto对象不同时被多个线程访问
		//判断是否再能往下移动（如果能返回操作，如不能继续操作即堆积）
		if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())){
	        return false;
		}
		//获得游戏地图对象
		//引用传递，如果在此之前被内容被改变，就相当于你所指向的内容被改变 
		boolean[][] map=this.dto.getGameMap();
		//获得方块对象
		Point[] act =this.dto.getGameAct().getActPoint();
		//将方块堆积到地图数组
		for (int i = 0; i < act.length; i++) {
			map[act[i].x][act[i].y]=true;
		}
		int  plusExp=this.plusExp();
		if(plusExp>0){
			this.plusPoint(plusExp);
		}
		//创建下一个方块
		this.dto.getGameAct().init(this.dto.getNext());
		//随机生成在下一个方块
		this.dto.setNext(random.nextInt( MAX_TYPE));
		//检查游戏是否失败
		if(this.isLose()){
			//游戏输掉之后结束游戏
			this.dto.setStart(false);
		}
	}
		return true;
	}
	
	//检查游戏是否失败
	private boolean isLose(){
		//获得现在的活动方块
		Point[] actPoints=this.dto.getGameAct().getActPoint();
		boolean[][] map=this.dto.getGameMap();
		for (int i = 0; i < actPoints.length; i++) {
			if(map[actPoints[i].x][actPoints[i].y]){
				return true;
			}
		}
		return false;
	}

	//消行后进行升级
	//加分方法
	private void plusPoint(int plusExp) {
		int point =this.dto.getNowPoint();
		int rmline=this.dto.getNowRemoveLine();
		int lv =this.dto.getNowlevel();
		point+=10;
		rmline+=1;
		if(rmline%20==0){
			lv+=1;
		}
		this.dto.setNowPoint(point);
		this.dto.setNowlevel(lv);
		this.dto.setNowRemoveLine(rmline); 
	}

	//升级操作
	//消行操作
	private int plusExp() {
		int i=0;
		//获得地图
		boolean [][] map=this.dto.getGameMap();
		//扫描地图。查看是否有可消行
		for (int y = 0; y <GameDto.GAMEZONE_H; y++) {
			//判断是否可消行
			if( isCanRemoveLine( y,map)){
				//消行
				this.removeLine( y,map);
				i++;
			}
		}
		return i;
	}
	
	//判断是否可以消行
	//判断一行是否可消除
	private boolean isCanRemoveLine(int y,boolean[][] map){
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			if(!map[x][y]){
				//如果有一个方格为false则直接跳到下一行
				return false;
			}
		}
		return true;
	}

	//消行操作
	private void removeLine(int rowNumber,boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			for (int y = rowNumber; y >0; y--) {
				map[x][y]=map[x][y-1];	
			}
			map[x][0]=false;
		}
	}

	
	//方块操作（左）
	public boolean keyLeft() {
		//如果按了暂停。这个键就不能再按
		if(this.dto.isPause()){
				return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());
		}
			return true;
	}
	//方块操作（右）
	public boolean keyRight() {
		//如果按了暂停。这个键就不能再按
		if(this.dto.isPause()){
				return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(1, 0,this.dto.getGameMap());
		}
			return true;
	}
	

	/**
	 * 作弊键
	 */
	@Override
	public boolean keyFunUp() {
		this.plusPoint(4);
		return true;
	}

	@Override//急速下降
	public boolean keyFunDown() {
		//如果按了暂停。这个键就不能再按
		if(this.dto.isPause()){
				return true;
		}
		if(this.dto.isStart()) {
			while(!this.keyDown());
   		}
		return true;
	}

	@Override//阴影开关
	public boolean keyFunLeft() {
		this.dto.changeShowShadow();
		return true;
	}

	//暂停
	@Override
	public boolean keyFunRight() {
		//开始之后才能暂停
		if(this.dto.isStart()){
			this.dto.changePause();
		}
		return true;
	}
	
	
	//启动主线程开始游戏
	@Override
	public void startGame() {
		//随机生成下一个方块
		this.dto.setNext(random.nextInt( MAX_TYPE));
		//随机生产现在方块
		dto.setGameAct(new GameAct(random.nextInt( MAX_TYPE)));
		//把游戏状态设为开始
		this.dto.setStart(true);
		//为重新开始初始化参数 
		this.dto.dtoInit();
	}
}

