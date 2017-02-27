package com.yqsh.ifmp.common.model;

import java.util.Date;

import com.yqsh.ifmp.util.DateUtil2;

/**
 * 调存储过程SP_InsertWstData所需参数
 * @author LJC
 *
 */
public class Deduction{
	/**流水状态0：正常1：异常*/
	private String recState="0";
	/**账号id*/
	private String accountID="";
	/**物理卡号*/
	private String serialNo="";
	/**机号*/
	private String posId="";
	/**部门编号*/
	private String dpBh="";
	/**应用号 0:现金 1：补助*/
	private int appID=0;
	/**流水类型 0：消费流水 1：充值流水 2：取款流水 3：补助流水 4：挂失流水*/
	private String recType="0";
	/**交易时间*/
	private Date consTime=DateUtil2.toDateTime(DateUtil2.getDateTime());
	/**采集时间*/
	private Date gatherTime=DateUtil2.toDateTime(DateUtil2.getDateTime());
	/**交易金额*/
	private Double consFare=0.0;
	/**手续费*/
	private Double consO_1=0.0;
	/**卡费*/
	private Double consO_2=0.0;
	/**押金*/
	private Double consO_3=0.0;
	/**管理费*/
	private Double consO_4=0.0;
	/**操作员号*/
	private String oper_id="";
	/**餐次号*/
	private String meal_id="1";
	/**工作站编号*/
	private String station_id="";
	/**商品键位号*/
	private int goodsID=0;
	/**商品份数*/
	private int goodsCount=0;
	/**数据校验Key值*/
	private String fRecKey="";  
	@Override
	public String toString() {
		return "\n"
				+ "Deduction [recState=" + recState + ", accountID=" + accountID + ", serialNo=" + serialNo + ", posId="
				+ posId + ", dpBh=" + dpBh + ", appID=" + appID + ", recType=" + recType + ", consTime=" + consTime
				+ ", gatherTime=" + gatherTime + ", consFare=" + consFare + ", consO_1=" + consO_1 + ", consO_2="
				+ consO_2 + ", consO_3=" + consO_3 + ", consO_4=" + consO_4 + ", oper_id=" + oper_id + ", meal_id="
				+ meal_id + ", station_id=" + station_id + ", goodsID=" + goodsID + ", goodsCount=" + goodsCount
				+ ", fRecKey=" + fRecKey + "]";
	}
	public String getRecState() {
		return recState;
	}
	public void setRecState(String recState) {
		this.recState = recState;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getDpBh() {
		return dpBh;
	}
	public void setDpBh(String dpBh) {
		this.dpBh = dpBh;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getRecType() {
		return recType;
	}
	public void setRecType(String recType) {
		this.recType = recType;
	}
	public Date getConsTime() {
		return consTime;
	}
	public void setConsTime(Date consTime) {
		this.consTime = consTime;
	}
	public Date getGatherTime() {
		return gatherTime;
	}
	public void setGatherTime(Date gatherTime) {
		this.gatherTime = gatherTime;
	}
	public Double getConsFare() {
		return consFare;
	}
	public void setConsFare(Double consFare) {
		this.consFare = consFare;
	}
	public Double getConsO_1() {
		return consO_1;
	}
	public void setConsO_1(Double consO_1) {
		this.consO_1 = consO_1;
	}
	public Double getConsO_2() {
		return consO_2;
	}
	public void setConsO_2(Double consO_2) {
		this.consO_2 = consO_2;
	}
	public Double getConsO_3() {
		return consO_3;
	}
	public void setConsO_3(Double consO_3) {
		this.consO_3 = consO_3;
	}
	public Double getConsO_4() {
		return consO_4;
	}
	public void setConsO_4(Double consO_4) {
		this.consO_4 = consO_4;
	}
	public String getOper_id() {
		return oper_id;
	}
	public void setOper_id(String oper_id) {
		this.oper_id = oper_id;
	}
	public String getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(String meal_id) {
		this.meal_id = meal_id;
	}
	public String getStation_id() {
		return station_id;
	}
	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getfRecKey() {
		return fRecKey;
	}
	public void setfRecKey(String fRecKey) {
		this.fRecKey = fRecKey;
	}
}
