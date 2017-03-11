package dp.els.ui;

import java.awt.Graphics;

import dp.els.config.FrameConfig;
import dp.els.config.GameConfig;

public class LayerLevel extends Layer {
	 FrameConfig cfg=GameConfig.getFrameConfig();
	 public  int windowsize= cfg.getBorder(); 
	 //����ͼƬ�Ŀ��
	 private static final int IMG_LV_W=Img.Level.getWidth(null);
	 
	 public LayerLevel(int x, int y,int w,int h){
		    	super(x,y,w,h);
		    }
		    
		  public void paint(Graphics g){
		    	this.createWindow(g);
		    	//���ƴ��ڱ���
		    	int centerX=this.w-IMG_LV_W>>1;
		    	g.drawImage(Img.Level , this.x+centerX, this.y+PADDING, null);
		    	//��ʾ�ȼ�
		    	this.drawNumberLeftPad(centerX,64,this.dto.getNowlevel(), 2,g);
		    }
		  
}

