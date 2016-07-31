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
 *��������Panel������ϵ�������Լ�����PlayerControl����
 * ������Ҽ����¼�
 * ������Ϸ����
 * ������Ϸ�߼�
 * ��һ����תվһ�������е����ݶ�Ҫͨ������ת
 * */
public class GameControl  {
	//��Ϸ�߼��㣨�ṩ��Ϸ�����߼�������࣬����GameControl�������ƣ�
	private GameService gameService;
	//��Ϸ����㣨����潨����ϵ��
	private JPanelGame panelGame; 
	//���ݷ��ʽӿ�A
	private IData dataA;
	//���ݷ��ʽӿ�B
	private IData dataB;
	//��Ϸ��Ϊ����
	private Map<Integer,Method> actionList;
	
	
public GameControl(JPanelGame panelGame ,GameTetris gameService){
	  this.panelGame=panelGame;
	  this.gameService=gameService;
	  //��������
	  this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
	  //�������ݿ��¼����Ϸ
	  this.gameService.setRecondeDataBase(dataA.getData());
	  //ʵ�������ݽӿ�A���ڻ�ñ��ش��̼�¼
	  this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
	  //���뱾�ؼ�¼����Ϸ
	  this.gameService.setRecondeDisk(dataB.getData());
	  //��ȡ�û���������
	  this.setContorlConfig();
	}


	private void setContorlConfig(){
		  //�����������뷽������ӳ������
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
//			//��÷�����
//			String methodName=this.actionList.get(keyCode);
//			//���ݷ�������÷�������
//			Method actionMethod =this.gameService.getClass().getMethod(methodName);
//			//���÷���
//			actionMethod.invoke(this.gameService);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		this.panelGame.repaint();		
	}

	
}
