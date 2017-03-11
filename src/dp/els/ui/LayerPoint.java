package dp.els.ui;

import java.awt.Graphics;

import dp.els.config.GameConfig;

public class LayerPoint extends Layer {
	//��������
	 private final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUP();
	 //�������ֵ�ڳ�����ص�ʱ��ͼ������
	 private  final int rmlineY;
	 //�������λ
	 private  final int POINT_BIT=5;
	 //����y ����
	 private  final int pointY; 
	 //����ֵy����
	 private final int expY;
	 //����X����
	 private  final int comX;
	 public LayerPoint(int x, int y,int w,int h){
	    	super(x,y,w,h);
	    	//��ʼ����ͬ��X����
	    	this.comX=this.w -TMG_NUMBER_W *POINT_BIT-PADDING;
	    	//��ʼ��������ʾ��y����
	    	this.pointY=PADDING;
	    	//��ʼ��������ʾy����
	    	this.rmlineY=this.pointY+Img.POINT.getHeight(null)+PADDING;
	    	//��ʼ������ֵ��ʾ��y����
	    	this.expY=this.rmlineY+Img.RMLINE.getHeight(null)+PADDING;
	    }
	    
	  public void paint(Graphics g){
	    	this.createWindow(g);
	    	//���Ʊ���(����)
	    	g.drawImage(Img.POINT , this.x+pointY, this.y+pointY, null);
	    	//��ʾ����
	    	this.drawNumberLeftPad(comX,pointY,this.dto.getNowPoint(), POINT_BIT,g);
	     	//���Ʊ���(����
	    	g.drawImage( Img.RMLINE , this.x+PADDING, this.y+rmlineY, null);
	    	//��ʾ����
	    	this.drawNumberLeftPad(comX,rmlineY,this.dto.getNowRemoveLine(), POINT_BIT,g);
	    	//����ֵ�ۣ�����ֵ��
	    	int rmline=this.dto.getNowRemoveLine();
	    	this.drawRect(expY,"��һ��",null,(double)(rmline % LEVEL_UP)/(double)LEVEL_UP,g);
	    }
}


 
 
 
 
