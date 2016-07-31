package dp.els.main;
import dp.els.control.GameControl;
import dp.els.control.PlayerControl;
import dp.els.dto.GameDto;
import dp.els.service.GameTetris;
import dp.els.ui.JFrameGame;
import dp.els.ui.JPanelGame;
public class Main {
    public static void main(String[] args) {
    	//创建游戏数据源
    	GameDto dto =new GameDto();
    	//创建游戏面板
		JPanelGame panel =new JPanelGame(dto);
		//创建游戏逻辑块（连接游戏数据源）
		GameTetris service =new GameTetris(dto);
		//创建游戏控制器（连接游戏面板和游戏逻辑块）
		GameControl gameControl =new GameControl(panel,service);
		//创建玩家控制器（连接游戏控制器）
		PlayerControl control =new PlayerControl(gameControl);
		//安装玩家控制器
		panel.setGameControl(control);
		//创建游戏窗口 安装游戏面板
	     new JFrameGame(panel);
		
		
	}
}
