package dp.els.util;

public class GameFunction {

	
	
	/**
	 * 计算线程休眠时间
	 * @param level
	 * @return
	 */
	public static long getSleepTimeByLevel(int level){
		long sleep=(-40*level+740);
		sleep=sleep<100 ? 100 : sleep;
		return sleep;
	}
}
