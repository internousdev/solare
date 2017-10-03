package com.internousdev.solare.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * GoToLeaveAction 退会するためのクラス
 *
 * @author 岡部吉男
 * @since 2015/04/14
 * @version 1.0
 */
public class GoToLeaveAction extends ActionSupport {

	/**
	 * 生成されたシリアルナンバー
	 *
	 * @author 岡部吉男
	 * @since 2015/04/14
	 * @version 1.0
	 */
	private static final long serialVersionUID = 8003612314492950312L;

	/**
	 * 実行メソッド
	 *
	 * @author 岡部吉男
	 * @since 2015/04/14
	 * @return result 結果
	 */
	public String execute() {
		String result = ERROR;
		try {
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
