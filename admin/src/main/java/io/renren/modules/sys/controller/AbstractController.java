package io.renren.modules.sys.controller;

import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller公共组件
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2019-01-29 14:59:19
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());


	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Long accountId;
	/**
	 * 注入程序参数
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void init(
			HttpServletRequest request,
			HttpServletResponse response,
			@ApiIgnore @LoginUser Long accountId
			) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.accountId = accountId;
	}

	public Long getAccountId() {
		return accountId;
	}

	protected SysUserEntity getUser() {
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
