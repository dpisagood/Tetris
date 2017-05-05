package dp.els.dao;

import java.util.HashMap;
/**
 * 对本地硬盘数据进行操作的方法
 * @author DP
 *
 */
public abstract class DataDisk implements IData{
	protected  final String filepath;
	
	public DataDisk(HashMap<String,String> param){
		this.filepath=param.get("path");
	}
}
