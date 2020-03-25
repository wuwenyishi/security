package com.haha.xixi.config;

/**
 * @author weilai
 * @data 2018年11月21日 下午6:25:49
 *
 * @desc 类描述
 *       <li>
 */
public class Global {

	// spring security中使用BCryptPasswordEncoder方法加密,得到密码字符串在最前面增加{bcrypt}标志，存放到数据表中
	// 登陆时进行密码比对,需要删除{bcrypt}
	public static final String PASSWORD_TYPE_PREFIX = "{bcrypt}";

	// backend 后台登录页(首页)访问路径

	public static final String[] LOGIN_URL = new String[] { "/", "/login_page", "/swagger-ui.html/**", "/webjars/springfox-swagger-ui/**", "/swagger-resources/**", "/v2/api-docs" };

	// 不需要token访问路径：用户登陆，新增，短信发送，虚拟拨号,图片服务器预览图片
	public static final String[] USER_REGiSTRY_URL = new String[] { "/userServer/white/**", "/sms/send" ,"/axb/sendRecord","/image/**"};

	// 用于gate-way中判断是否需要token
	public static final String WHITE_URL = "/userServer/white";

	// 内部服务间调度 不拦截
	public static final String[] SCHEDULE_URL = new String[] { "/schedule/**" };

	// Layui框架核心js\css\image;/json/** 是模拟数据，生产环境删除

	public static final String[] STATIC_RESOURCE = new String[] { "/layuiAdmin/**", "/favicon.ico", "/page/**", "/identify/**",  "/userServer/login"};

	// 支付宝\微信\京东异步通知回调地址
	public static final String[] PAY_SynchNotifiy_URL = new String[] { "/pay/*/white/**" };

	// Hystrix 仪表盘访问路径
	public static final String[] HYSTRIX_STREAM_URL = new String[] { "/hystrix.stream", "/hystrix" };

	// rabbitMQ 消息队列名称
	public static final String queueName = "leesky-mq";

	// rabbitMQ 使用topic策略
	public static final String topicExchange = queueName + "-exchange";

	// 图片服务器中 定义图片存放路径
	public static final String IMG_SAVE_PATH = "/home/image/";


}
