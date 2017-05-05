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
 * 游戏配置器,此类将数据从配置文件中导出，并提供几个类型配置实例化的对象。本类构造器私有化不提供实例化对象
 * 其实可以当作几个配置类对象的工厂类
 * @author DP
 */
public class GameConfig {
	private static FrameConfig FRAME_CONFIG=null;
	private static DataConfig DATA_CONFIG=null;
	private static SystemConfig SYSTEM_CONFIG=null;
	private static ButtonConfig BUTTON_CONFIG=null;
	//是否是开发环境
	private static boolean ISDEBUG=false;
	private static ObjectInputStream ois=null;
	private static ObjectOutputStream oos=null;
	static {
		try {
			//开发环境从配置文件中读取配置
			if(ISDEBUG){
					//创建xml读取器
			    	SAXReader  reader =new SAXReader();
			    	//读取XML文件
					Document doc;
					doc = reader.read("data/cfg.xml");
					//获得XML文件的根节点
					Element game =doc.getRootElement();
					//创建界面配置对象
					FRAME_CONFIG=new FrameConfig(game.element("frame"));
					//创建系统对象
					SYSTEM_CONFIG=new SystemConfig(game.element("system"));
					//创建数据访问对象
					DATA_CONFIG=new DataConfig(game.element("data"));
					//创建按钮数据访问对象
					BUTTON_CONFIG=new ButtonConfig(game.element("Button"));
					//重新写入新的配置
					writeObject("data/framecfg.dat",FRAME_CONFIG);
					writeObject("data/datacfg.dat",DATA_CONFIG);
					writeObject("data/systemcfg.dat",SYSTEM_CONFIG);
					writeObject("data/buttoncfg.dat",BUTTON_CONFIG);
				}else{//发布之后从.dat配置中读取，是为了不给用户改配置的功能
					FRAME_CONFIG=(FrameConfig) readObject("data/framecfg.dat");
					DATA_CONFIG=(DataConfig) readObject("data/datacfg.dat");
					SYSTEM_CONFIG=(SystemConfig) readObject("data/systemcfg.dat");
					BUTTON_CONFIG=(ButtonConfig) readObject("data/buttoncfg.dat");
				}
		
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//构造器私有化
	private GameConfig(){}
	
	/**
	 * 获得窗口配置
	 * @return
	 */
	public static FrameConfig getFrameConfig(){
		return FRAME_CONFIG;
	}

	/**
	 * 获得数据访问配置
	 * @return
	 */
	public static DataConfig getDataConfig(){
		return DATA_CONFIG;
	}
	
	/**
	 *  获得系统配置
	 * @return
	 */
	public static SystemConfig getSystemConfig(){
		return SYSTEM_CONFIG;
	}
	
	/**
	 * 获取按钮配置
	 * @return
	 */
	public static ButtonConfig getBUTTON_CONFIG() {
		return BUTTON_CONFIG;
	}

	
	/**
	 * 在生产环境中（即用户使用）读取.dat中的配置数据
	 * @param path .dat配置文件路径
	 * @return 返回object
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
	 * 每次在开发环境进行修改后需要重新写入配置
	 * @param path 配置文件路径
	 * @param object 写入整个类的数据，这个类必须实现序列化
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
