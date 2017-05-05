package dp.els.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import dp.els.bean.GameDto;
import dp.els.config.FrameConfig;
import dp.els.config.GameConfig;

/*绘制窗口
 * @dingpeng
 * */
abstract public class Layer {
	//内边距
	protected static final int PADDING;
	protected static final int BORDER;//线条宽度
	private static final int WINDOW_W;//截取的线条宽度，即向右的长度
	private static final int WINDOW_H; //截取的线条长度，即向下的长度 
	 //数字切片的宽度
	 protected static final int TMG_NUMBER_W=Img.NUMBER.getWidth(null)/10;
	 //数字切片的高度
	 private static final int TMG_NUMBER_H=Img.NUMBER.getHeight(null);
	 //矩形值槽（高度）
	 public static final int IMG_RECT_H=Img.RECT.getHeight(null);
	 //矩形值槽图片（宽度）
	 private static final int IMG_RECT_W=Img.RECT.getWidth(null);
	 //经验值槽的宽度
	private  final int rectW;
	 //默认字体
	 private static final Font DEF_FONT=new Font("黑体",Font.BOLD,20);
	static{
		//获得游戏配置
		FrameConfig fCfg=GameConfig.getFrameConfig();
		 PADDING=fCfg.getPadding();
		 BORDER=fCfg.getBorder();
		 WINDOW_H=fCfg.getWindow_h();
		 WINDOW_W=fCfg.getWindow_w();
	}//使用代码块将常量初始化
	 //窗口左上角x坐标 
	protected  final int x;
     // 窗口左上角y坐标
    protected  final int y;
    // 窗口宽度
    protected  final int w;
     //窗口高度
    protected  final int h;
    //游戏数据    
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
     * 刷新游戏具体内容
     * @author dingpeng 
     * @param g 画笔
     * */
    abstract  public void paint(Graphics g);
    
    /**
     * 绘制窗口
     * @author dingpeng 
     * @param g 画笔
     * */
    protected void createWindow(Graphics g){//子类可以调用
    	 //上
    	g.drawImage(Img.WINDOW,   x,              y,    x+w,   y+BORDER,     x,     y,   WINDOW_W,  BORDER, null);
   	     //左
   	    g.drawImage(Img.WINDOW,   x,          y+BORDER,   x+BORDER,  y+BORDER+h,  x,  y+BORDER, BORDER,  WINDOW_H, null);
   	     //右
        g.drawImage(Img.WINDOW, x+(w-BORDER),   y+BORDER,   x+w,    y+BORDER+h,   x,  y+BORDER, BORDER, WINDOW_H, null);
         //下
   	    g.drawImage(Img.WINDOW,   x,         y+BORDER+h,  x+w,    y+h+2*BORDER,  x,     y,   WINDOW_W,   BORDER, null);
    	
    }
	
	  /**
	   * 显示数字
	   * @param x 左上角x坐标
	   * @param y 左上角坐标
	   * @param  bitCount  数字位数
	   * @param num 更显示的数字
	   * @param g 画笔对象
	   */
	  protected void drawNumberLeftPad(int x,int y,int num,int maxBit,Graphics g){
		  //把要打印的数字转换成字符串
		  String strNum=Integer.toString(num);
		  //循环绘制数字右对齐
		  for (int i = 0; i < maxBit; i++) {
			  //判断是否满足打印条件
			  if(maxBit-i <= strNum.length()){
				  //获得数字在字符串中的下标
				  int idx=i-maxBit+strNum.length();
				  //把数字number中的每一位取出
				  int bit=strNum.charAt(idx)-'0';
				  //绘制数字
					g.drawImage(Img.NUMBER, 
							this.x+x+TMG_NUMBER_W * i, this.y+y,
							this.x+x+TMG_NUMBER_W * (i+1), this.y+y+TMG_NUMBER_H,
							bit*TMG_NUMBER_W, 0,
							(bit+1)*TMG_NUMBER_W, TMG_NUMBER_H, null); 
			  }
		}
	}
	  
	  
	  /**
	   * 绘制值槽
	   * @param value  单位度量
	   * @param maxValue  总度量
	   * @param g  画笔
	   * @title 玩家名称
	   * @number 
	   */
	  	protected void drawRect(int y,String title,String number ,double percent,Graphics g){
	  	 		//各种值初始化
	  	 		int rect_x =this.x+(PADDING<<1);
	  	 		int rect_y=this.y+y;
	  	 		//绘制背景
		    	g.setColor(Color.BLACK);
		    	g.fillRect(rect_x, rect_y,this.rectW,IMG_RECT_H+4);
		    	g.setColor(Color.WHITE);
		    	g.fillRect(rect_x+1, rect_y+1,this.rectW-2,IMG_RECT_H+2);
		    	g.setColor(Color.BLACK);
		    	g.fillRect(rect_x+2, rect_y+2,this.rectW-4, IMG_RECT_H);
		    	//求出宽度
		    	int w=(int)(percent*(this.rectW-4));
		    	//求出颜色
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
	  	
	  	 
		  //正中绘图
		  protected void drawImageAtCenter(Image img,Graphics g){
			  int imgW=img.getWidth(null);
			  int imgH=img.getHeight(null);
			  g.drawImage(img, this.x+(this.w-imgW>>1), this.y+(this.h-imgH>>1), null);
		  }
    
}
