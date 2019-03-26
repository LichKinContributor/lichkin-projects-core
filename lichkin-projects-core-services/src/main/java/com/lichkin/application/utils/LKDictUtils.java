package com.lichkin.application.utils;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.db.beans.eq_;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 字典工具类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LKDictUtils {

	/**
	 * 连接字典表
	 * @param sql SQL语句对象
	 * @param key 字典键
	 * @param compId 公司ID
	 * @param categoryCode 类目名称
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 * @param locale 国际化编码
	 */
	protected static void leftJoinDictionary(QuerySQL sql, String key, String compId, String categoryCode, int columnResId, int tableIdx, String locale) {
		sql.select(tableIdx, SysDictionaryR.dictName, key);
		sql.select(tableIdx, SysDictionaryR.dictCode, key + "DictCode");

		sql.leftJoin(SysDictionaryEntity.class,

				new Condition(null, new eq_(tableIdx, SysDictionaryR.dictCode, columnResId)),

				new Condition(true, new eq(tableIdx, SysDictionaryR.categoryCode, categoryCode)),

				new Condition(true, new eq(tableIdx, SysDictionaryR.compId, compId)),

				new Condition(true, new eq(tableIdx, SysDictionaryR.locale, locale))

		);
	}


	/**
	 * 连接字典表
	 * @param sql SQL语句对象
	 * @param key 字典键
	 * @param compId 公司ID
	 * @param categoryCode 类目名称
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	protected static void leftJoinDictionary(QuerySQL sql, String key, String compId, String categoryCode, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, key, compId, categoryCode, columnResId, tableIdx, "zh_CN");
	}


	/**
	 * 连接字典表（类目权限类型）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void categoryAuthType(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "authType", LKFrameworkStatics.LichKin, "CATEGORY_AUTH_TYPE", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（在用状态）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void usingStatus(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "usingStatus", LKFrameworkStatics.LichKin, "USING_STATUS", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（在用状态）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void usingStatus4Employee(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "usingStatus", LKFrameworkStatics.LichKin, "EMPLOYEE_USING_STATUS", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（客户端类型）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void clientType(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "clientType", LKFrameworkStatics.LichKin, "CLIENT_TYPE", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（操作类型）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void operType(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "operType", LKFrameworkStatics.LichKin, "OPERATION_TYPE", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（性别）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void gender(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "gender", LKFrameworkStatics.LichKin, "GENDER", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（实名认证等级）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void authentication(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "authentication", LKFrameworkStatics.LichKin, "AUTHENTICATION", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（民族）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void nation(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "nation", LKFrameworkStatics.LichKin, "NATION", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（学历）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void education(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "education", LKFrameworkStatics.LichKin, "EDUCATION", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（学位）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void degree(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "degree", LKFrameworkStatics.LichKin, "DEGREE", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（婚姻状态）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void maritalStatus(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "maritalStatus", LKFrameworkStatics.LichKin, "MARITAL_STATUS", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（管理员业务操作类型）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void adminBusType(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "busType", LKFrameworkStatics.LichKin, "ADMIN_BUS_TYPE", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（员工业务操作类型）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void employeeBusType(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "busType", LKFrameworkStatics.LichKin, "EMPLOYEE_BUS_TYPE", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（用户业务操作类型）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void userBusType(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "busType", LKFrameworkStatics.LichKin, "USER_BUS_TYPE", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（地图类型）
	 * @param sql SQL语句对象
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void mapAPI(QuerySQL sql, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "mapAPI", LKFrameworkStatics.LichKin, "MAP_API", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（职位）
	 * @param sql SQL语句对象
	 * @param compId 公司ID
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void jobTitle(QuerySQL sql, String compId, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "jobTitle", compId, "JOB_TITLE", columnResId, tableIdx);
	}


	/**
	 * 连接字典表（位置编码）
	 * @param sql SQL语句对象
	 * @param compId 公司ID
	 * @param columnResId 列资源ID
	 * @param tableIdx 字典表序号（从0开始）
	 */
	public static void locationCode(QuerySQL sql, String compId, int columnResId, int tableIdx) {
		leftJoinDictionary(sql, "locationCode", compId, "LOCATION_CODE", columnResId, tableIdx);
	}

}
