package dp.els.control;


import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import config.DataInterfaceConfig;
import config.GameConfig;
import dp.els.dao.IData;
import dp.els.service.GameService;
import dp.els.service.GameTetris;
import dp.els.ui.JPanelGame;


/**
 *，把它与Panel建立联系，而他自己又受PlayerControl控制
 * 接收玩家键盘事件
 * 控制游戏画面
 * 控制游戏逻辑
 * 像一个中转站一样，所有的数据都要通过其中转
 * */
public class GameControl  {
	//游戏逻辑层（提供游戏动作逻辑服务的类，交给GameControl监听控制）
	private GameService gameService;
	//游戏界面层（与界面建立联系）
	private JPanelGame panelGame; 
	//数据访问接口A
	private IData dataA;
	//数据访问接口B
	private IData dataB;
	//游戏行为控制
	private Map<Integer,Method> actionList;
	
	
public GameControl(JPanelGame panelGame ,GameTetris gameService){
	  this.panelGame=panelGame;
	  this.gameService=gameService;
	  //获得类对象
	  this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
	  //导入数据库记录到游戏
	  this.gameService.setRecondeDataBase(dataA.getData());
	  //实例化数据接口A用于获得本地磁盘记录
	  this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
	  //导入本地记录到游戏
	  this.gameService.setRecondeDisk(dataB.getData());
	  //读取用户控制设置
	  this.setContorlConfig();
	}


	private void setContorlConfig(){
		  //创建键盘码与方法名的映射数组
		  this.actionList=new HashMap<Integer,Method>();
		  try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("data/control.dat"));
			HashMap<Integer,String> cfgSet=(HashMap<Integer,String>)ois.readObject();
			ois.close();
			Set<Entry<Integer,String>> entryset=cfgSet.entrySet();
			for (Entry<Integer, String> entry : entryset) {
				this.actionList.put(entry.getKey(), this.gameService.getClass().getMethod(entry.getValue()));
			}
		  } catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 创建数据对象
	 * @param cfg
	 * @return
	 */
	private IData createDataObject(DataInterfaceConfig cfg){
		try {
			//获得类对象
			Class<?> cls=Class.forName(cfg.getClassName());
			//获得构造器
			Constructor<?> ctr=cls.getConstructor(HashMap.class);
			//创建对象
			return (IData)ctr.newInstance(cfg.getParam());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void actionByKeyCode(int keyCode) {
		try {
			if(this.actionList.containsKey(keyCode)){
				this.actionList.get(keyCode).invoke(this.gameService);
			}
//			//获得方法名
//			String methodName=this.actionList.get(keyCode);
//			//根据方法名获得方法对象
//			Method actionMethod =this.gameService.getClass().getMethod(methodName);
//			//调用方法
//			actionMethod.invoke(this.gameService);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		this.panelGame.repaint();		
	}

	
}
