 package dp.els.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dp.els.config.GameConfig;
import dp.els.entity.GameAct;
import dp.els.util.GameFunction;
//������
public class GameDto {
	//��Ϸ���
	public static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	//��Ϸ�߶�
	public static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
	//���ݿ��¼
    private List<Player> dbRecode;
    //����Ӳ�̼�¼
    private List<Player> disRecode;
     private boolean[][] gameMap;
     private  GameAct gameAct;//���䷽��
     private int next;//��һ������
     private int nowlevel;//�ȼ�
     private int nowPoint ;//���ڵķ���
     private int nowRemoveLine;//����
     private boolean start;//��Ϸ�Ƿ��ǿ�ʼ״̬
     private boolean showShadow;//�Ƿ���ʾ��Ӱ
     private boolean pause;//��ͣ
     private long sleepTime;//�߳�����ʱ��
     
     public long getSleepTime() {
		return sleepTime;
	}

	public boolean isPause() {
		return pause;
	}

	public void changePause() {
			this.pause = !this.pause;
	}

	public boolean isShowShadow() {
		return showShadow;
	}

	public void changeShowShadow() {
		this.showShadow=!this.showShadow;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public GameDto(){
    	 dtoInit();
     }
     
	/**
	 * ����֮�����¿�ʼ��ʼ����Ϸ�ĸ�������
	 */
     public  void dtoInit() {
		this.gameMap=new boolean[GAMEZONE_W][GAMEZONE_H];
		this.nowlevel=1;
		this.nowPoint=0;
		this.nowRemoveLine=0;
		this.pause=false;
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowlevel);
	}

	public int getNowlevel() {
		return nowlevel;
	}

	public void setNowlevel(int nowlevel) {
		this.nowlevel = nowlevel<16?nowlevel:16;
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowlevel);
	}

	
	public List<Player> getDbRecode() {
		return dbRecode;
	}
	
	public void setDbRecode(List<Player> dbRecode) {
		this.Sort(dbRecode);
		this.dbRecode = dbRecode;
	}
	
	public List<Player> getDisRecode() {
		return disRecode;
	}
	
	public void setDisRecode(List<Player> disRecode) {
		this.Sort(disRecode);
		this.disRecode = disRecode;
	}
	
	/**
	 * ����͵����ݿ���û���������ʱ�ṩһ��0����player
	 * @param pla  ��Ҫ����Ļ��߿����пյ��û�����
	 */
	private void Sort(List<Player> pla){
		//����������ǿգ���ô�ʹ���
		if(pla==null){
			pla=new ArrayList<Player>();
		}
		//�����¼��С��5����ô�ͼӵ�5��Ϊֹ 
		while(pla.size()<5){
			pla.add(new Player("No Data",0));
		}
		Collections.sort(pla);
	}
	
	public boolean[][] getGameMap() {
		return gameMap;
	}
	
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	
	public GameAct getGameAct() {
		return gameAct;
	}
	
	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}
	
	public int getNext() {
		return next;
	}
	
	public void setNext(int next) {
		this.next = next;
	}
	
	
	public int getNowPoint() {
		return nowPoint;
	}
	
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	
	public int getNowRemoveLine() {
		return nowRemoveLine;
	}
	
	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}
	  
}
