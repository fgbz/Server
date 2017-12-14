package phalaenopsis.common.method.init;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.poi.ss.formula.functions.FinanceFunction;

/**
 * 加载Class的工具类
 * @author chunl
 * 2017年7月25日下午3:07:12
 */
public class ClassUtil {
	
	/**
	 * .class长度，用于截取后缀
	 */
	private final static int ClassLegth = 6;

	/**
	 * 隐藏构造函数
	 */
	protected ClassUtil() {
	}
	
	/**
	 * 传递一个接口类。获取工程下所有继承接口的类
	 * @param c 接口类类型
	 * @return 返回继承接口的所有类
	 */
	public static List<Class<?>> getAllClass(final Class<?> c) {
		List<Class<?>> returnClassList = null;

		if (c.isInterface()) {
			String packageName = "phalaenopsis";
			List<Class<?>> allClass = getClasses(packageName); // 获取指定包下的所有类
			if (allClass != null) {
				returnClassList = new ArrayList<Class<?>>();
				for (Class<?> classes : allClass) {
					if (c.isAssignableFrom(classes)) {
						if (!c.equals(classes)) {
							returnClassList.add(classes);
						}
					}
				}
			}
		}
		return returnClassList;
	}


	/**
	 * packageName
	 * @param classLocation 类所在路径
	 * @param packageName 包名称 
	 * @return 返回包下所有class
	 */
	public static String[] getPackageAllClassName(final String classLocation, final String packageName) {
		// 将packageName分解
		String[] packagePathSplit = packageName.split("[.]");
		String realClassLocation = classLocation;
		int packageLength = packagePathSplit.length;
		for (int i = 0; i < packageLength; i++) {
			realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
		}

		File packeageDir = new File(realClassLocation);
		if (packeageDir.isDirectory()) {
			String[] allClassName = packeageDir.list();
			return allClassName;
		}
		return null;
	}

	/**
	 * 从包package中获取所有的Class 
	 * @param packageName 包名称
	 * @return 返回包下所有Class
	 */
	public static List<Class<?>> getClasses(final String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		boolean recursive = true;
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				}

				// else if ("jar".equals(protocol)) {
				// JarFile jar;
				// try {
				// // 获取jar
				// jar = ((JarURLConnection) url.openConnection()).getJarFile();
				// // 从此jar包 得到一个枚举类
				// Enumeration<JarEntry> entries = jar.entries();
				// // 同样的进行循环迭代
				// while (entries.hasMoreElements()) {
				// // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
				// JarEntry entry = entries.nextElement();
				// String name = entry.getName();
				// // 如果是以/开头的
				// if (name.charAt(0) == '/') {
				// // 获取后面的字符串
				// name = name.substring(1);
				// }
				//
				// // 如果前半部分和定义的包名相同
				// if (name.startsWith(packageDirName)) {
				// int idx = name.lastIndexOf('/');
				// // 如果以"/"结尾 是一个包
				// if (idx != -1) {
				// // 获取包名 把"/"替换成"."
				// packageName = name.substring(0, idx).replace('/', '.');
				// }
				// // 如果可以迭代下去 并且是一个包
				// if ((idx != -1) || recursive) {
				// // 如果是一个.class文件 而且不是目录
				// if (name.endsWith(".class") && !entry.isDirectory()) {
				// // 去掉后面的".class" 获取真正的类名
				// String className = name.substring(packageName.length() + 1,
				// name.length() - 6);
				// try {
				// // 添加到classes
				// classes.add(Class.forName(packageName + '.' + className));
				// } catch (ClassNotFoundException e) {
				// e.printStackTrace();
				// }
				// }
				// }
				// }
				// }
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				// }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/** 以文件的形式来获取包下的所有Class 
	 * @param packageName 包名
	 * @param packagePath 包路径
	 * @param recursive 是否循环
	 * @param classes 类
	 */
	public static void findAndAddClassesInPackageByFile(final String packageName, final String packagePath, final boolean recursive,
			final List<Class<?>> classes) {
		File dir = new File(packagePath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		File[] dirfiles = dir.listFiles(new FileFilter() {
			public boolean accept(final File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});

		for (File file : dirfiles) {
			if (file.isDirectory()) {
				if (!file.getName().equals("util")) {
					findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(),
							recursive, classes);
				}
			} else {
				String className = file.getName().substring(0, file.getName().length() - ClassLegth);
				try {
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
				}
			}
		}
	}

}
