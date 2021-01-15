package com.nwsuaf.entity;

public class ph {

	String cert_id;
	String time;
	String ip;
	String zd;
	String weizhi;
	public String getCert_id() {
		return cert_id;
	}
	public void setCert_id(String cert_id) {
		this.cert_id = cert_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String timr) {
		this.time = timr;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getWeizhi() {
		return weizhi;
	}
	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}
	@Override
	public String toString() {
		return "ph [cert_id=" + cert_id + ", timr=" + time + ", ip=" + ip + ", zd=" + zd + ", weizhi=" + weizhi + "]";
	}
	
}
