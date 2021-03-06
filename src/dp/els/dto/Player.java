package dp.els.dto;

import java.io.Serializable;


public class Player implements Comparable<Player>,Serializable {

	private static final long serialVersionUID = -4987747885886777342L;
	
	private String name;
	private int point;
	public Player(String name, int point) {
		this.name = name;
		this.point = point;
	}
	public Player(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public int compareTo(Player pla) {
		return pla.point-this.point;
	}
	
}
