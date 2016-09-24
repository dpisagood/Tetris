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
 *��������Panel������ϵ�������Լ�����PlayerControl����
 * ������Ҽ����¼�
 * ������Ϸ����
 * ������Ϸ�߼�
 * ��һ����תվһ�������е����ݶ�Ҫͨ������ת
 * */
public class GameControl  {
	//��Ϸ�߼��㣨�ṩ��Ϸ�����߼�������࣬����GameControl�������ƣ�
	private GameService gameService;
	//��Ϸ���ƴ���
	private JFrameConfig frameConfig;
	//��Ϸ����㣨����潨����ϵ��
	private JPanelGame panelGame; 
	//���ݷ��ʽӿ�A
	private IData dataA;
	//���ݷ��ʽӿ�B
	private IData dataB;
	//��Ϸ��Ϊ����
	private Map<Integer,Method> actionList;
	//��Ϸ�߳�
	private Thread gameThread=null;
	//������Ϸ����Դ
	private GameDto dto=null;
	//��¼��������
	private JFrameSavePoint frameSavePoint;
	
public GameControl(){
	
		//������Ϸ����Դ
		this.dto=new GameDto();
		//������Ϸ�߼��飨������Ϸ����Դ��
		this.gameService=new GameTetris(dto);
		//�������ݽӿ�A����
		this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
		//�������ݿ��¼����Ϸ
		this.dto.setDbRecode(dataA.getData());
		//ʵ�������ݽӿ�A���ڻ�ñ��ش��̼�¼
		this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
		//���뱾�ؼ�¼����Ϸ
		this.dto.setDisRecode(dataB.getData());
		//������Ϸ���
		this.panelGame=new JPanelGame(this,dto);
		//��ȡ�û���������
		this.setContorlConfig();
		//��ʼ���û����ô���
		this.frameConfig=new JFrameConfig(this);
		//������Ϸ�����ڣ���װ��Ϸ���
		new JFrameGame(this.panelGame);
		//��ʼ���÷ֱ����������
		this.frameSavePoint=new JFrameSavePoint(this);
		
	}


	private void setContorlConfig(){
		  //�����������뷽������ӳ������
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
	 * �������ݶ���
	 * @param cfg
	 * @return
	 */
	private IData createDataObject(DataInterfaceConfig cfg){
		try {
			//��������
			Class<?> cls=Class.forName(cfg.getClassName());
			//��ù�����
			Constructor<?> ctr=cls.getConstructor(HashMap.class);
			//��������
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

	//��ʾ��ҿ��ƴ���
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}

	//�Ӵ��ڹر��¼�
	public void setOver() {
		this.panelGame.repaint();
		this.setContorlConfig();
	}

	//��ʼ��ť�¼�
	public void start() {
		//��尴ť����Ϊ���ɻ�
		this.panelGame.buttonSwitch(false);
		//��ʼǰ�ر����д���
		this.frameConfig.setVisible(false);
		this.frameSavePoint.setVisible(false);
		//��Ϸ���ݳ�ʼ��
		this.gameService.startGame();
		//�����̶߳���
		this.gameThread=new MainThread();
		//�����߳�
		this.gameThread.start();
		//ˢ�»���
		this.panelGame.repaint();
	}
	
	
	//������������ݿ�
	public void savePoint(String name) {
		Player pla=new Player(name,this.dto.getNowPoint());
		//�����¼�����ݿ�
		this.dataA.saveData(pla);
		//�����¼�����ش���
		this.dataB.saveData(pla);
		//�������ݿ��¼����Ϸ
		this.dto.setDbRecode(dataA.getData());
		//���ô��̼�¼����Ϸ
		this.dto.setDisRecode(dataB.getData());
		//ˢ�»���
		this.panelGame.repaint();
	}
	
	//��Ϸʧ��֮��
	public void afterLose(){
		//��ʾ����÷ִ���
		this.frameSavePoint.show(this.dto.getNowPoint());
		this.frameSavePoint.setVisible(true);
		//ʹ��ť���Ե��
		this.panelGame.buttonSwitch(true);
		
	}
	
	//�ڲ��ࡣ
	private class MainThread extends Thread{
		@Override
		public void run() {
			//����ˢ��
			panelGame.repaint();
			//��ѭ��
			while(dto.isStart()){
				try {
					//�߳�˯��
					Thread.sleep(dto.getSleepTime());
					System.out.println(dto.getSleepTime());
					if(dto.isPause()){
						continue;
					}
					//��������
					gameService.keyDown();
					//����ˢ��
					panelGame.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
					//��Ϸ����
					afterLose();
		}
	}
	
	
}

	
