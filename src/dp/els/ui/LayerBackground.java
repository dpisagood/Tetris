package dp.els.ui;

import java.awt.Graphics;

public class LayerBackground extends Layer {
	public LayerBackground(int x ,int y, int w, int h){
		super(x,y,w,h);
	}

	@Override
	public void paint(Graphics g) {
		//���ȼ������Ա���ͼƬ�������ȼ���һ��ʼ��Ҳ����������1��ʼ����һ��Ϊ��ͼƬ��bg01��ʼ
		int bgIdx=this.dto.getNowlevel() % Img.BG_LIST.size()-1;
		bgIdx=bgIdx<0?0:bgIdx;
		//����ͼƬ
		g.drawImage(Img.BG_LIST.get(bgIdx), 0, 0,1162,654, null);
	}

}
