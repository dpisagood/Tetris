package dp.els.bean;

import java.awt.Point;

import dp.els.util.ConfigConstant;

public class Square {
	//��������
    private Point[]  actPoint=null;
	//������
    private int typeCode;
    public Square(int typeCode){
         this.init(typeCode);
    }
      
    public void init(int typeCode){
	 	 //������
	 	 this.typeCode = typeCode;
	    	 // TODO  ����actCode��ֵˢ�·���
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
