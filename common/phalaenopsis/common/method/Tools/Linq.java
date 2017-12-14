package phalaenopsis.common.method.Tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class Linq {

	protected Linq() {
	}

	public enum Operate {
		equal, contain
	}

	/**
	 * 返回集合的第一个值。默认为null
	 * 
	 * @param list
	 * @return
	 */
	public static <T> T firstOrDefault(List<T> list) {
		if (null == list || 0 == list.size())
			return null;

		return list.get(0);
	}

	/**
	 * 返回集合最后一个值。默认为null
	 * 
	 * @param list
	 * @return
	 */
	public static <T> T getLast(List<T> list) {
		if (null == list || 0 == list.size())
			return null;

		return list.get(list.size() - 1);
	}

	/**
	 * 在集合中查询符合条件的单个实体
	 * 
	 * @param list
	 *            集合
	 * @param fieldName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 返回符合查询条件的实体。默认值为null
	 */
	public static <T> T extEquals(List<T> list, String fieldName, Object value) {
		try {
			// 1,判断list是否为null
			if (null == list || 0 == list.size())
				return null;

			// 2,循环判断是否有相等的值
			for (T t : list) {
				Field f = t.getClass().getDeclaredField(fieldName);
				f.setAccessible(true); // 设置些属性是可以访问的
				Object object = f.get(t);
				if (value.equals(object)) { // 判断是否相等。其中1与"1"是不等的
					return t;
				}

			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询集合中某一特定列的值集合
	 * 
	 * @param list
	 *            查询的集合
	 * @param fieldName
	 *            指定哪一列,如"Name"
	 * @return 返回对应列的所有值
	 */
	public static <T, K> K extSelectOne(List<T> list, String fieldName) {
		try {
			// 1,判断list是否为null
			if (null == list || 0 == list.size())
				return null;

			// 2,循环判断是否有相等的值
			for (T t : list) {
				Field f = t.getClass().getDeclaredField(fieldName);
				f.setAccessible(true); // 设置些属性是可以访问的
				K object = (K) f.get(t);
				return object;

			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询集合中某一特定列的值集合
	 * 
	 * @param list
	 *            查询的集合
	 * @param fieldName
	 *            指定哪一列,如"Name"
	 * @return 返回对应列的所有值
	 */
	public static <T, K> List<K> extSelect(List<T> list, String fieldName) {
		List<K> result = new ArrayList<K>();

		try {
			// 1,判断list是否为null
			if (null == list || 0 == list.size())
				return null;

			// 2,循环判断是否有相等的值
			for (T t : list) {
				Field f = t.getClass().getDeclaredField(fieldName);
				f.setAccessible(true); // 设置些属性是可以访问的
				K object = (K) f.get(t);
				result.add(object);

			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 在集合中查询符合条件的实体集合
	 * 
	 * @param list
	 *            集合
	 * @param fieldName
	 * @param value
	 * @return 返回符合条件的集合。默认为空值
	 */
	public static <T> List<T> extEqualsList(List<T> list, String fieldName, Object value) {
		List<T> result = new ArrayList<T>();
		try {

			// 1,判断list是否为null
			if (null == list || 0 == list.size())
				return result;

			// 2,循环判断是否有相等的值
			for (T t : list) {
				Field f = t.getClass().getDeclaredField(fieldName);
				f.setAccessible(true); // 设置些属性是可以访问的
				Object object = f.get(t);
				if (value.equals(object)) { // 判断是否相等。其中1与"1"是不等的
					result.add(t);
				}
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 在集合中查询符合条件的实体集合
	 * 
	 * @param list
	 *            集合
	 * @param fieldName
	 * @param value
	 * @param 判断符号 > = <
	 * @return 返回符合条件的集合。默认为空值
	 */
	public static <T> List<T> extEqualsList(List<T> list, String fieldName, Object value, String symbol) {
		List<T> result = new ArrayList<T>();
		try {

			// 1,判断list是否为null
			if (null == list || 0 == list.size() || null == symbol)
				return result;

			// 2,循环判断是否有相等的值
			for (T t : list) {
				Field f = t.getClass().getDeclaredField(fieldName);
				f.setAccessible(true); // 设置些属性是可以访问的
				Object object = f.get(t);

				if (symbol.equals(">")) {
					double columnVal = (double) object;
					double dvalue = Double.valueOf(value.toString());
					if (columnVal > dvalue) {
						result.add(t);
					}
				} else if (symbol.equals("<")) {
					double columnVal = (double) object;
					double dvalue = (double) value;
					if (columnVal < dvalue) {
						result.add(t);
					}
				} else if (symbol.equals("=")) {
					double columnVal = (double) object;
					double dvalue = (double) value;
					if (columnVal == dvalue) {
						result.add(t);
					}
				}

				/*
				 * if (value.equals(object)) { // 判断是否相等。其中1与"1"是不等的
				 * result.add(t); }
				 */
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 使用样列:Student s = Linq.extEquals(students, new String[]{"Name","Age"}, new
	 * Object[]{"lis",20})
	 * 
	 * @param list
	 * @param fieldNames
	 * @param values
	 * @return
	 */
	public static <T> T extEquals(List<T> list, String[] fieldNames, Object[] values) {
		try {
			// 1,判断list是否为null
			if (null == list || 0 == list.size())
				return null;

			// 2,判断参数不能为空
			if (null == fieldNames || 0 == fieldNames.length || null == values || 0 == values.length)
				return null;

			// 2,循环判断是否有相等的值
			for (T t : list) {
				for (int i = 0; i < fieldNames.length; i++) {
					Field f = t.getClass().getDeclaredField(fieldNames[i]);
					f.setAccessible(true); // 设置些属性是可以访问的
					Object object = f.get(t);
					if (values[i].equals(object)) { // 判断是否相等。其中1与"1"是不等的
						return t;
					}
				}
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用样列:List<Student> s = Linq.extEqualsList(students, new
	 * String[]{"Name","Age"}, new Object[]{"lis",20})
	 * 
	 * @param list
	 * @param fieldNames
	 * @param values
	 * @return
	 */
	public static <T> List<T> extEqualsList(List<T> list, String[] fieldNames, Object[] values) {
		List<T> result = new ArrayList<T>();
		try {
			// 1,判断list是否为null
			if (null == list || 0 == list.size())
				return null;

			// 2,判断参数不能为空
			if (null == fieldNames || 0 == fieldNames.length || null == values || 0 == values.length)
				return null;

			// 2,循环判断是否有相等的值
			for (T t : list) {
				for (int i = 0; i < fieldNames.length; i++) {
					Field f = t.getClass().getDeclaredField(fieldNames[i]);
					f.setAccessible(true); // 设置些属性是可以访问的
					Object object = f.get(t);
					if (values[i].equals(object)) { // 判断是否相等。其中1与"1"是不等的
						result.add(t);
					}
				}
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断某一个集合中是否包含一个字段是某值
	 * 
	 * @param list
	 * @param fileName
	 * @param value
	 * @return
	 */
	public static <T> boolean extExists(List<T> list, String fieldName, Object value) {
		try {
			// 1,判断list是否为null
			if (null == list || 0 == list.size())
				return false;

			// 2,循环判断是否有相等的值
			for (T t : list) {
				Field f = t.getClass().getDeclaredField(fieldName);
				f.setAccessible(true); // 设置些属性是可以访问的
				Object object = (Object) f.get(t);
				if (object.equals(value)) {
					return true;
				}
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 取集合某个字段的最大值
	 * 
	 * @param list
	 * @param fileName
	 * @return
	 */
	public static <T, K> K extMax(List<T> list, String fileName) {

		K maxvalue = null;
		try {
			// 1,判断list是否为null
			if (null == list || 0 == list.size())
				return null;

			// 2,判断参数不能为空
			if (null == fileName || 0 == fileName.length())
				return null;

			Field f;
			for (int i = 0; i < list.size(); i++) {
				f = list.get(i).getClass().getDeclaredField(fileName);
				f.setAccessible(true); // 设置些属性是可以访问的
				if (null == maxvalue) {
					maxvalue = (K) f.get(list.get(i));
				} else {
					K object = (K) f.get(list.get(i));
					if (null != object) {
						if (object instanceof Comparable) {
							if (((Comparable) object).compareTo(maxvalue) > 0) {
								maxvalue = object;
							}
						}
					}
				}
			}

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return maxvalue;
	}

	/**
	 * 判断两个基本类型数组对象，是否相等
	 * @param t1s 要比较的数组对象1
	 * @param t2s 要比较的数组对象2
	 * @return 返回两个数组对象是否相等
	 */
	@SuppressWarnings("unused")
	public static final <T> boolean equals(T[] t1s, T[] t2s) {
		if (null == t1s || null == t2s)
			return false;

		if (t1s.length != t1s.length)
			return false;

		for (int i = 0; i < t1s.length; i++) {
			if (!t1s[i].equals(t2s[i])){
				return false;
			}
		}
		return true;
	}

}
