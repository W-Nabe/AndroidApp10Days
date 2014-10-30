package jp.co.se.android.androidbook2.chapter0208;

import android.R.integer;

public class Calculater {
	
	// 入力中の文字列や演算子、計算結果を表示する
	StringBuilder mInputNumber = new StringBuilder(); // 入力中の文字
	String mOperator; // 演算子
	int mResult = 0; // 計算結果
	
	
	// パラメータのKeyが数値ならtrueを返却する処理
	private boolean isNumber(String key) {
		try {
			Integer.parseInt(key);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}
	
	
	// パラメータのKeyがサポートしている演算子ならtrueを返却する処理
	private boolean isSupportedOperator(String Key) {
		if (Key.equals("+")) {
			return true;
		} else if (Key.equals("-")) {
			return true;
		} else if (Key.equals("*")) {
			return true;
		} else if (Key.equals("/")) {
			return true;
//		} else if (Key.equals("%")) {
//			return true;
		} else if (Key.equals("=")) {
			return true;
		}
		
		return false;
	}
	
	
	// 演算を実施する処理
	private void doCalculation(String ope) {
		if(ope.equals("+")) {
			mResult = mResult + Integer.parseInt(mInputNumber.toString());
		} else if(ope.equals("-")) {
			mResult = mResult - Integer.parseInt(mInputNumber.toString());
		} else if(ope.equals("*")) {
			mResult = mResult * Integer.parseInt(mInputNumber.toString());
		} else if(ope.equals("/")) {
			mResult = mResult / Integer.parseInt(mInputNumber.toString());
		}
		
		mInputNumber = new StringBuilder();
	}
	
	// 入力された文字を元に処理を行い、ディスプレイに結果を返却する処理
	public String putInput(String key) {
		if(isNumber(key)) {

			mInputNumber.append(key);
			return mInputNumber.toString();

		} else if(isSupportedOperator(key)) {

			// =なら演算を行い、結果を表示する
			if (key.equals("=")) {
				if (mOperator != null) {
					doCalculation(mOperator);
					mOperator = null;
				}
				return Integer.toString(mResult);
			} else {
				if (mOperator != null) {
					// 入力中の演算子があるなら、演算を行い、次の入力を待つ
					doCalculation(mOperator);
					mOperator = null;
				} else if (mInputNumber.length() > 0) {
					// 初めての演算子入力なら、入力中の数値を設定する
					mResult = Integer.parseInt(mInputNumber.toString());
					mInputNumber = new StringBuilder();
				}
				mOperator = key;
				return mOperator;
			}

		} else {
			return null;
		}
	}

}
