package dp.els.ui;

import java.awt.Graphics;

public class LayerBackground extends Layer {
	public LayerBackground(int x ,int y, int w, int h){
		super(x,y,w,h);
	}

	@Override
	public void paint(Graphics g) {
		//ȡ��
		int bgIdx=this.dto.getNowlevel() % Img.BG_LIST.size();
		//����ͼƬ
		g.drawImage(Img.BG_LIST.get(bgIdx), 0, 0,1162,654, null);
	}

}
