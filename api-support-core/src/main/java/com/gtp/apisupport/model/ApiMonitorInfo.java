package com.gtp.apisupport.model;

import java.io.Serializable;
import java.util.Date;

import com.gtp.apisupport.annotation.ApiDescribe;

/**
 * 操作日志拦截信息
 * 
 * @author gaotingping@cyberzone.cn
 */
@ApiDescribe("监控日志")
public class ApiMonitorInfo implements Serializable{
	
	private static final long serialVersionUID = -1123784049980669895L;

	@ApiDescribe("")
    private Long id;

    @ApiDescribe("操作者标识")
    private String playId;

    @ApiDescribe("时间")
    private Date ctime;

    @ApiDescribe("自定义标识")
    private String customKey;

    @ApiDescribe("服务标识，即serviceModule")
    private String server;

    @ApiDescribe("参数")
    private String args;

    @ApiDescribe("耗时，单位毫秒")
    private Long cost;

    @ApiDescribe("扩展字段1")
    private String field1;

    @ApiDescribe("扩展字段2")
    private String field2;

    @ApiDescribe("扩展字段3")
    private String field3;

    @ApiDescribe("扩展字段4")
    private String field4;

    @ApiDescribe("扩展字段5")
    private String field5;

    @ApiDescribe("方法标识，即serviceNumber")
    private String serviceCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getCustomKey() {
        return customKey;
    }

    public void setCustomKey(String customKey) {
        this.customKey = customKey;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}