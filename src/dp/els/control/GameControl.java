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
import dp.els.dto.GameDto;
import dp.els.dto.Player;
import dp.els.service.GameService;
import dp.els.service.GameTetris;
import dp.els.ui.window.JFrameConfig;
import dp.els.ui.window.JFrameGame;
import dp.els.ui.window.JFrameSavePoint;
import dp.els.ui.window.JPanelGame;


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
	//游戏控制窗口
	private JFrameConfig frameConfig;
	//游戏界面层（与界面建立联系）
	private JPanelGame panelGame; 
	//数据访问接口A
	private IData dataA;
	//数据访问接口B
	private IData dataB;
	//游戏行为控制
	private Map<Integer,Method> actionList;
	//游戏线程
	private Thread gameThread=null;
	//创建游戏数据源
	private GameDto dto=null;
	//记录分数窗口
	private JFrameSavePoint frameSavePoint;
	
public GameControl(){
	
		//创建游戏数据源
		this.dto=new GameDto();
		//创建游戏逻辑块（连接游戏数据源）
		this.gameService=new GameTetris(dto);
		//创建数据接口A对象
		this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
		//导入数据库记录到游戏
		this.dto.setDbRecode(dataA.getData());
		//实例化数据接口A用于获得本地磁盘记录
		this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
		//导入本地记录到游戏
		this.dto.setDisRecode(dataB.getData());
		//创建游戏面板
		this.panelGame=new JPanelGame(this,dto);
		//读取用户控制设置
		this.setContorlConfig();
		//初始化用户配置窗口
		this.frameConfig=new JFrameConfig(this);
		//创建游戏主窗口，安装游戏面板
		new JFrameGame(this.panelGame);
		//初始化得分保存分数窗口
		this.frameSavePoint=new JFrameSavePoint(this);
		
	}


	private void setContorlConfig(){
		  //创建键盘码与方法名的映射数组
		  this.actionList=new HashMap<Integer,Method>();
		  try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("data/control.dat"));
			@SuppressWarnings("unchecked")
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
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		this.panelGame.repaint();		
	}

	//显示玩家控制窗口
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}

	//子窗口关闭事件
	public void setOver() {
		this.panelGame.repaint();
		this.setContorlConfig();
	}

	//开始按钮事件
	public void start() {
		//面板按钮设置为不可击
		this.panelGame.buttonSwitch(false);
		//开始前关闭所有窗口
		this.frameConfig.setVisible(false);
		this.frameSavePoint.setVisible(false);
		//游戏数据初始化
		this.gameService.startGame();
		//创建线程对象
		this.gameThread=new MainThread();
		//启动线程
		this.gameThread.start();
		//刷新画面
		this.panelGame.repaint();
	}
	
	
	//保存分数到数据库
	public void savePoint(String name) {
		Player pla=new Player(name,this.dto.getNowPoint());
		//保存记录到数据库
		this.dataA.saveData(pla);
		//保存记录到本地磁盘
		this.dataB.saveData(pla);
		//设置数据库记录到游戏
		this.dto.setDbRecode(dataA.getData());
		//设置磁盘记录到游戏
		this.dto.setDisRecode(dataB.getData());
		//刷新画面
		this.panelGame.repaint();
	}
	
	//游戏失败之后
	public void afterLose(){
		//显示保存得分窗口
		this.frameSavePoint.show(this.dto.getNowPoint());
		this.frameSavePoint.setVisible(true);
		//使按钮可以点击
		this.panelGame.buttonSwitch(true);
		
	}
	
	//内部类。
	private class MainThread extends Thread{
		@Override
		public void run() {
			//画面刷新
			panelGame.repaint();
			//主循环
			while(dto.isStart()){
				try {
					//线程睡眠
					Thread.sleep(dto.getSleepTime());
					System.out.println(dto.getSleepTime());
					if(dto.isPause()){
						continue;
					}
					//方块下落
					gameService.keyDown();
					//画面刷新
					panelGame.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
					//游戏结束
					afterLose();
		}
	}
	
	
}

	
