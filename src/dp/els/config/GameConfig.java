package dp.els.config;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * ��Ϸ������,���ཫ���ݴ������ļ��е��������ṩ������������ʵ�����Ķ��󡣱��๹����˽�л����ṩʵ��������
 * ��ʵ���Ե����������������Ĺ�����
 * @author DP
 */
public class GameConfig {
	private static FrameConfig FRAME_CONFIG=null;
	private static DataConfig DATA_CONFIG=null;
	private static SystemConfig SYSTEM_CONFIG=null;
	private static ButtonConfig BUTTON_CONFIG=null;
	//�Ƿ��ǿ�������
	private static boolean ISDEBUG=false;
	private static ObjectInputStream ois=null;
	private static ObjectOutputStream oos=null;
	static {
		try {
			//���������������ļ��ж�ȡ����
			if(ISDEBUG){
					//����xml��ȡ��
			    	SAXReader  reader =new SAXReader();
			    	//��ȡXML�ļ�
					Document doc;
					doc = reader.read("data/cfg.xml");
					//���XML�ļ��ĸ��ڵ�
					Element game =doc.getRootElement();
					//�����������ö���
					FRAME_CONFIG=new FrameConfig(game.element("frame"));
					//����ϵͳ����
					SYSTEM_CONFIG=new SystemConfig(game.element("system"));
					//�������ݷ��ʶ���
					DATA_CONFIG=new DataConfig(game.element("data"));
					//������ť���ݷ��ʶ���
					BUTTON_CONFIG=new ButtonConfig(game.element("Button"));
					//����д���µ�����
					writeObject("data/framecfg.dat",FRAME_CONFIG);
					writeObject("data/datacfg.dat",DATA_CONFIG);
					writeObject("data/systemcfg.dat",SYSTEM_CONFIG);
					writeObject("data/buttoncfg.dat",BUTTON_CONFIG);
				}else{//����֮���.dat�����ж�ȡ����Ϊ�˲����û������õĹ���
					FRAME_CONFIG=(FrameConfig) readObject("data/framecfg.dat");
					DATA_CONFIG=(DataConfig) readObject("data/datacfg.dat");
					SYSTEM_CONFIG=(SystemConfig) readObject("data/systemcfg.dat");
					BUTTON_CONFIG=(ButtonConfig) readObject("data/buttoncfg.dat");
				}
		
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//������˽�л�
	private GameConfig(){}
	
	/**
	 * ��ô�������
	 * @return
	 */
	public static FrameConfig getFrameConfig(){
		return FRAME_CONFIG;
	}

	/**
	 * ������ݷ�������
	 * @return
	 */
	public static DataConfig getDataConfig(){
		return DATA_CONFIG;
	}
	
	/**
	 *  ���ϵͳ����
	 * @return
	 */
	public static SystemConfig getSystemConfig(){
		return SYSTEM_CONFIG;
	}
	
	/**
	 * ��ȡ��ť����
	 * @return
	 */
	public static ButtonConfig getBUTTON_CONFIG() {
		return BUTTON_CONFIG;
	}

	
	/**
	 * �����������У����û�ʹ�ã���ȡ.dat�е���������
	 * @param path .dat�����ļ�·��
	 * @return ����object
	 */
	private static Object readObject(String path){
			try {
				ois = new ObjectInputStream(new FileInputStream(path));
				return ois.readObject();
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
	}
	
	
	/**
	 * ÿ���ڿ������������޸ĺ���Ҫ����д������
	 * @param path �����ļ�·��
	 * @param object д������������ݣ���������ʵ�����л�
	 * @throws Exception
	 */
	private static void writeObject(String path ,Object object){
			try {
				oos = new ObjectOutputStream(new FileOutputStream(path));
				oos.writeObject(object);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
