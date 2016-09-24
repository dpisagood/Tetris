package dp.els.ui;

import java.awt.Graphics;
import java.awt.Point;

import config.GameConfig;
import dp.els.entity.GameAct;

public  class LayerGame extends Layer {
		private  static  final int LEFT_SIZE=GameConfig.getSystemConfig().getMinX();
		private  static final int RIGHT_SIZE=GameConfig.getSystemConfig().getMaxX();
		private static int SIZE_ROL=GameConfig.getFrameConfig().getSizeRol();     
		private static final int LOSE_IDX=GameConfig.getFrameConfig().getLoseIdx();
		public LayerGame(int x, int y,int w,int h){
		    	super(x,y,w,h);
		    }
		    
	 public void paint(Graphics g){
	        this.createWindow(g);
	        GameAct act=this.dto.getGameAct();
	        if(act!=null){
	        	//获得方块数组集合
		        Point[] points=act.getActPoint();
		        //绘制阴影 
		        this.drawShadow(points,g);
			    //绘制活动方块
		        this.drawMainAct(points,g);
	        }
        	//绘制地图
		    this.drawMap(g);
		    //暂停
		    if(this.dto.isPause()){
		    	this.drawImageAtCenter(Img.PAUSE, g);
		    }
	       }
	 
 	//绘制地图
	private void drawMap(Graphics g) {
	        //打印地图
         boolean[][] map=this.dto.getGameMap();
         //计算当前堆积颜色
         int lv =this.dto.getNowlevel();
         //小算法
         int imgIdx = lv==0?0:(lv % 7+1);
         for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if(map[x][y]){
	             this.drawActByPoint(x, y, imgIdx, g);
	           }
			}
         }
		}

	//绘制活动方块
	private void drawMainAct(Point[] points,Graphics g) {
        //获得方块 类型编号（0——6）
        int typeCode=this.dto.getGameAct().getTypeCode();
        typeCode=this.dto.isStart()?typeCode:LOSE_IDX;
        //绘制方块
        for(int i=0; i < points.length;i++){
        this.drawActByPoint(points[i].x, points[i].y, typeCode+1, g);	
        }
	}

	public void drawShadow(Point[] points,Graphics g){
		if(!this.dto.isShowShadow()){
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
		imgIdx=this.dto.isStart()?imgIdx:LOSE_IDX;
	      g.drawImage(Img.game, 
		    		  this.x+BORDER+(x<<SIZE_ROL),
		    		  this.y+BORDER+(y<<SIZE_ROL), 
		    		  this.x+BORDER+(x+1<<SIZE_ROL), 
		    		  this.y+BORDER+(y+1<<SIZE_ROL), 
		    		 imgIdx<<SIZE_ROL, 0, (imgIdx+1)<<SIZE_ROL, 1<<SIZE_ROL, null);
		       }
	}  
		  
		  

