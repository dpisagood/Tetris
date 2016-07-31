package dp.els.main;
import dp.els.control.GameControl;
import dp.els.control.PlayerControl;
import dp.els.dto.GameDto;
import dp.els.service.GameTetris;
import dp.els.ui.JFrameGame;
import dp.els.ui.JPanelGame;
public class Main {
    public static void main(String[] args) {
    	//������Ϸ����Դ
    	GameDto dto =new GameDto();
    	//������Ϸ���
		JPanelGame panel =new JPanelGame(dto);
		//������Ϸ�߼��飨������Ϸ����Դ��
		GameTetris service =new GameTetris(dto);
		//������Ϸ��������������Ϸ������Ϸ�߼��飩
		GameControl gameControl =new GameControl(panel,service);
		//������ҿ�������������Ϸ��������
		PlayerControl control =new PlayerControl(gameControl);
		//��װ��ҿ�����
		panel.setGameControl(control);
		//������Ϸ���� ��װ��Ϸ���
	     new JFrameGame(panel);
		
		
	}
}
