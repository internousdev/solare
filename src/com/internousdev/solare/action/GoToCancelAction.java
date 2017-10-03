package com.internousdev.solare.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * GoToCancelAction ゴートゥーキャンセルアクション
 *
 * @author 松本雄太
 * @since 2015/04/21
 * @version 1.0
 *
 */
public class GoToCancelAction extends ActionSupport {
	/**
	 * 生成されたシリアルナンバー
	 *
	 * @author 松本雄太
	 * @since
	 * @version 1.0
	 */
	private static final long serialVersionUID = 5151550203682398891L;

	/**
	 * 実行メソッド
	 *
	 * @author 松本雄太
	 * @since 2015/04/21
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
