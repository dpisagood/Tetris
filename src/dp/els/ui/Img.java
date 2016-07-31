package dp.els.ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img {
    private  Img(){}
    
    //边框素材读取（layer）
    public static Image WINDOW =new ImageIcon("Graphics/window/windows5.jpg").getImage();
    //数字图片 260 36
    public static Image NUMBER =new ImageIcon("Graphics/string/num.png").getImage();
    //矩形值槽图片
    public static  Image RECT =new ImageIcon("Graphics/window/rect.png").getImage();
    
    //本地记录（字）(LayerDisk)
    public  static Image DISK =new ImageIcon("Graphics/string/disk.png").getImage();
    
    //背景图片（LayerBackGround）
	public  static Image TEMP =new ImageIcon("Graphics/background/bg01.jpg").getImage();
	
	//开始按钮
	public  static ImageIcon BTN_START =new ImageIcon("Graphics/string/strat.png");

	//设置按钮
	public  static ImageIcon BTN_CONFIG =new ImageIcon("Graphics/string/config.png");
	
	
	
	
	//制作人签名图片（暂时没有）（LayerAbout）
	//public static  Image About =new ImageIcon("Graphics/string/sign.png").getImage();
	
    //数据库图片（字）（LayerDataBase）
	public static Image DataBase =new ImageIcon("Graphics/string/db.png").getImage();
	
	//方块图片（LayerGame）
	public static Image game =new ImageIcon("Graphics/game/rect.png").getImage();
	
	 //等级图片（字）(LayerGame)
	public static Image Level=new ImageIcon("Graphics/string/level.png").getImage();
	//阴影图片
	public static Image SHODOW=new ImageIcon("Graphics/game/shodow.png").getImage();
	
	 //汉字图片(分数)（LayerPoint）
	public static Image POINT =new ImageIcon("Graphics/string/point.png").getImage();
	//汉字图片(消行)
	public static Image RMLINE =new ImageIcon("Graphics/string/rmline.png").getImage();
	//下一个图片数组（LayerNext）
	public  static Image[] NEXT_ACT;
	public static List<Image> BG_LIST;
		static {
			//下一个方块图片
			NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
			for (int i = 0; i < NEXT_ACT.length; i++) {
				NEXT_ACT[i]=new ImageIcon("Graphics/game/"+ i + ".png").getImage();
			}
			
			//背景图片数组
			File dir =new File("Graphics/background");
			File[] files =dir.listFiles();
			BG_LIST=new ArrayList<Image>();
			for(File file:files){
				if(file.isDirectory()){
					continue;//如果不是文件夹路径继续执行
				}
				//通过图片文件路径来得到Image对象
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
				//String path =file.getPath();//获得图片文件路径
			}
			
		}
		
    
}




