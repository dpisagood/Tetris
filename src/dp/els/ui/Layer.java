package dp.els.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import dp.els.bean.GameDto;
import dp.els.config.FrameConfig;
import dp.els.config.GameConfig;

/*���ƴ���
 * @dingpeng
 * */
abstract public class Layer {
	//�ڱ߾�
	protected static final int PADDING;
	protected static final int BORDER;//�������
	private static final int WINDOW_W;//��ȡ��������ȣ������ҵĳ���
	private static final int WINDOW_H; //��ȡ���������ȣ������µĳ��� 
	 //������Ƭ�Ŀ��
	 protected static final int TMG_NUMBER_W=Img.NUMBER.getWidth(null)/10;
	 //������Ƭ�ĸ߶�
	 private static final int TMG_NUMBER_H=Img.NUMBER.getHeight(null);
	 //����ֵ�ۣ��߶ȣ�
	 public static final int IMG_RECT_H=Img.RECT.getHeight(null);
	 //����ֵ��ͼƬ����ȣ�
	 private static final int IMG_RECT_W=Img.RECT.getWidth(null);
	 //����ֵ�۵Ŀ��
	private  final int rectW;
	 //Ĭ������
	 private static final Font DEF_FONT=new Font("����",Font.BOLD,20);
	static{
		//�����Ϸ����
		FrameConfig fCfg=GameConfig.getFrameConfig();
		 PADDING=fCfg.getPadding();
		 BORDER=fCfg.getBorder();
		 WINDOW_H=fCfg.getWindow_h();
		 WINDOW_W=fCfg.getWindow_w();
	}//ʹ�ô���齫������ʼ��
	 //�������Ͻ�x���� 
	protected  final int x;
     // �������Ͻ�y����
    protected  final int y;
    // ���ڿ��
    protected  final int w;
     //���ڸ߶�
    protected  final int h;
    //��Ϸ����    
    protected GameDto dto =null;
	public void setDto(GameDto dto) {
		this.dto = dto;
	}
	
    protected Layer(int x ,int y, int w, int h){
    	this.x =x;
    	this.y=y;
    	this.h=h;
    	this.w=w;
    	rectW=IMG_RECT_W+4;
    }
    
    
    /**
     * ˢ����Ϸ��������
     * @author dingpeng 
     * @param g ����
     * */
    abstract  public void paint(Graphics g);
    
    /**
     * ���ƴ���
     * @author dingpeng 
     * @param g ����
     * */
    protected void createWindow(Graphics g){//������Ե���
    	 //��
    	g.drawImage(Img.WINDOW,   x,              y,    x+w,   y+BORDER,     x,     y,   WINDOW_W,  BORDER, null);
   	     //��
   	    g.drawImage(Img.WINDOW,   x,          y+BORDER,   x+BORDER,  y+BORDER+h,  x,  y+BORDER, BORDER,  WINDOW_H, null);
   	     //��
        g.drawImage(Img.WINDOW, x+(w-BORDER),   y+BORDER,   x+w,    y+BORDER+h,   x,  y+BORDER, BORDER, WINDOW_H, null);
         //��
   	    g.drawImage(Img.WINDOW,   x,         y+BORDER+h,  x+w,    y+h+2*BORDER,  x,     y,   WINDOW_W,   BORDER, null);
    	
    }
	
	  /**
	   * ��ʾ����
	   * @param x ���Ͻ�x����
	   * @param y ���Ͻ�����
	   * @param  bitCount  ����λ��
	   * @param num ����ʾ������
	   * @param g ���ʶ���
	   */
	  protected void drawNumberLeftPad(int x,int y,int num,int maxBit,Graphics g){
		  //��Ҫ��ӡ������ת�����ַ���
		  String strNum=Integer.toString(num);
		  //ѭ�����������Ҷ���
		  for (int i = 0; i < maxBit; i++) {
			  //�ж��Ƿ������ӡ����
			  if(maxBit-i <= strNum.length()){
				  //����������ַ����е��±�
				  int idx=i-maxBit+strNum.length();
				  //������number�е�ÿһλȡ��
				  int bit=strNum.charAt(idx)-'0';
				  //��������
					g.drawImage(Img.NUMBER, 
							this.x+x+TMG_NUMBER_W * i, this.y+y,
							this.x+x+TMG_NUMBER_W * (i+1), this.y+y+TMG_NUMBER_H,
							bit*TMG_NUMBER_W, 0,
							(bit+1)*TMG_NUMBER_W, TMG_NUMBER_H, null); 
			  }
		}
	}
	  
	  
	  /**
	   * ����ֵ��
	   * @param value  ��λ����
	   * @param maxValue  �ܶ���
	   * @param g  ����
	   * @title �������
	   * @number 
	   */
	  	protected void drawRect(int y,String title,String number ,double percent,Graphics g){
	  	 		//����ֵ��ʼ��
	  	 		int rect_x =this.x+(PADDING<<1);
	  	 		int rect_y=this.y+y;
	  	 		//���Ʊ���
		    	g.setColor(Color.BLACK);
		    	g.fillRect(rect_x, rect_y,this.rectW,IMG_RECT_H+4);
		    	g.setColor(Color.WHITE);
		    	g.fillRect(rect_x+1, rect_y+1,this.rectW-2,IMG_RECT_H+2);
		    	g.setColor(Color.BLACK);
		    	g.fillRect(rect_x+2, rect_y+2,this.rectW-4, IMG_RECT_H);
		    	//������
		    	int w=(int)(percent*(this.rectW-4));
		    	//�����ɫ
		    	int subIdx=(int)(percent*IMG_RECT_W)-1;
		    	g.drawImage(Img.RECT, 
		    			rect_x+2,rect_y+2, 
		    			rect_x+2+w, rect_y+2+IMG_RECT_H, 
		    			subIdx, 0, 
		    			subIdx+1, IMG_RECT_H,
		    			null);
		    	g.setColor(Color.WHITE);
		    	g.setFont(DEF_FONT);
		    	g.drawString(title,rect_x+4,rect_y+22);
		    	if(number!=null){
		    		g.drawString(number,rect_x+200,rect_y+22);
		    	}
	  	 	}
	  	
	  	 
		  //���л�ͼ
		  protected void drawImageAtCenter(Image img,Graphics g){
			  int imgW=img.getWidth(null);
			  int imgH=img.getHeight(null);
			  g.drawImage(img, this.x+(this.w-imgW>>1), this.y+(this.h-imgH>>1), null);
		  }
    
}
