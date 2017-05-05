package dp.els.gameservice;



public interface GameService {
	//方向键上
	public boolean keyUp();
	//方向键下
	public boolean keyDown();
	//方向键左
	public boolean keyLeft();
	//方向键右
	public boolean keyRight();
	
	public boolean keyFunUp();
	public boolean keyFunDown();
	public boolean keyFunLeft();
	public boolean keyFunRight();
	public void startGame();
	
}
