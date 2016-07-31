 package dp.els.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.GameConfig;
import dp.els.entity.GameAct;
//������
public class GameDto {
	private static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	private static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
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
     
     
     public GameDto(){
    	 dtoInit();
     }
     
     public  void dtoInit() {
		this.gameMap=new boolean[GAMEZONE_W][GAMEZONE_H];
	}

	public int getNowlevel() {
		return nowlevel;
	}

	public void setNowlevel(int nowlevel) {
		this.nowlevel = nowlevel;
	}

	
	public List<Player> getDbRecode() {
		return dbRecode;
	}
	
	public void setDbRecode(List<Player> dbRecode) {
//		if(dbRecode==null){
//			dbRecode=new ArrayList<Player>();
//		}
//		while(dbRecode.size()<5){
//			dbRecode.add(new Player("No Data",0));
//		}
//		Collections.sort(dbRecode);
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
