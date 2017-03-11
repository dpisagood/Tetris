package dp.els.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import dp.els.config.GameConfig;
import dp.els.dto.Player;

	abstract public class LayerData  extends Layer {
	
		//最大数据行
		private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();
		//起始y坐标
		private static int STATR_Y=0;
		//y坐标每次增加长度
		private static final int RECT_H=IMG_RECT_H+4;
		//间距
		private static  int SPA=0;
	
		public LayerData(int x,int y,int w,int h) {
			super(x,y,w,h);
			SPA=(this.h-RECT_H*5-(PADDING<<1)-Img.DataBase.getHeight(null)) / MAX_ROW;
			STATR_Y=PADDING+Img.DataBase.getHeight(null)+SPA;
		}
   
   
		@Override
	abstract public void paint(Graphics g) ;
		
		
   /**
    * 绘制该所有值槽
    * @param imgtitle 标题图片
    * @param players 数据源
    * @param g 画笔
    */
	public void showData(Image imgtitle,List<Player> players,Graphics g){
    	//加this表示相对坐标（字相对于显示自己的框的拐角坐标）
		//绘制标题
    	g.drawImage(imgtitle, this.x+PADDING, this.y+PADDING, null);
    	//获得现在的分数
    	int nowPoint=this.dto.getNowPoint();
    	//循环绘制记录
    	for (int i = 0; i < MAX_ROW; i++) { 
    		//获得一条玩家记录
    		Player pla=players.get(i);
    		//获得该分数
    		int point=pla.getPoint();
    		//计算现在分数与记录分数比值
    		double percent=(double)nowPoint / point;
    		//如果已经破记录，比值设为100%
    		percent=percent>1?1.0:percent;
    		//绘制单条记录
    		String strPoint=point==0?null:Integer.toString(point);
    		this.drawRect(STATR_Y+i*(RECT_H+SPA),
    				pla.getName(),strPoint, 
    				percent, g);
		}
	}
}
	
	
	
	
	
	
	
	
