package dp.els.entity;

import java.awt.Point;

import dp.els.bean.Square;
import dp.els.config.GameConfig;
import dp.els.util.ConfigConstant;

/**
 * ���鶯���࣬�����ṩ�˷�����ƶ��ͷ�ת��ʵ�֡��������������ݵĵ�����
 * @author DP
 *
 */
public class GameAction {

	 private final static int MIN_X=GameConfig.getSystemConfig().getMinX();
     private final static int MAX_X=GameConfig.getSystemConfig().getMaxX();
     private final static int MIN_Y=GameConfig.getSystemConfig().getMinY();
     private final static int MAX_Y=GameConfig.getSystemConfig().getMaxY();
     
    /**
     * �����ƶ�
     * 
     * @parom moveX  x��ƫ����
     * @parom moveY  y��ƫ����
     * */
   public static boolean move(int moveX,int moveY,Square square){
	   Point [] actPoint=square.getActPoint();
	   for (int i = 0; i < actPoint.length; i++) {
		//�ƶ�����
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
	 * ������ת
	 * ˳ʱ�룺
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
	 *�ж��Ƿ񳬳��߽�
	 * */
	private static boolean isOverMap(int x,int y){
		 return x < MIN_X || x > MAX_X || y < MIN_Y || y >MAX_Y || ConfigConstant.gameMap[x][y] ;
	}
}












