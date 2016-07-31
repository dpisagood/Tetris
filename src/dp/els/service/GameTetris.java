package dp.els.service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import dp.els.dto.GameDto;
import dp.els.dto.Player;
import dp.els.entity.GameAct;

public class GameTetris implements GameService{
	//游戏数据对象
	private GameDto dto;
	//随机数产生器
    private Random random =new Random();
    //方块种类个数
    private static final int   MAX_TYPE=6;
	public GameTetris(GameDto dto) {
		this.dto = dto;
		GameAct gameAct=new GameAct(random.nextInt( MAX_TYPE));
		dto.setGameAct(gameAct);
	}

	//方块操作（上，旋转）
	public void keyUp() {
      this.dto.getGameAct().round(this.dto.getGameMap());
	}
	//方块操作（下）
	public void keyDown() {
		//判断是否再能往下移动（如果能返回操作，如不能继续操作即堆积）
		if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())){
	        return;
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
		//TODO 判断是否可以消行
		//TODO 消行操作
		//TODO 算分操作
		//TODO 判断是否升级
		//TODO 升级
		//创建下一个方块
		this.dto.getGameAct().init(this.dto.getNext());
		//随机生成在下一个方块
		this.dto.setNext(random.nextInt( MAX_TYPE));
	}
	//方块操作（左）
	public void keyLeft() {
			this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());
	}
	//方块操作（右）
	public void keyRight() {
			this.dto.getGameAct().move(1, 0,this.dto.getGameMap());
	}
	

	@Override
	public void ketDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyFunUp() {
		System.out.println("正三角被按下");
	}

	@Override
	public void keyFunDown() {//作弊按钮
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

	@Override
	public void keyFunLeft() {
		System.out.println("方块被按下");
	}

	@Override
	public void keyFunRight() {
		System.out.println("圆圈被按下");
	}
	
	
	
	//将数据库数据存入dto
	public void setRecondeDataBase(List<Player> databaseplayers){
		this.dto.setDbRecode(databaseplayers);
	}
	//将本地磁盘数据存入dto
	public void setRecondeDisk(List<Player> diskplayers){
		this.dto.setDisRecode(diskplayers);
	}
}



//+++++++++++++++++++++++++++++++测试代码

//public void testlevelUp() {
//	int point =this.dto.getNowPoint();
//	int rmline=this.dto.getNowRemoveLine();
//	int lv =this.dto.getNowlevel();
//	point+=10;
//	rmline+=1;
//	if(rmline%20==0){
//		lv+=1;
//	}
//	this.dto.setNowPoint(point);
//	this.dto.setNowlevel(lv);
//	this.dto.setNowRemoveLine(rmline); 
//}