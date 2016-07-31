package dp.els.ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img {
    private  Img(){}
    
    //�߿��زĶ�ȡ��layer��
    public static Image WINDOW =new ImageIcon("Graphics/window/windows5.jpg").getImage();
    //����ͼƬ 260 36
    public static Image NUMBER =new ImageIcon("Graphics/string/num.png").getImage();
    //����ֵ��ͼƬ
    public static  Image RECT =new ImageIcon("Graphics/window/rect.png").getImage();
    
    //���ؼ�¼���֣�(LayerDisk)
    public  static Image DISK =new ImageIcon("Graphics/string/disk.png").getImage();
    
    //����ͼƬ��LayerBackGround��
	public  static Image TEMP =new ImageIcon("Graphics/background/bg01.jpg").getImage();
	
	//��ʼ��ť
	public  static ImageIcon BTN_START =new ImageIcon("Graphics/string/strat.png");

	//���ð�ť
	public  static ImageIcon BTN_CONFIG =new ImageIcon("Graphics/string/config.png");
	
	
	
	
	//������ǩ��ͼƬ����ʱû�У���LayerAbout��
	//public static  Image About =new ImageIcon("Graphics/string/sign.png").getImage();
	
    //���ݿ�ͼƬ���֣���LayerDataBase��
	public static Image DataBase =new ImageIcon("Graphics/string/db.png").getImage();
	
	//����ͼƬ��LayerGame��
	public static Image game =new ImageIcon("Graphics/game/rect.png").getImage();
	
	 //�ȼ�ͼƬ���֣�(LayerGame)
	public static Image Level=new ImageIcon("Graphics/string/level.png").getImage();
	//��ӰͼƬ
	public static Image SHODOW=new ImageIcon("Graphics/game/shodow.png").getImage();
	
	 //����ͼƬ(����)��LayerPoint��
	public static Image POINT =new ImageIcon("Graphics/string/point.png").getImage();
	//����ͼƬ(����)
	public static Image RMLINE =new ImageIcon("Graphics/string/rmline.png").getImage();
	//��һ��ͼƬ���飨LayerNext��
	public  static Image[] NEXT_ACT;
	public static List<Image> BG_LIST;
		static {
			//��һ������ͼƬ
			NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
			for (int i = 0; i < NEXT_ACT.length; i++) {
				NEXT_ACT[i]=new ImageIcon("Graphics/game/"+ i + ".png").getImage();
			}
			
			//����ͼƬ����
			File dir =new File("Graphics/background");
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




