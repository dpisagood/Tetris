package dp.els.ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import dp.els.config.GameConfig;

public class Img {
    private  Img(){}
    //图片路径
    public static final String GRAPHICS_PATH="Graphics";
    private static String Skin_Path="默认";
    //边框素材读取（layer）
    public static Image WINDOW =null;
    //数字图片 260 36
    public static Image NUMBER =null;
    //矩形值槽图片
    public static  Image RECT =null;
    //本地记录（字）(LayerDisk)
    public  static Image DISK =null;
    //背景图片（LayerBackGround）
	public  static Image TEMP =null;
	//开始按钮(button中有个传入ImageIcon的构造方法)
	public  static ImageIcon BTN_START =null;
	//设置按钮(button中有个传入ImageIcon的构造方法)
	public  static ImageIcon BTN_CONFIG =null;
	//暂停
	public  static Image PAUSE =null;
	//制作人签名图片（暂时没有）（LayerAbout）
	//public static  Image About =new ImageIcon(GRAPHICS_PATH+"/string/sign.png").getImage();
    //数据库图片（字）（LayerDataBase）
	public static Image DataBase =null;
	//方块图片（LayerGame）
	public static Image game =null;
	 //等级图片（字）(LayerGame)
	public static Image Level=null;
	//阴影图片
	public static Image SHODOW=null;
	 //汉字图片(分数)（LayerPoint）
	public static Image POINT =null;
	//汉字图片(消行)
	public static Image RMLINE =null;
	//下一个图片数组（LayerNext）
	public  static Image[] NEXT_ACT=null;
	public static List<Image> BG_LIST=null;
	
    static{
    	setSkin(Skin_Path);
    }
	
    /**
     * 设置皮肤
     * @param path 皮肤素材路径
     */
    public static void setSkin(String path){
    	String skinPath=GRAPHICS_PATH+"/"+path;
        //边框素材读取（layer）
         WINDOW =new ImageIcon(skinPath+"/window/windows5.jpg").getImage();
        //数字图片 260 36
         NUMBER =new ImageIcon(skinPath+"/string/num.png").getImage();
        //矩形值槽图片
         RECT =new ImageIcon(skinPath+"/window/rect.png").getImage();
        //本地记录（字）(LayerDisk)
         @SuppressWarnings("unused")
		Image DISK =new ImageIcon(skinPath+"/string/disk.png").getImage();
        //背景图片（LayerBackGround）
         @SuppressWarnings("unused")
		Image TEMP =new ImageIcon(skinPath+"/background/bg01.jpg").getImage();
    	//开始按钮(button中有个传入ImageIcon的构造方法)
         BTN_START =new ImageIcon(skinPath+"/string/strat.png");
    	//设置按钮(button中有个传入ImageIcon的构造方法)
         BTN_CONFIG =new ImageIcon(skinPath+"/string/config.png");
    	//暂停
         PAUSE =new ImageIcon(skinPath+"/string/pause.png").getImage();
    	//制作人签名图片（暂时没有）（LayerAbout）
    	//public static  Image About =new ImageIcon(skinPath+"/string/sign.png").getImage();
        //数据库图片（字）（LayerDataBase）
    	 DataBase =new ImageIcon(skinPath+"/string/db.png").getImage();
    	//方块图片（LayerGame）
    	 game =new ImageIcon(skinPath+"/game/rect.png").getImage();
    	 //等级图片（字）(LayerGame)
    	 Level=new ImageIcon(skinPath+"/string/level.png").getImage();
    	//阴影图片
    	 SHODOW=new ImageIcon(skinPath+"/game/shodow.png").getImage();
    	 //汉字图片(分数)（LayerPoint）
    	 POINT =new ImageIcon(skinPath+"/string/point.png").getImage();
    	//汉字图片(消行)
    	 RMLINE =new ImageIcon(skinPath+"/string/rmline.png").getImage();
    			//下一个方块图片
    			NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
    			for (int i = 0; i < NEXT_ACT.length; i++) {
    				NEXT_ACT[i]=new ImageIcon(skinPath+"/game/"+ i + ".png").getImage();
    			}
    			
    			//背景图片数组
    			File dir =new File(skinPath+"/background");
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




