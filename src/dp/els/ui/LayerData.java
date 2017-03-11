package dp.els.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import dp.els.config.GameConfig;
import dp.els.dto.Player;

	abstract public class LayerData  extends Layer {
	
		//���������
		private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();
		//��ʼy����
		private static int STATR_Y=0;
		//y����ÿ�����ӳ���
		private static final int RECT_H=IMG_RECT_H+4;
		//���
		private static  int SPA=0;
	
		public LayerData(int x,int y,int w,int h) {
			super(x,y,w,h);
			SPA=(this.h-RECT_H*5-(PADDING<<1)-Img.DataBase.getHeight(null)) / MAX_ROW;
			STATR_Y=PADDING+Img.DataBase.getHeight(null)+SPA;
		}
   
   
		@Override
	abstract public void paint(Graphics g) ;
		
		
   /**
    * ���Ƹ�����ֵ��
    * @param imgtitle ����ͼƬ
    * @param players ����Դ
    * @param g ����
    */
	public void showData(Image imgtitle,List<Player> players,Graphics g){
    	//��this��ʾ������꣨���������ʾ�Լ��Ŀ�Ĺս����꣩
		//���Ʊ���
    	g.drawImage(imgtitle, this.x+PADDING, this.y+PADDING, null);
    	//������ڵķ���
    	int nowPoint=this.dto.getNowPoint();
    	//ѭ�����Ƽ�¼
    	for (int i = 0; i < MAX_ROW; i++) { 
    		//���һ����Ҽ�¼
    		Player pla=players.get(i);
    		//��ø÷���
    		int point=pla.getPoint();
    		//�������ڷ������¼������ֵ
    		double percent=(double)nowPoint / point;
    		//����Ѿ��Ƽ�¼����ֵ��Ϊ100%
    		percent=percent>1?1.0:percent;
    		//���Ƶ�����¼
    		String strPoint=point==0?null:Integer.toString(point);
    		this.drawRect(STATR_Y+i*(RECT_H+SPA),
    				pla.getName(),strPoint, 
    				percent, g);
		}
	}
}
	
	
	
	
	
	
	
	
