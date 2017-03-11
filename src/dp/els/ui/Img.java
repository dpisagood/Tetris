package dp.els.ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import dp.els.config.GameConfig;

public class Img {
    private  Img(){}
    //ͼƬ·��
    public static final String GRAPHICS_PATH="Graphics";
    private static String Skin_Path="Ĭ��";
    //�߿��زĶ�ȡ��layer��
    public static Image WINDOW =null;
    //����ͼƬ 260 36
    public static Image NUMBER =null;
    //����ֵ��ͼƬ
    public static  Image RECT =null;
    //���ؼ�¼���֣�(LayerDisk)
    public  static Image DISK =null;
    //����ͼƬ��LayerBackGround��
	public  static Image TEMP =null;
	//��ʼ��ť(button���и�����ImageIcon�Ĺ��췽��)
	public  static ImageIcon BTN_START =null;
	//���ð�ť(button���и�����ImageIcon�Ĺ��췽��)
	public  static ImageIcon BTN_CONFIG =null;
	//��ͣ
	public  static Image PAUSE =null;
	//������ǩ��ͼƬ����ʱû�У���LayerAbout��
	//public static  Image About =new ImageIcon(GRAPHICS_PATH+"/string/sign.png").getImage();
    //���ݿ�ͼƬ���֣���LayerDataBase��
	public static Image DataBase =null;
	//����ͼƬ��LayerGame��
	public static Image game =null;
	 //�ȼ�ͼƬ���֣�(LayerGame)
	public static Image Level=null;
	//��ӰͼƬ
	public static Image SHODOW=null;
	 //����ͼƬ(����)��LayerPoint��
	public static Image POINT =null;
	//����ͼƬ(����)
	public static Image RMLINE =null;
	//��һ��ͼƬ���飨LayerNext��
	public  static Image[] NEXT_ACT=null;
	public static List<Image> BG_LIST=null;
	
    static{
    	setSkin(Skin_Path);
    }
	
    /**
     * ����Ƥ��
     * @param path Ƥ���ز�·��
     */
    public static void setSkin(String path){
    	String skinPath=GRAPHICS_PATH+"/"+path;
        //�߿��زĶ�ȡ��layer��
         WINDOW =new ImageIcon(skinPath+"/window/windows5.jpg").getImage();
        //����ͼƬ 260 36
         NUMBER =new ImageIcon(skinPath+"/string/num.png").getImage();
        //����ֵ��ͼƬ
         RECT =new ImageIcon(skinPath+"/window/rect.png").getImage();
        //���ؼ�¼���֣�(LayerDisk)
         @SuppressWarnings("unused")
		Image DISK =new ImageIcon(skinPath+"/string/disk.png").getImage();
        //����ͼƬ��LayerBackGround��
         @SuppressWarnings("unused")
		Image TEMP =new ImageIcon(skinPath+"/background/bg01.jpg").getImage();
    	//��ʼ��ť(button���и�����ImageIcon�Ĺ��췽��)
         BTN_START =new ImageIcon(skinPath+"/string/strat.png");
    	//���ð�ť(button���и�����ImageIcon�Ĺ��췽��)
         BTN_CONFIG =new ImageIcon(skinPath+"/string/config.png");
    	//��ͣ
         PAUSE =new ImageIcon(skinPath+"/string/pause.png").getImage();
    	//������ǩ��ͼƬ����ʱû�У���LayerAbout��
    	//public static  Image About =new ImageIcon(skinPath+"/string/sign.png").getImage();
        //���ݿ�ͼƬ���֣���LayerDataBase��
    	 DataBase =new ImageIcon(skinPath+"/string/db.png").getImage();
    	//����ͼƬ��LayerGame��
    	 game =new ImageIcon(skinPath+"/game/rect.png").getImage();
    	 //�ȼ�ͼƬ���֣�(LayerGame)
    	 Level=new ImageIcon(skinPath+"/string/level.png").getImage();
    	//��ӰͼƬ
    	 SHODOW=new ImageIcon(skinPath+"/game/shodow.png").getImage();
    	 //����ͼƬ(����)��LayerPoint��
    	 POINT =new ImageIcon(skinPath+"/string/point.png").getImage();
    	//����ͼƬ(����)
    	 RMLINE =new ImageIcon(skinPath+"/string/rmline.png").getImage();
    			//��һ������ͼƬ
    			NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
    			for (int i = 0; i < NEXT_ACT.length; i++) {
    				NEXT_ACT[i]=new ImageIcon(skinPath+"/game/"+ i + ".png").getImage();
    			}
    			
    			//����ͼƬ����
    			File dir =new File(skinPath+"/background");
    			File[] files =dir.listFiles();
    			BG_LIST=new ArrayList<Image>();
    			for(File file:files){
    				if(file.isDirectory()){
    					continue;//��������ļ���·������ִ��
    				}
    				//ͨ��ͼƬ�ļ�·�����õ�Image����
    				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
    				//String path =file.getPath();//���ͼƬ�ļ�·��
    			}
    			
    }
}




