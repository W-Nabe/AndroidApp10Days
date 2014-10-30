package jp.co.se.android.androidbook2.chapter0208;

import android.R.integer;

public class Calculater {
	
	// 入力中の文字列や演算子、計算結果を保持する
	StringBuilder mInputNumber = new StringBuilder(); // 入力中の文字列
	String mOperator; // 入力中の演算子
	int mResult = 0; // 計算結果
	
	
	// パラメータの値が数値ならtrueを返却
	private boolean isNumber(String key) {
		try {
			Integer.parseInt(key);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}
	
	
	// パラメータの値がサポートしている演算子ならtrueを返却
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
	
	
	// 演算を実施
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
	
	// 入力された文字列を元に処理を行い、ディスプレイに表示する結果を返却する
	public String putInput(String key) {
		if(isNumber(key)) {

			// 数値の場合次の入力を待つ
			mInputNumber.append(key);
			return mInputNumber.toString();

		} else if(isSupportedOperator(key)) {

			// ＝なら演算を行い、返却する
			if (key.equals("=")) {
				if (mOperator != null) {
					doCalculation(mOperator);
					mOperator = null;
				}
				return Integer.toString(mResult);
			} else {
				if (mOperator != null) {
					// 入力中の演算子があるなら、前回の結果を用いて演算を行う
					doCalculation(mOperator);
					mOperator = null;
				} else if (mInputNumber.length() > 0) {
					// 初めての演算子なら入力中の数値を設定する
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
