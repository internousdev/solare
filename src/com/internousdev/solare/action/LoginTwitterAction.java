package com.internousdev.solare.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.solare.dao.LoginOauthDAO;
import com.internousdev.solare.dto.LoginOauthDTO;
import com.internousdev.solare.util.TwitterOauth;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Twitterでログインする為のクラス
 *
 * @author 堅田 一成
 * @since 1.0
 * @version 1.0
 */
public class LoginTwitterAction extends ActionSupport implements ServletResponseAware,ServletRequestAware,SessionAware{

	/**
	 * シリアルバージョンIDの生成
	 *
	 * @author 堅田 一成
	 * @since 1.0
	 * @version 1.0
	 */
	private static final long serialVersionUID = 3787626263699340105L;

	/**
	 * ネットワークネーム
	 *
	 * @author 堅田 一成
	 * @since 1.0
	 * @version 1.0
	 */
	static final String NETWORK_NAME = "Twitter";

	/**
	 * セッション
	 *
	 * @author 堅田 一成
	 * @since 1.0
	 * @version 1.0
	 */
	public Map<String, Object> session;

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
	 * Twitter認証の実行メソッド
	 *
	 * @return String
	 */
	public String execute() {

		String rtn = ERROR;
		TwitterOauth twitterOauth = new TwitterOauth();
		String[] userData = new String[2];
		userData = twitterOauth.getAccessToken(request, response);

		if (userData == null) {
			return rtn;
		}

		String uniqueId = userData[0];
		String userName = userData[1];
		LoginOauthDAO dao = new LoginOauthDAO();
		if (dao.select(uniqueId, NETWORK_NAME)) {
			LoginOauthDTO dto = dao.getLoginOauthDTO();
			session.put("loginId", dto.getUserId());
			session.put("userName", dto.getUserName());
			rtn = SUCCESS;
			return rtn;
		}

		boolean result = dao.insert(uniqueId, userName, NETWORK_NAME);
		if (!result) {
			return rtn;
		}

		dao.select(uniqueId, NETWORK_NAME);
		LoginOauthDTO dto = dao.getLoginOauthDTO();
		session.put("loginId", dto.getUserId());
		session.put("userName", dto.getUserName());
		rtn = SUCCESS;
		return rtn;
	}

	/**
	 * レスポンス格納メソッド
	 *
	 * @param response
	 *            レスポンス
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * リクエスト格納メソッド
	 *
	 * @param request
	 *            リクエスト
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * セッション格納メソッド
	 *
	 * @param session
	 *            セッション
	 */

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * セッション取得メソッド
	 *
	 * @return Session
	 */
	public Map<String, Object> getSession() {
		return session;
	}
}