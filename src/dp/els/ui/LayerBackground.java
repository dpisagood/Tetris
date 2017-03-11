package dp.els.ui;

import java.awt.Graphics;

public class LayerBackground extends Layer {
	public LayerBackground(int x ,int y, int w, int h){
		super(x,y,w,h);
	}

	@Override
	public void paint(Graphics g) {
		//将等级数除以背景图片数量，等级从一开始，也就是余数从1开始，减一是为了图片从bg01开始
		int bgIdx=this.dto.getNowlevel() % Img.BG_LIST.size()-1;
		bgIdx=bgIdx<0?0:bgIdx;
		//背景图片
		g.drawImage(Img.BG_LIST.get(bgIdx), 0, 0,1162,654, null);
	}

}
