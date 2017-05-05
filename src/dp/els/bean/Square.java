package dp.els.bean;

import java.awt.Point;

import dp.els.util.ConfigConstant;

public class Square {
	//方块数组
    private Point[]  actPoint=null;
	//方块编号
    private int typeCode;
    public Square(int typeCode){
         this.init(typeCode);
    }
      
    public void init(int typeCode){
	 	 //方块编号
	 	 this.typeCode = typeCode;
	    	 // TODO  根据actCode的值刷新方块
	 	 Point[] points= ConfigConstant.TYPE_CONFIG.get(typeCode);
	 	 actPoint=new Point[points.length];
	 	 for (int i = 0; i < points.length; i++) {
	     	 actPoint[i] = new Point(points[i].x,points[i].y);
	 		}
      }
      
    public Point[] getActPoint() {
  		return actPoint;
  	}

  	public int getTypeCode() {
  		return typeCode;
  	}

}
