package dp.els.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//������(�൱����ҿ���),���ڿ���GameControl
public class PlayerControl extends KeyAdapter {//ʵ�ּ������ӿڲ���д�䷽�����м�������
             
	private GameControl gameControl;
	public PlayerControl(GameControl gameControl){
		this.gameControl=gameControl;
	}
	
	//KeyAdapter�̳���KeyListener�ӿڣ��̳������֮�����ǾͲ����ٸ�д//��������//�����ſ�������������
	/**
	 *���̰����¼� 
	 */
	public void keyPressed(KeyEvent e) {
	this.gameControl.actionByKeyCode(e.getKeyCode());
  }
	

}
