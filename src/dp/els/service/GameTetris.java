package dp.els.service;

import java.awt.Point;
import java.util.Random;

import dp.els.config.GameConfig;
import dp.els.dto.GameDto;
import dp.els.entity.GameAct;

public class GameTetris implements GameService{
	//��Ϸ���ݶ���
	private GameDto dto;
	//�����������
    private Random random =new Random();
    //�����������
    private static final int   MAX_TYPE=GameConfig.getSystemConfig().getTypeConfig().size()-1;

    public GameTetris(GameDto dto) {
		this.dto = dto;
	}

	
	//����������ϣ���ת��
	public boolean keyUp() {
	//���������ͣ��������Ͳ����ٰ�
	if(this.dto.isPause()){
			return true;
	}
	//�߳���
	  synchronized (this.dto) {
		  this.dto.getGameAct().round(this.dto.getGameMap());
	}
      return true;
	}
	
	
	//����������£�
	public boolean keyDown() {
		//���������ͣ��������Ͳ����ٰ�
		if(this.dto.isPause()){
				return true;
		}
		  synchronized (this.dto) {//ͬ�����ƿ飬����dto����ͬʱ������̷߳���
		//�ж��Ƿ����������ƶ�������ܷ��ز������粻�ܼ����������ѻ���
		if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())){
	        return false;
		}
		//�����Ϸ��ͼ����
		//���ô��ݣ�����ڴ�֮ǰ�����ݱ��ı䣬���൱������ָ������ݱ��ı� 
		boolean[][] map=this.dto.getGameMap();
		//��÷������
		Point[] act =this.dto.getGameAct().getActPoint();
		//������ѻ�����ͼ����
		for (int i = 0; i < act.length; i++) {
			map[act[i].x][act[i].y]=true;
		}
		int  plusExp=this.plusExp();
		if(plusExp>0){
			this.plusPoint(plusExp);
		}
		//������һ������
		this.dto.getGameAct().init(this.dto.getNext());
		//�����������һ������
		this.dto.setNext(random.nextInt( MAX_TYPE));
		//�����Ϸ�Ƿ�ʧ��
		if(this.isLose()){
			//��Ϸ���֮�������Ϸ
			this.dto.setStart(false);
		}
	}
		return true;
	}
	
	//�����Ϸ�Ƿ�ʧ��
	private boolean isLose(){
		//������ڵĻ����
		Point[] actPoints=this.dto.getGameAct().getActPoint();
		boolean[][] map=this.dto.getGameMap();
		for (int i = 0; i < actPoints.length; i++) {
			if(map[actPoints[i].x][actPoints[i].y]){
				return true;
			}
		}
		return false;
	}

	//���к��������
	//�ӷַ���
	private void plusPoint(int plusExp) {
		int point =this.dto.getNowPoint();
		int rmline=this.dto.getNowRemoveLine();
		int lv =this.dto.getNowlevel();
		point+=10;
		rmline+=1;
		if(rmline%20==0){
			lv+=1;
		}
		this.dto.setNowPoint(point);
		this.dto.setNowlevel(lv);
		this.dto.setNowRemoveLine(rmline); 
	}

	//��������
	//���в���
	private int plusExp() {
		int i=0;
		//��õ�ͼ
		boolean [][] map=this.dto.getGameMap();
		//ɨ���ͼ���鿴�Ƿ��п�����
		for (int y = 0; y <GameDto.GAMEZONE_H; y++) {
			//�ж��Ƿ������
			if( isCanRemoveLine( y,map)){
				//����
				this.removeLine( y,map);
				i++;
			}
		}
		return i;
	}
	
	//�ж��Ƿ��������
	//�ж�һ���Ƿ������
	private boolean isCanRemoveLine(int y,boolean[][] map){
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			if(!map[x][y]){
				//�����һ������Ϊfalse��ֱ��������һ��
				return false;
			}
		}
		return true;
	}

	//���в���
	private void removeLine(int rowNumber,boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			for (int y = rowNumber; y >0; y--) {
				map[x][y]=map[x][y-1];	
			}
			map[x][0]=false;
		}
	}

	
	//�����������
	public boolean keyLeft() {
		//���������ͣ��������Ͳ����ٰ�
		if(this.dto.isPause()){
				return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());
		}
			return true;
	}
	//����������ң�
	public boolean keyRight() {
		//���������ͣ��������Ͳ����ٰ�
		if(this.dto.isPause()){
				return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(1, 0,this.dto.getGameMap());
		}
			return true;
	}
	

	/**
	 * ���׼�
	 */
	@Override
	public boolean keyFunUp() {
		this.plusPoint(4);
		return true;
	}

	@Override//�����½�
	public boolean keyFunDown() {
		//���������ͣ��������Ͳ����ٰ�
		if(this.dto.isPause()){
				return true;
		}
		if(this.dto.isStart()) {
			while(!this.keyDown());
   		}
		return true;
	}

	@Override//��Ӱ����
	public boolean keyFunLeft() {
		this.dto.changeShowShadow();
		return true;
	}

	//��ͣ
	@Override
	public boolean keyFunRight() {
		//��ʼ֮�������ͣ
		if(this.dto.isStart()){
			this.dto.changePause();
		}
		return true;
	}
	
	
	//�������߳̿�ʼ��Ϸ
	@Override
	public void startGame() {
		//���������һ������
		this.dto.setNext(random.nextInt( MAX_TYPE));
		//����������ڷ���
		dto.setGameAct(new GameAct(random.nextInt( MAX_TYPE)));
		//����Ϸ״̬��Ϊ��ʼ
		this.dto.setStart(true);
		//Ϊ���¿�ʼ��ʼ������ 
		this.dto.dtoInit();
	}
}

