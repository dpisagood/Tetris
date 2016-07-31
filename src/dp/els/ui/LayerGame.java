package dp.els.ui;

import java.awt.Graphics;
import java.awt.Point;

import config.GameConfig;

public  class LayerGame extends Layer {
		private  static  final int LEFT_SIZE=GameConfig.getSystemConfig().getMinX();
		private  static final int RIGHT_SIZE=GameConfig.getSystemConfig().getMaxX();
		private static int SIZE_ROL=GameConfig.getFrameConfig().getSizeRol();     
		public LayerGame(int x, int y,int w,int h){
		    	super(x,y,w,h);
		    }
		    
	 public void paint(Graphics g){
		    	this.createWindow(g); 
		    	//获得方块数组集合
 		        Point[] points=this.dto.getGameAct().getActPoint();
 		        //绘制阴影 
 		        this.drawShadow(points, true,g);
 		        //获得方块 类型编号（0——6）
 		        int typeCode=this.dto.getGameAct().getTypeCode();
 		        //绘制方块
 		        for(int i=0; i < points.length;i++){
 		        drawActByPoint(points[i].x, points[i].y, typeCode+1, g);
 		       }
 		        //打印地图
 		          boolean[][] map=this.dto.getGameMap();
 		          //计算当前堆积颜色
 		          int lv =this.dto.getNowlevel();
 		          //小算法
 		          int imgIdx = lv==0?0:(lv % 7+1);
 		          //TODO 如果输了 imgIdx=8
 		          for (int x = 0; x < map.length; x++) {
					for (int y = 0; y < map[x].length; y++) {
						if(map[x][y]){
			             drawActByPoint(x, y, imgIdx, g);
			             }
				}
			}
		 }
	 
	 	
	public void drawShadow(Point[] points,boolean isShowShadow,Graphics g){
		if(!isShowShadow){
			return;
		}
		int leftX=RIGHT_SIZE;
		int rightX=LEFT_SIZE;
		for (Point p:points){
			leftX=p.x<leftX?p.x:leftX;
			rightX=p.x>rightX?p.x:rightX;
		} 
		g.drawImage(Img.SHODOW,
				this.x+BORDER+(leftX<<SIZE_ROL),
				this.y+BORDER,
				(rightX-leftX+1)<<SIZE_ROL, 
				this.h-(BORDER<<1),
				null);
	}
		//绘制正方形块  
	private void drawActByPoint(int x, int y, int imgIdx, Graphics g){
	      g.drawImage(Img.game, 
		    		  this.x+5+(x<<SIZE_ROL),
		    		  this.y+5+(y<<SIZE_ROL), 
		    		  this.x+5+(x+1<<SIZE_ROL), 
		    		  this.y+5+(y+1<<SIZE_ROL), 
		    		 imgIdx<<SIZE_ROL, 0, (imgIdx+1)<<SIZE_ROL, 1<<SIZE_ROL, null);
		       }
	}  
		  
		  

