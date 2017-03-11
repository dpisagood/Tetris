package dp.els.ui;

import java.awt.Graphics;

import dp.els.config.GameConfig;

public class LayerPoint extends Layer {
	//升级函数
	 private final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUP();
	 //将不变的值在程序加载的时候就计算完成
	 private  final int rmlineY;
	 //分数最高位
	 private  final int POINT_BIT=5;
	 //分数y 坐标
	 private  final int pointY; 
	 //经验值y坐标
	 private final int expY;
	 //消行X坐标
	 private  final int comX;
	 public LayerPoint(int x, int y,int w,int h){
	    	super(x,y,w,h);
	    	//初始化共同的X坐标
	    	this.comX=this.w -TMG_NUMBER_W *POINT_BIT-PADDING;
	    	//初始化分数显示的y坐标
	    	this.pointY=PADDING;
	    	//初始化消行显示y坐标
	    	this.rmlineY=this.pointY+Img.POINT.getHeight(null)+PADDING;
	    	//初始化经验值显示的y坐标
	    	this.expY=this.rmlineY+Img.RMLINE.getHeight(null)+PADDING;
	    }
	    
	  public void paint(Graphics g){
	    	this.createWindow(g);
	    	//绘制标题(分数)
	    	g.drawImage(Img.POINT , this.x+pointY, this.y+pointY, null);
	    	//显示分数
	    	this.drawNumberLeftPad(comX,pointY,this.dto.getNowPoint(), POINT_BIT,g);
	     	//绘制标题(消行
	    	g.drawImage( Img.RMLINE , this.x+PADDING, this.y+rmlineY, null);
	    	//显示消行
	    	this.drawNumberLeftPad(comX,rmlineY,this.dto.getNowRemoveLine(), POINT_BIT,g);
	    	//绘制值槽（经验值）
	    	int rmline=this.dto.getNowRemoveLine();
	    	this.drawRect(expY,"下一级",null,(double)(rmline % LEVEL_UP)/(double)LEVEL_UP,g);
	    }
}


 
 
 
 
