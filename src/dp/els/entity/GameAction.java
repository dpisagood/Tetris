package dp.els.entity;

import java.awt.Point;

import dp.els.bean.Square;
import dp.els.config.GameConfig;
import dp.els.util.ConfigConstant;

/**
 * 方块动作类，里面提供了方块的移动和反转的实现。（方块坐标数据的调整）
 * @author DP
 *
 */
public class GameAction {

	 private final static int MIN_X=GameConfig.getSystemConfig().getMinX();
     private final static int MAX_X=GameConfig.getSystemConfig().getMaxX();
     private final static int MIN_Y=GameConfig.getSystemConfig().getMinY();
     private final static int MAX_Y=GameConfig.getSystemConfig().getMaxY();
     
    /**
     * 方块移动
     * 
     * @parom moveX  x轴偏移量
     * @parom moveY  y轴偏移量
     * */
   public static boolean move(int moveX,int moveY,Square square){
	   Point [] actPoint=square.getActPoint();
	   for (int i = 0; i < actPoint.length; i++) {
		//移动处理
		  int newX=actPoint[i].x+moveX;
		  int newY=actPoint[i].y+moveY;
		if(isOverMap(newX,newY)){
			return false;
		}
	}
		for(int i=0 ; i< actPoint.length; i++){
			 actPoint[i].x+=moveX;
			 actPoint[i].y+=moveY;
		}
	   return true;
  }

	/**
	 * 方块旋转
	 * 顺时针：
	 * B.x = O.x - O.y + A.y
     * B.y = O.x + O.y - A.x
	 * */
	public static void round(Square square){
		Point [] actPoint=square.getActPoint();
		if(!ConfigConstant.TYPE_ROUND.get(square.getTypeCode())){
			return;
		}
	   for(int i=1;i<actPoint.length;i++){
		   int newX =actPoint[0].y + actPoint[0].x - actPoint[i].y;
		   int newY =actPoint[0].y - actPoint[0].x + actPoint[i].x;
		   if(isOverMap(newX, newY) ){
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
	private static boolean isOverMap(int x,int y){
		 return x < MIN_X || x > MAX_X || y < MIN_Y || y >MAX_Y || ConfigConstant.gameMap[x][y] ;
	}
}












