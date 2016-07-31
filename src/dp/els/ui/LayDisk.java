package dp.els.ui;

import java.awt.Graphics;

public class LayDisk extends LayerData {
	 public LayDisk(int x, int y,int w,int h){
	    	super(x,y,w,h);
	    }
	    
	  public void paint(Graphics g){
	    	this.createWindow(g);
	    	this.showData(Img.DISK, this.dto.getDisRecode(), g);
	  }
}
