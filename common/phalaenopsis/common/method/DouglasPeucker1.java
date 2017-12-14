package phalaenopsis.common.method;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import phalaenopsis.common.entity.Point;


public class DouglasPeucker1 {
	
	public void Reduction(ArrayList<ENPoint> pGPSArrayInit)
	{
		//ArrayList<ENPoint> pGPSArrayInit = new ArrayList<ENPoint>();
		ArrayList<ENPoint> pGPSArrayFilter = new ArrayList<ENPoint>();// 过滤后的经纬度坐标数组
		
		double DMax = 30.0;// 设定最大距离误差阈值
		pGPSArrayFilter.add(pGPSArrayInit.get(0));// 获取第一个原始经纬度点坐标并添加到过滤后的数组中
		pGPSArrayFilter.add(pGPSArrayInit.get(pGPSArrayInit.size() - 1));// 获取最后一个原始经纬度点坐标并添加到过滤后的数组中
		ENPoint[] enpInit = new ENPoint[pGPSArrayInit.size()];// 使用一个点数组接收所有的点坐标，用于后面的压缩
		Iterator<ENPoint> iInit = pGPSArrayInit.iterator();
		int jj = 0;
		while (iInit.hasNext()) {
			enpInit[jj] = iInit.next();
			jj++;
		} // 将ArrayList中的点坐标拷贝到点数组中
		int start = 0;// 起始下标
		int end = pGPSArrayInit.size() - 1;// 结束下标
		TrajCompressC(enpInit, pGPSArrayFilter, start, end, DMax);// DP压缩算法
		
		
		
		//return pGPSArrayFilter;
		
		//System.out.println(pGPSArrayFilter.size());// 输出压缩后的点数
	}
	
	
	public static ArrayList<ENPoint> getENPointFromFile(File fGPS) throws Exception {
		ArrayList<ENPoint> pGPSArray = new ArrayList<ENPoint>();
		if (fGPS.exists() && fGPS.isFile()) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fGPS));
			BufferedReader bReader = new BufferedReader(read);
			String str;
			String[] strGPS;
			int i = 0;
			while ((str = bReader.readLine()) != null) {
				strGPS = str.split(" ");
				ENPoint p = new ENPoint();
				p.id = i;
				i++;
				p.pe = (dfTodu(strGPS[3]));
				p.pn = (dfTodu(strGPS[5]));
				pGPSArray.add(p);
			}
			bReader.close();
		}
		return pGPSArray;
	}

	/**
	 * 函数功能：将过滤后的点的经纬度坐标、平均距离误差、压缩率写到结果文件中
	 * 
	 * @param outGPSFile：结果文件
	 * @param pGPSPointFilter：过滤后的点
	 * @param mDerror：平均距离误差
	 * @param cRate：压缩率
	 * @throws Exception
	 */
	public static void writeFilterPointToFile(File outGPSFile, ArrayList<ENPoint> pGPSPointFilter, double mDerror,
			double cRate) throws Exception {
		Iterator<ENPoint> iFilter = pGPSPointFilter.iterator();
		RandomAccessFile rFilter = new RandomAccessFile(outGPSFile, "rw");
		while (iFilter.hasNext()) {
			ENPoint p = iFilter.next();
			String sFilter = p.getResultString() + "\n";
			byte[] bFilter = sFilter.getBytes();
			rFilter.write(bFilter);
		}
		String strmc = "#" + Integer.toString(pGPSPointFilter.size()) + "," + Double.toString(mDerror) + ","
				+ Double.toString(cRate) + "%" + "#" + "\n";
		byte[] bmc = strmc.getBytes();
		rFilter.write(bmc);

		rFilter.close();
	}

	/**
	 * 函数功能：将转换后的原始经纬度数据点存到文件中
	 * 
	 * @param outGPSFile
	 * @param pGPSPointFilter
	 * @throws Exception
	 */
	public static void writeInitPointToFile(File outGPSFile, ArrayList<ENPoint> pGPSPointFilter) throws Exception {
		Iterator<ENPoint> iFilter = pGPSPointFilter.iterator();
		RandomAccessFile rFilter = new RandomAccessFile(outGPSFile, "rw");
		while (iFilter.hasNext()) {
			ENPoint p = iFilter.next();
			String sFilter = p.toString() + "\n";
			byte[] bFilter = sFilter.getBytes();
			rFilter.write(bFilter);
		}
		rFilter.close();
	}

	/**
	 * 函数功能：将数组中的经纬度点坐标数据写入测试文件中，用于可视化测试
	 * 
	 * @param outGPSFile：文件对象
	 * @param pGPSPointFilter：点数组
	 * @throws Exception
	 */
	public static void writeTestPointToFile(File outGPSFile, ArrayList<ENPoint> pGPSPointFilter) throws Exception {
		Iterator<ENPoint> iFilter = pGPSPointFilter.iterator();
		RandomAccessFile rFilter = new RandomAccessFile(outGPSFile, "rw");
		while (iFilter.hasNext()) {
			ENPoint p = iFilter.next();
			String sFilter = p.getTestString() + "\n";
			byte[] bFilter = sFilter.getBytes();
			rFilter.write(bFilter);
		}
		rFilter.close();
	}

	/**
	 * 函数功能：将原始经纬度坐标数据转换成度
	 * 
	 * @param str：原始经纬度坐标
	 * @return ：返回对于的度数据
	 */
	public static double dfTodu(String str) {
		int indexD = str.indexOf('.');
		String strM = str.substring(0, indexD - 2);
		String strN = str.substring(indexD - 2);
		double d = Double.parseDouble(strM) + Double.parseDouble(strN) / 60;
		return d;
	}

	/**
	 * 函数功能：保留一个double数的小数点后六位
	 * 
	 * @param d：原始double数
	 * @return 返回转换后的double数
	 */
	public static double getPointSix(double d) {
		DecimalFormat df = new DecimalFormat("0.000000");
		return Double.parseDouble(df.format(d));
	}

	/**
	 * 函数功能：使用三角形面积（使用海伦公式求得）相等方法计算点pX到点pA和pB所确定的直线的距离
	 * 
	 * @param pA：起始点
	 * @param pB：结束点
	 * @param pX：第三个点
	 * @return distance：点pX到pA和pB所在直线的距离
	 */
	public static double distToSegment(ENPoint pA, ENPoint pB, ENPoint pX) {
		double a = Math.abs(geoDist(pA, pB));
		double b = Math.abs(geoDist(pA, pX));
		double c = Math.abs(geoDist(pB, pX));
		double p = (a + b + c) / 2.0;
		double s = Math.sqrt(Math.abs(p * (p - a) * (p - b) * (p - c)));
		double d = s * 2.0 / a;
		return d;
	}

	/**
	 * 函数功能：用老师给的看不懂的方法求两个经纬度点之间的距离
	 * 
	 * @param pA：起始点
	 * @param pB：结束点
	 * @return distance：距离
	 */
	public static double geoDist(ENPoint pA, ENPoint pB) {
		double radLat1 = Rad(pA.pn);
		double radLat2 = Rad(pB.pn);
		double delta_lon = Rad(pB.pe - pA.pe);
		double top_1 = Math.cos(radLat2) * Math.sin(delta_lon);
		double top_2 = Math.cos(radLat1) * Math.sin(radLat2)
				- Math.sin(radLat1) * Math.cos(radLat2) * Math.cos(delta_lon);
		double top = Math.sqrt(top_1 * top_1 + top_2 * top_2);
		double bottom = Math.sin(radLat1) * Math.sin(radLat2)
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(delta_lon);
		double delta_sigma = Math.atan2(top, bottom);
		double distance = delta_sigma * 6378137.0;
		return distance;
	}

	/**
	 * 函数功能：角度转弧度
	 * 
	 * @param d：角度
	 * @return 返回的是弧度
	 */
	public static double Rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 函数功能：根据最大距离限制，采用DP方法递归的对原始轨迹进行采样，得到压缩后的轨迹
	 * 
	 * @param enpInit：原始经纬度坐标点数组
	 * @param enpArrayFilter：保持过滤后的点坐标数组
	 * @param start：起始下标
	 * @param end：终点下标
	 * @param DMax：预先指定好的最大距离误差
	 */
	public static void TrajCompressC(ENPoint[] enpInit, ArrayList<ENPoint> enpArrayFilter, int start, int end,
			double DMax) {
		if (start < end) {// 递归进行的条件
			double maxDist = 0;// 最大距离
			int cur_pt = 0;// 当前下标
			for (int i = start + 1; i < end; i++) {
				double curDist = distToSegment(enpInit[start], enpInit[end], enpInit[i]);// 当前点到对应线段的距离
				if (curDist > maxDist) {
					maxDist = curDist;
					cur_pt = i;
				} // 求出最大距离及最大距离对应点的下标
			}
			// 若当前最大距离大于最大距离误差
			if (maxDist >= DMax) {
				enpArrayFilter.add(enpInit[cur_pt]);// 将当前点加入到过滤数组中
				// 将原来的线段以当前点为中心拆成两段，分别进行递归处理
				TrajCompressC(enpInit, enpArrayFilter, start, cur_pt, DMax);
				TrajCompressC(enpInit, enpArrayFilter, cur_pt, end, DMax);
			}
		}
	}

	/**
	 * 函数功能：求平均距离误差
	 * 
	 * @param pGPSArrayInit：原始数据点坐标
	 * @param pGPSArrayFilterSort：过滤后的数据点坐标
	 * @return ：返回平均距离
	 */
	public static double getMeanDistError(ArrayList<ENPoint> pGPSArrayInit, ArrayList<ENPoint> pGPSArrayFilterSort) {
		double sumDist = 0.0;
		for (int i = 1; i < pGPSArrayFilterSort.size(); i++) {
			int start = pGPSArrayFilterSort.get(i - 1).id;
			int end = pGPSArrayFilterSort.get(i).id;
			for (int j = start + 1; j < end; j++) {
				sumDist += distToSegment(pGPSArrayInit.get(start), pGPSArrayInit.get(end), pGPSArrayInit.get(j));
			}
		}
		double meanDist = sumDist / (pGPSArrayInit.size());
		return meanDist;
	}
}
