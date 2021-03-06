package com.event.domain;
// Generated 2017-6-11 9:12:32 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.event.util.Compaer;

/**
 * TUser generated by hbm2java
 */
public class TUser implements java.io.Serializable {

	private int userId;
	private String userRealname;
	private String userPassword;
	private Integer userPriviliage;
	private String userName;
	private String userPhone;
	private String userEmail;
	private Integer userMember=0;
	private Integer userMax=0;
	private Integer userComplete=0;
	private Integer userDepartfb=0;
	private Integer userUserfb=0;
	private Set TEventsForEventCommitId = new HashSet(0);
	private Set TTalksForKehuId = new HashSet(0);
	private Set TEventsForKefuId = new HashSet(0);

	private Set TTalksForKefuId = new HashSet(0);
	private Set TEventsForLeadId = new HashSet(0);

	public TUser() {
	}

	public TUser(int userId) {
		this.userId = userId;
	}

	public TUser(int userId, String userRealname, String userPassword, Integer userPriviliage, String userName,
			String userPhone, String userEmail, Integer userMember, Integer userMax, Integer userComplete,
			Integer userDepartfb, Integer userUserfb, Set TEventsForEventCommitId, Set TTalksForKehuId,
			Set TEventsForKefuId,Set TTalksForKefuId, Set TEventsForLeadId) {
		this.userId = userId;
		this.userRealname = userRealname;
		this.userPassword = userPassword;
		this.userPriviliage = userPriviliage;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userMember = userMember;
		this.userMax = userMax;
		this.userComplete = userComplete;
		this.userDepartfb = userDepartfb;
		this.userUserfb = userUserfb;
		this.TEventsForEventCommitId = TEventsForEventCommitId;
		this.TTalksForKehuId = TTalksForKehuId;
		this.TEventsForKefuId = TEventsForKefuId;
		this.TTalksForKefuId = TTalksForKefuId;
		this.TEventsForLeadId = TEventsForLeadId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserRealname() {
		return this.userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserPriviliage() {
		return this.userPriviliage;
	}

	public void setUserPriviliage(Integer userPriviliage) {
		this.userPriviliage = userPriviliage;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserMember() {
		return this.userMember;
	}

	public void setUserMember(Integer userMember) {
		this.userMember = userMember;
	}

	public Integer getUserMax() {
		return this.userMax;
	}

	public void setUserMax(Integer userMax) {
		this.userMax = userMax;
	}

	public Integer getUserComplete() {
		return this.userComplete;
	}

	public void setUserComplete(Integer userComplete) {
		this.userComplete = userComplete;
	}

	public Integer getUserDepartfb() {
		return this.userDepartfb;
	}

	public void setUserDepartfb(Integer userDepartfb) {
		this.userDepartfb = userDepartfb;
	}

	public Integer getUserUserfb() {
		return this.userUserfb;
	}

	public void setUserUserfb(Integer userUserfb) {
		this.userUserfb = userUserfb;
	}

	public Set getTEventsForEventCommitId() {
		return this.TEventsForEventCommitId;
	}

	public void setTEventsForEventCommitId(Set TEventsForEventCommitId) {
		this.TEventsForEventCommitId = TEventsForEventCommitId;
	}

	public Set getTTalksForKehuId() {
		return this.TTalksForKehuId;
	}

	public void setTTalksForKehuId(Set TTalksForKehuId) {
		this.TTalksForKehuId = TTalksForKehuId;
	}

	public Set getTEventsForKefuId() {
		return this.TEventsForKefuId;
	}

	public void setTEventsForKefuId(Set TEventsForKefuId) {
		this.TEventsForKefuId = TEventsForKefuId;
	}

	public Set getTTalksForKefuId() {
		return this.TTalksForKefuId;
	}

	public void setTTalksForKefuId(Set TTalksForKefuId) {
		this.TTalksForKefuId = TTalksForKefuId;
	}

	public Set getTEventsForLeadId() {
		return this.TEventsForLeadId;
	}

	public void setTEventsForLeadId(Set TEventsForLeadId) {
		this.TEventsForLeadId = TEventsForLeadId;
	}

}
