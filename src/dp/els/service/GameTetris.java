package dp.els.service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import dp.els.dto.GameDto;
import dp.els.dto.Player;
import dp.els.entity.GameAct;

public class GameTetris implements GameService{
	//��Ϸ���ݶ���
	private GameDto dto;
	//�����������
    private Random random =new Random();
    //�����������
    private static final int   MAX_TYPE=6;
	public GameTetris(GameDto dto) {
		this.dto = dto;
		GameAct gameAct=new GameAct(random.nextInt( MAX_TYPE));
		dto.setGameAct(gameAct);
	}

	//����������ϣ���ת��
	public void keyUp() {
      this.dto.getGameAct().round(this.dto.getGameMap());
	}
	//����������£�
	public void keyDown() {
		//�ж��Ƿ����������ƶ�������ܷ��ز������粻�ܼ����������ѻ���
		if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())){
	        return;
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
		//TODO �ж��Ƿ��������
		//TODO ���в���
		//TODO ��ֲ���
		//TODO �ж��Ƿ�����
		//TODO ����
		//������һ������
		this.dto.getGameAct().init(this.dto.getNext());
		//�����������һ������
		this.dto.setNext(random.nextInt( MAX_TYPE));
	}
	//�����������
	public void keyLeft() {
			this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());
	}
	//����������ң�
	public void keyRight() {
			this.dto.getGameAct().move(1, 0,this.dto.getGameMap());
	}
	

	@Override
	public void ketDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyFunUp() {
		System.out.println("�����Ǳ�����");
	}

	@Override
	public void keyFunDown() {//���װ�ť
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

	@Override
	public void keyFunLeft() {
		System.out.println("���鱻����");
	}

	@Override
	public void keyFunRight() {
		System.out.println("ԲȦ������");
	}
	
	
	
	//�����ݿ����ݴ���dto
	public void setRecondeDataBase(List<Player> databaseplayers){
		this.dto.setDbRecode(databaseplayers);
	}
	//�����ش������ݴ���dto
	public void setRecondeDisk(List<Player> diskplayers){
		this.dto.setDisRecode(diskplayers);
	}
}



//+++++++++++++++++++++++++++++++���Դ���

//public void testlevelUp() {
//	int point =this.dto.getNowPoint();
//	int rmline=this.dto.getNowRemoveLine();
//	int lv =this.dto.getNowlevel();
//	point+=10;
//	rmline+=1;
//	if(rmline%20==0){
//		lv+=1;
//	}
//	this.dto.setNowPoint(point);
//	this.dto.setNowlevel(lv);
//	this.dto.setNowRemoveLine(rmline); 
//}