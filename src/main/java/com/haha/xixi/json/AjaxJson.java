package com.haha.xixi.json;

import java.util.Map;

/**
 * @author admin
 * @data 2018年10月30日 上午11:44:33
 *
 * @desc 类描述 控制器返回值包装类型
 *       <li>
 */
public class AjaxJson {

	private Integer code = 0;
	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object data = null;// 返回数据
	private Long count;
	private Map<String, Object> attributes;// 其他参数

	public AjaxJson() {

	}

	public AjaxJson(boolean success) {
		this.success = success;
		this.msg = "操作失败!";
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setMsg(String msg, String param) {
		this.msg = msg + param;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
		if (!success)
			this.msg = "操作失败!";
	}

	public void setSuccess(boolean success, String msg) {
		this.success = success;

		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}



	public void setSuccess(Object data, Long count, String msg) {
		this.data = data;
		this.count = count;
		this.msg = msg;
	}

	public static AjaxJson result(AjaxJson json) {

		return json == null ? new AjaxJson(false) : json;

	}

}
