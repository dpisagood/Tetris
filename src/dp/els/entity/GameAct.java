package dp.els.entity;

import java.awt.Point;
import java.util.List;

import config.GameConfig;

//方块类
public class GameAct {
	//方块数组
     private Point[]  actPoint=null;
     //方块编号
      private int typeCode;
     
     private final static int MIN_X=GameConfig.getSystemConfig().getMinX();
     private final static int MAX_X=GameConfig.getSystemConfig().getMaxX();
     private final static int MIN_Y=GameConfig.getSystemConfig().getMinY();
     private final static int MAX_Y=GameConfig.getSystemConfig().getMaxY();
     
     private final static  List<Point[]> TYPE_CONFIG=GameConfig.getSystemConfig().getTypeConfig();
     private final static List<Boolean> TYPE_ROUND=GameConfig.getSystemConfig().getTypeRound();
     
     public GameAct(int typeCode){
        this.init(typeCode);
     }
     
     public void init(int typeCode){
    	 //方块编号
    	 this.typeCode = typeCode;
       	 // TODO  根据actCode的值刷新方块
    	 Point[] points= TYPE_CONFIG.get(typeCode);
    	 actPoint=new Point[points.length];
    	 for (int i = 0; i < points.length; i++) {
        	 actPoint[i] = new Point(points[i].x,points[i].y);
		}

     }
    /**
     * 方块移动
     * 
     * @parom moveX  x轴偏移量
     * @parom moveY  y轴偏移量
     * */
   public boolean move(int moveX,int moveY,boolean[][] gameMap){
	   for (int i = 0; i < actPoint.length; i++) {
		//移动处理
		  int newX=actPoint[i].x+moveX;
		  int newY=actPoint[i].y+moveY;
		if(isOverMap(newX,newY,gameMap)){
			return false;
		}
	}
		for(int i=0 ; i< actPoint.length; i++){
			 actPoint[i].x+=moveX;
			 actPoint[i].y+=moveY;
		}
	   return true;
  }
	public Point[] getActPoint() {
		return actPoint;
	}
	
	/**
	 * 方块旋转
	 * 顺时针：
	 * B.x = O.x - O.y + A.y
     * B.y = O.x + O.y - A.x
	 * */
	public void round(boolean[][]   gameMap){
		
		if(!TYPE_ROUND.get(this.typeCode)){
			return;
		}
	   for(int i=1;i<actPoint.length;i++){
		   int newX =actPoint[0].y + actPoint[0].x - actPoint[i].y;
		   int newY =actPoint[0].y - actPoint[0].x + actPoint[i].x;
		   if(this.isOverMap(newX, newY,gameMap) ){
			   return;
	   }
	}
	   for (int i = 0; i < actPoint.length; i++) {
		   int newX =actPoint[0].y + actPoint[0].x - actPoint[i].y;
		   int newY =actPoint[0].y - actPoint[0].x + actPoint[i].x;
		   actPoint[i].x=newX;
		   actPoint[i].y=newY;	
	}
	}
	/**
	 *判断是否超出边界
	 * */
	private boolean isOverMap(int x,int y,boolean[][]  gameMap){
		 return x < MIN_X || x > MAX_X || y < MIN_Y || y >MAX_Y || gameMap[x][y] ;
	}
    //获得方块类型编号 
	public int getTypeCode() {
		return typeCode;
	}
	
}












