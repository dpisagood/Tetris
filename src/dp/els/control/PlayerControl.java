package dp.els.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * ���������������������Ҽ��̵Ĳ����ģ�Ψһ����Ϸ�����еĽ���
 * @param gameControl ������ͨ��������Ϸ���������󣬵�����в���ʱ��������ʱ�������������ô�����
 * @author DP
 *
 */
public class PlayerControl extends KeyAdapter {//ʵ�ּ������ӿڲ���д�䷽�����м�������
             
	private GameControl gameControl;
	public PlayerControl(GameControl gameControl){
		this.gameControl=gameControl;
	}
	
	//KeyAdapter�̳���KeyListener�ӿڣ��̳������֮�����ǾͲ����ٸ�д//��������//�����ſ�������������
	/**
	 * ���̰��µ���gameControl��actionByKeyCode()��������������̱�ţ�����������¼�
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		this.gameControl.actionByKeyCode(e.getKeyCode());
	}
	

}
