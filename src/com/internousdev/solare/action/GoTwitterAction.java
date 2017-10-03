package com.internousdev.solare.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.internousdev.solare.util.TwitterOauth;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Twitterでログインする為のクラス
 *
 * @author 堅田 一成
 * @since 1.0
 * @version 1.0
 */
public class GoTwitterAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	/**
	 * シリアルID
	 *
	 * @author 堅田 一成
	 * @since 1.0
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1524555947613444860L;

	/**
	 * セッション情報
	 *
	 * @author 堅田 一成
	 * @since 1.0
	 * @version 1.0
	 */
	private Map<String, Object> session;

	/**
	 * レスポンス
	 *
	 * @author 堅田 一成
	 * @since 1.0
	 * @version 1.0
	 */
	private HttpServletResponse response;

	/**
	 * リクエスト
	 *
	 * @author 堅田 一成
	 * @since 1.0
	 * @version 1.0
	 */
	private HttpServletRequest request;

	/**
	 * TwitterリクエストToken取得メソッド
	 *
	 * @author 堅田 一成
	 * @since 1.0
	 * @return SUCCESS ERROR
	 */
	public String execute() {
		TwitterOauth twitterOauth = new TwitterOauth();
		if (!twitterOauth.getRequestToken(request, response)) {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * セッション情報登録メソッド
	 *
	 * @param session
	 *            セッション情報
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * セッション情報取得メソッド
	 *
	 * @return session 情報
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * レスポンス登録メソッド
	 *
	 * @param response
	 *            レスポンス
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * リクエスト登録メソッド
	 *
	 * @param request
	 *            リクエスト
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
