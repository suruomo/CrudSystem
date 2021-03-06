package com.demo.model;

import java.io.Serializable;

/**
 * @author 苏若墨
 */
public class Log implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.module
     *
     * @mbggenerated
     */
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.method
     *
     * @mbggenerated
     */
    private String method;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.parameter
     *
     * @mbggenerated
     */
    private String parameter;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.response_data
     *
     * @mbggenerated
     */
    private String responseTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.date
     *
     * @mbggenerated
     */
    private String date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.commit
     *
     * @mbggenerated
     */
    private String commit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.ip
     *
     * @mbggenerated
     */
    private String ip;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.id
     *
     * @return the value of sys_log.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.id
     *
     * @param id the value for sys_log.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.user_id
     *
     * @return the value of sys_log.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.user_id
     *
     * @param userId the value for sys_log.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.user_name
     *
     * @return the value of sys_log.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.user_name
     *
     * @param userName the value for sys_log.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.module
     *
     * @return the value of sys_log.module
     *
     * @mbggenerated
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.module
     *
     * @param module the value for sys_log.module
     *
     * @mbggenerated
     */
    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.method
     *
     * @return the value of sys_log.method
     *
     * @mbggenerated
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.method
     *
     * @param method the value for sys_log.method
     *
     * @mbggenerated
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.parameter
     *
     * @return the value of sys_log.parameter
     *
     * @mbggenerated
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.parameter
     *
     * @param parameter the value for sys_log.parameter
     *
     * @mbggenerated
     */
    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.response_data
     *
     * @return the value of sys_log.response_data
     *
     * @mbggenerated
     */
    public String getResponseTime() {
        return responseTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.response_data
     *
     * @param responseTime the value for sys_log.response_data
     *
     * @mbggenerated
     */
    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime == null ? null : responseTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.date
     *
     * @return the value of sys_log.date
     *
     * @mbggenerated
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.date
     *
     * @param date the value for sys_log.date
     *
     * @mbggenerated
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.commit
     *
     * @return the value of sys_log.commit
     *
     * @mbggenerated
     */
    public String getCommit() {
        return commit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.commit
     *
     * @param commit the value for sys_log.commit
     *
     * @mbggenerated
     */
    public void setCommit(String commit) {
        this.commit = commit == null ? null : commit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.ip
     *
     * @return the value of sys_log.ip
     *
     * @mbggenerated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.ip
     *
     * @param ip the value for sys_log.ip
     *
     * @mbggenerated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}