/**
 *
 */
package com.internousdev.solare.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 予約内容確認画面へ遷移するためのクラス
 *
 * @author 牧野秀徳
 * @since 2016/04/18
 */
public class GoToPaymentInformationPageAction extends ActionSupport implements SessionAware {
	/**
	 * 生成されたシリアルナンバー
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @version 1.0
	 */
	private static final long serialVersionUID = -1373652116839199858L;

	/**
	 * 予約日
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @version 1.0
	 */
	private String calendar;

	/**
	 * コース番号
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @version 1.0
	 */
	private int courseNumber;

	/**
	 * 予約時間
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @version 1.0
	 */
	private String reservationTime;

	/**
	 * 予約人数
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @version 1.0
	 */
	private int numberOfPeople;

	/**
	 * コース(String)
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @version 1.0
	 */
	private String courseString;

	/**
	 * セッション情報
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @version 1.0
	 */
	private Map<String, Object> sessionMap;

	/**
	 * ユーザー遷移先リクエスト
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @version 1.0
	 */
	private String userRequest;

	/**
	 * 実行メソッド
	 *
	 * @author 牧野秀徳
	 * @since 2016/04/18
	 * @return result 結果
	 */

	public String execute() {

		/*
		 * セッションに予約コース番号を保存する
		 */
		sessionMap.put("CourseNumber", courseNumber);

		/*
		 * セッションに予約時間を保存する
		 */
		sessionMap.put("ReservationTime", reservationTime);

		/*
		 * セッションに予約人数を保存する
		 */
		sessionMap.put("NumberOfPeople", numberOfPeople);

		/*
		 * セッションに予約コースを保存する
		 */
		sessionMap.put("CourseString", courseString);

		/*
		 * セッションに予約日付を保存する
		 */
		sessionMap.put("Calendar", calendar);

		String result = SUCCESS;
		return result;
	}

	/**
	 * 予約日取得メソッド
	 *
	 * @return calendar 予約日
	 */
	public String getcalendar() {
		return calendar;
	}

	/**
	 * 予約日登録メソッド
	 *
	 * @return calendar 予約日
	 */
	public void setcalendar(String calendar) {
		this.calendar = calendar;
	}

	/**
	 * 予約コース番号取得メソッド
	 *
	 * @return courseNumber コース番号
	 */
	public int getCourseNumber() {
		return courseNumber;
	}

	/**
	 * 予約コース番号登録メソッド
	 *
	 * @return courseNumber コース番号
	 */
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	/**
	 * 予約時間取得メソッド
	 *
	 * @return reservationTime 予約時間
	 */
	public String getReservationTime() {
		return reservationTime;
	}

	/**
	 * 予約時間登録メソッド
	 *
	 * @return reservationTime 予約時間
	 */
	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}

	/**
	 * 予約人数取得メソッド
	 *
	 * @return numberOfPeople 予約人数
	 */
	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * 予約人数登録メソッド
	 *
	 * @return numberOfPeople 予約人数
	 */
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	/**
	 * コース取得メソッド
	 *
	 * @return courseString コース
	 */
	public String getCourseString() {
		return courseString;
	}

	/**
	 * コース登録メソッド
	 *
	 * @return courseString コース
	 */
	public void setCourseString(String courseString) {
		this.courseString = courseString;
	}

	/**
	 * セッション情報取得メソッド
	 *
	 * @param sessionMap
	 *            セッション情報
	 */
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	/**
	 * ユーザー遷移先リクエスト情報取得メソッド
	 *
	 * @return userRequest ユーザー遷移先リクエスト情報
	 */
	public String getUserRequest() {
		return userRequest;
	}

	/**
	 * ユーザー遷移先リクエスト情報登録メソッド
	 *
	 * @return userRequest ユーザー遷移先リクエスト情報
	 */
	public void setUserRequest(String userRequest) {
		this.userRequest = userRequest;
	}

	/**
	 * セッション情報登録メソッド
	 *
	 * @param sessionMap
	 *            セッション情報
	 */
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;

	}

}
