package dp.els.dao;

import java.util.HashMap;
/**
 * �Ա���Ӳ�����ݽ��в����ķ���
 * @author DP
 *
 */
public abstract class DataDisk implements IData{
	protected  final String filepath;
	
	public DataDisk(HashMap<String,String> param){
		this.filepath=param.get("path");
	}
}
