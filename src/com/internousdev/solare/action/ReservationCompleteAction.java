package com.internousdev.solare.action;

import java.net.SocketException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.solare.dao.ReservationCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ReservationCompleteAction
 *既に同一日時に予約がされているか確認、予約件数をチェックするアクション
 * @author 林美希
 * @since 2015/04/15
 * @version 1.0
 */
public class ReservationCompleteAction extends ActionSupport implements SessionAware {

	/**
	 * 生成されたシリアルナンバー
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private static final long serialVersionUID = -4497600680372642661L;

	/**
	 *
	 */

	/**
	 * セッション情報
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private Map<String, Object> sessionMap;

	/**
	 * メッセージ
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String message;

	/**
	 * 来店日時
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String reservationDate;

	/**
	 * コース番号
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private int courseNumber;

	/**
	 * 予約時間
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String reservationTime;

	/**
	 * 予約人数
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private int numberOfPeople;

	/**
	 * コース(String)
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String courseString;

	/**
	 * クレジットカードの下4ケタ
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String card4;

	/**
	 * クレジットカードトークン
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String token;

	/**
	 * クレジットカード種別
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String card;

	/**
	 * クレジットカード番号
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String cardNumber;

	/**
	 * 有効期限(月)
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String cardMonth;

	/**
	 * 有効期限(年)
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String cardYear;

	/**
	 * 有効期限(月/年)
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String expirationDate = cardMonth + "/" + cardYear;

	/**
	 * クレジットカード名義
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String cardHolder;

	/**
	 * セキュリティコード
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private String securityCode;

	/**
	 * 一時テーブルのID
	 *
	 * @author 林美希
	 * @since 2015/04/15
	 * @version 1.0
	 */
	private int tempId;

	/**
	 * 実行メソッド
	 *
	 * @return result 結果
	 */
	public String execute() throws SocketException {
		card = (String) sessionMap.get("Card");
		cardNumber = (String) sessionMap.get("CardNumber");
		cardMonth = (String) sessionMap.get("CardMonth");
		cardYear = (String) sessionMap.get("CardYear");
		cardHolder = (String) sessionMap.get("CardHolder");
		securityCode = (String) sessionMap.get("SecurityCode");
		tempId = (int) sessionMap.get("TempId");

		String result = ERROR;
		String reservationDateTimeStamp;
		int reservationTimeInt = 0;
		switch (courseNumber) {
		case 0:
			reservationTimeInt = 60;
			break;
		case 1:
			reservationTimeInt = 60;
			break;
		case 2:
			reservationTimeInt = 120;
			break;
		default:
			break;
		}
		switch (courseNumber) {
		case 0:
			this.courseString = "A";
			break;
		case 1:
			this.courseString = "B";
			break;
		case 2:
			this.courseString = "C";
			break;
		default:
			break;
		}
		reservationDateTimeStamp = reservationDate + " " + reservationTime;
		ReservationCompleteDAO dao = new ReservationCompleteDAO();

		boolean insertCheck = true;
		HashMap<String, String> userInfoMap = new HashMap<String, String>();
		try {
			int reservationNumber = dao.checkReservationCount((int) sessionMap.get("accountNumber"));
			if (reservationNumber == 3) {
				message = "ご予約は３件までとなっております。";
				return result;
			} else if (reservationNumber < 3) {
				reservationNumber++;
			}
			if (dao.isCheckUserReservation((int) sessionMap.get("accountNumber"), reservationDateTimeStamp)) {
				userInfoMap = dao.getUserInfo((int) sessionMap.get("accountNumber"));
				insertCheck = dao.isInsertReservation((int) sessionMap.get("accountNumber"), reservationNumber,
						courseNumber, numberOfPeople, userInfoMap.get("fullName"), userInfoMap.get("userTel"),
						reservationDate, reservationDateTimeStamp, reservationTimeInt);
			} else {
				message = "既に同一日時に既にご予約を頂いております。";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		if (insertCheck) {
			result = SUCCESS;
		} else {
			result = ERROR;
		}
		return result;
	}

	/**
	 * セッション情報登録メソッド
	 *
	 * @return session セッション情報
	 */
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}
	
	/**
	 * メッセージ取得メソッド
	 *
	 * @return message メッセージ
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 予約日取得メソッド
	 *
	 * @return reservationDate 予約日
	 */
	public String getReservationDate() {
		return reservationDate;
	}
	
	/**
	 * 予約日登録メソッド
	 *
	 * @return reservationDate 予約日
	 */
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
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
	 * コース（String）取得メソッド
	 *
	 * @return courseString コース（String）
	 */
	public String getCourseString() {
		return courseString;
	}

	/**
	 * コース（String）登録メソッド
	 *
	 * @return courseString コース（String）
	 */
	public void setCourseString(String courseString) {
		this.courseString = courseString;
	}

	/**
	 * コース番号取得メソッド
	 *
	 * @return courseNumber コース番号
	 */
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	/**
	 * コース番号登録メソッド
	 *
	 * @return courseNumber コース番号
	 */
	public int getCourseNumber() {
		return courseNumber;
	}

	/**
	 * セッション情報取得メソッド
	 *
	 * @return sessionMap セッション情報
	 */
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	/**
	 * セッション情報登録メソッド
	 *
	 * @return sessionMap セッション情報
	 */
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	/**
	 * クレジットカードの下4ケタ取得メソッド
	 *
	 * @return card4 クレジットカードの下4ケタ
	 */
	public String getCard4() {
		return card4;
	}

	/**
	 * クレジットカードの下4ケタ登録メソッド
	 *
	 * @param card4
	 *            クレジットカードの下4ケタ
	 */
	public void setCard4(String card4) {
		this.card4 = card4;
	}

	/**
	 * クレジットカードトークン取得メソッド
	 *
	 * @return token クレジットカードトークン
	 */
	public String getToken() {
		return token;
	}

	/**
	 * クレジットカードトークン登録メソッド
	 *
	 * @param token
	 *            クレジットカードトークン
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * クレジットカード種別取得メソッド
	 *
	 * @return card クレジットカード種別
	 *
	 */
	public String getCard() {
		return card;
	}

	/**
	 * クレジットカード種別登録メソッド
	 *
	 * @param card
	 *            クレジットカード種別
	 */
	public void setCard(String card) {
		this.card = card;
	}

	/**
	 * クレジットカード種別取得メソッド
	 *
	 * @return card クレジットカード種別
	 *
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * クレジットカード種別登録メソッド
	 *
	 * @param card
	 *            クレジットカード種別
	 *
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * 有効期限（月）取得メソッド
	 *
	 * @return cardMonth 有効期限（月）
	 *
	 */
	public String getCardMonth() {
		return cardMonth;
	}

	/**
	 * 有効期限（月）登録メソッド
	 *
	 * @param cardMonth
	 *            有効期限（月）
	 *
	 */
	public void setCardMonth(String cardMonth) {
		this.cardMonth = cardMonth;
	}

	/**
	 * 有効期限（年）取得メソッド
	 *
	 * @return cardYear 有効期限（年）
	 *
	 */
	public String getCardYear() {
		return cardYear;
	}

	/**
	 * 有効期限（年）登録メソッド
	 *
	 * @param cardYear
	 *            有効期限（年）
	 *
	 */
	public void setCardYear(String cardYear) {
		this.cardYear = cardYear;
	}

	/**
	 * 有効期限(月/年)取得メソッド
	 *
	 * @return expirationDate 有効期限(月/年)
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * 有効期限(月/年)登録メソッド
	 *
	 * @param expirationDate
	 *            有効期限(月/年)
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * クレジットカード名義取得メソッド
	 *
	 * @return cardHolder クレジットカード名義
	 */
	public String getCardHolder() {
		return cardHolder;
	}

	/**
	 * クレジットカード名義登録メソッド
	 *
	 * @param cardHolder
	 *            クレジットカード名義
	 */
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	/**
	 * セキュリティコード取得メソッド
	 *
	 * @return securityCode セキュリティコード
	 */
	public String getSecurityCode() {
		return securityCode;
	}

	/**
	 * セキュリティコード登録メソッド
	 *
	 * @param securityCode
	 *            セキュリティコード
	 */
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	/**
	 * 一時テーブルのID取得メソッド
	 *
	 * @return tempId 一時テーブルのID
	 */
	public int getTempId() {
		return tempId;
	}

	/**
	 * 一時テーブルのID登録メソッド
	 *
	 * @param tempId
	 *            一時テーブルのID
	 */
	public void setTempId(int tempId) {
		this.tempId = tempId;
	}

	/**
	 * シリアルナンバー取得メソッド
	 *
	 * @return serialVersionUID シリアルナンバー
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * メッセージ登録メソッド
	 *
	 * @param message
	 *            メッセージ
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}