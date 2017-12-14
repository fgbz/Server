//package phalaenopsis.common.method.typehandler;
//
//import java.security.interfaces.RSAKey;
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.MappedJdbcTypes;
//import org.apache.ibatis.type.MappedTypes;
//import org.apache.ibatis.type.TypeHandler;
//
//import oracle.spatial.geometry.JGeometry;
//import oracle.sql.STRUCT;
//
//@MappedTypes({JGeometry.class})
//@MappedJdbcTypes({JdbcType.STRUCT})
//public class SdoGeometryTypeHandler implements TypeHandler<JGeometry> {
//
//	/**
//	 * mybaits将java类型数据转换为对应数据库类型
//	 * 
//	 * @param ps
//	 *            当前的PreparedStatement对象
//	 * @param i
//	 *            当前参数的位置
//	 * @param parameter
//	 *            当前参数的Java对象
//	 * @param jdbcType
//	 *            当前参数的数据库类型
//	 * @throws SQLException
//	 */
//	@Override
//	public void setParameter(PreparedStatement ps, int i, JGeometry parameter, JdbcType jdbcType) throws SQLException {
//		STRUCT dbObject = JGeometry.store(parameter, ps.getConnection());
//		ps.setObject(i, dbObject);
//	}
//
//	/**
//	 * 用于mybatis获取结果集将数据库类型转换为java类型
//	 * 
//	 * @param rs
//	 *            结果集对象
//	 * @param columnName
//	 *            当前的字段名称
//	 * @return 返回转换后的Java对象
//	 */
//	@Override
//	public JGeometry getResult(ResultSet rs, String columnName) throws SQLException {
//		STRUCT st = (STRUCT) rs.getObject(columnName);
//		if (st != null) {
//			return JGeometry.load(st);
//		}
//		return null;
//	}
//
//	/**
//	 * 用于mybatis通过字段位置将数据库类型字段转换为Java类型
//	 * 
//	 * @param rs
//	 *            当前的结果集
//	 * @param columnIndex
//	 *            当前字段的位置
//	 * @return 转换后的Java对象
//	 * @throws SQLException
//	 */
//	@Override
//	public JGeometry getResult(ResultSet rs, int columnIndex) throws SQLException {
//		STRUCT st = (STRUCT)rs.getObject(columnIndex);
//		if (st != null)
//			return JGeometry.load(st);
//		return null;
//	}
//
//	/**
//	 * 用于mybatis调用存储过程将数据库类型转换为Java类型
//	 * 
//	 * @param cs
//	 *            当前的CallableStatement执行后的CallableStatement
//	 * @param columnIndex
//	 *            当前输出参数的位置
//	 * @return 返回转换后的Java类型
//	 * @throws SQLException
//	 */
//	@Override
//	public JGeometry getResult(CallableStatement cs, int columnIndex) throws SQLException {
//		STRUCT st = (STRUCT)cs.getObject(columnIndex);
//		if (null == st)
//			return JGeometry.load(st);
//		return null;
//	}
//
//}
