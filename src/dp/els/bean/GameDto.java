 package dp.els.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dp.els.util.ConfigConstant;
import dp.els.util.GameFunction;
//������
public class GameDto {
	 //���ݿ��¼
     private List<Player> dbRecode;
     //����Ӳ�̼�¼
     private List<Player> disRecode;
     private  Square gamesquare;//���䷽��
     private int next;//��һ������
     private int nowlevel;//�ȼ�
     private int nowPoint ;//���ڵķ���
     private int nowRemoveLine;//����
     private boolean start;//��Ϸ�Ƿ��ǿ�ʼ״̬
     private boolean showShadow;//�Ƿ���ʾ��Ӱ
     private boolean pause;//��ͣ
     private long sleepTime;//�߳�����ʱ��
     
     private static final GameDto gamedto=new GameDto();
 	 private GameDto(){
   	  dtoInit();
     }
	/**
	 * ������Ϸ�о�һ������Դ��ɵ�����
	 * @return
	 */
	public static GameDto getGameDto(){
		return gamedto;
	}
     
	/**
	 * ����֮�����¿�ʼ��ʼ����Ϸ�ĸ�������
	 */
     public  void dtoInit() {
    	ConfigConstant.init();
		this.nowlevel=1;
		this.nowPoint=0;
		this.nowRemoveLine=0;
		this.pause=false;
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowlevel);
	}
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
		this.dbRecode = this.Sort(dbRecode);
	}
	
	public List<Player> getDisRecode() {
		return disRecode;
	}
	
	public void setDisRecode(List<Player> disRecode) {
		this.disRecode = this.Sort(disRecode);
	}
	
	
	public Square getGamesquare() {
		return gamesquare;
	}

	public void setGamesquare(Square gamesquare) {
		this.gamesquare = gamesquare;
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
	  
	/**
	 * ����͵����ݿ���û���������ʱ�ṩһ��0����player
	 * @param pla  ��Ҫ����Ļ��߿����пյ��û�����
	 */
	private List<Player> Sort(List<Player> pla){
		//����������ǿգ���ô�ʹ���
		if(pla==null){
			pla=new ArrayList<Player>();
		}
		//�����¼��С��5����ô�ͼӵ�5��Ϊֹ 
		while(pla.size()<5){
			pla.add(new Player("No Data",0));
		}
		Collections.sort(pla);
		return pla;
	}
}
