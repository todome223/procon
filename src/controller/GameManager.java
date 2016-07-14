package controller;

import model.MySituation;
import model.MyValue;
import model.ValueList;
import factory.ValueListFactory;

public class GameManager {
	/**
	 * 値一覧リスト
	 */
	ValueList m_valueList;
	/**
	 * 回答リスト
	 */
	ValueList m_answerList;
	/**
	 * 回答確認用リスト1
	 */
	ValueList m_answerTempList;
	/**
	 * 回答確認用リスト2
	 */
	ValueList m_answerTempList2;
	/**
	 * 確認済みリスト
	 */
	ValueList m_checkedList;
	/**
	 * ゲーム状況
	 */
	MySituation m_situation;
	/**
	 * 前回ゲーム状況
	 */
	MySituation m_beforeSituation;
	/**
	 * 確認対象添え字番号
	 */
	int m_checkNumber;
	/**
	 * 回答リスト変更対象番号
	 */
	int m_answerNumber;
	/**
	 * 交換方法
	 */
	int m_changeMode;

	/**
	 * コンストラクタ
	 */
	public GameManager()
	{
		this.m_valueList = new ValueList();
		this.m_answerList = new ValueList();
		this.m_answerTempList = new ValueList();
		this.m_checkedList = new ValueList();
	}

	/**
	 * 初期化
	 */
	public void initialize()
	{
		// 値一覧リストの取得
		this.m_valueList = ValueListFactory.getInstance().create();
		// 回答リストの取得
		this.m_answerList = ValueListFactory.getInstance().createFirstAnswerList();
		// 回答数を引数にゲーム状況を初期化
		this.m_situation = new MySituation( answerNum() );
		this.m_beforeSituation = new MySituation( answerNum() );
		// 確認対象添え字番号を0番目に
		m_checkNumber = 0;

		m_answerNumber = 0;

		m_changeMode = 1;
	}

	/**
	 * ゲーム状況更新
	 * @param i_inputList 入力
	 */
	public void updateGameSituation( String i_inputList )
	{
		this.m_beforeSituation = this.m_situation.clone();
		this.m_situation.changeSituation(i_inputList);
	}

	/**
	 * 回答リスト取得
	 * @return m_answerList 回答リスト
	 */
	public ValueList getAnswerList()
	{
		return this.m_answerList;
	}

	public String printAnswerList()
	{
		return this.m_answerList.toString();
	}

	/**
	 * ゲーム状況取得
	 * @return m_situation ゲーム状況
	 */
	public MySituation getSituation()
	{
		return this.m_situation;
	}

	/**
	 * 勝利確認
	 * @return 勝利確認結果
	 */
	public boolean isWin()
	{
		return this.m_situation.isWin();
	}

	/**
	 * 入力と同様の値を持つリストの要素を使用禁止に変更
	 * @param i_value 入力値
	 */
	public void changeUnavailableFromValue( MyValue i_value )
	{
		m_valueList.changeUnavailableFromValue(i_value);
	}

	/**
	 * 回答リスト更新
	 */
	public void changeNumber()
	{
		// 確認番号がすでに確認リストの最後尾に至っているか
		switch( this.m_changeMode )
		{
		case 1:
			// 未満なら、回答リストに存在しない値と交換
			this.m_answerTempList = this.m_answerList.clone();
			// whileカウント数がm_valueListの大きさを超えるかの確認カウンタ
			int l_count = 0;
			while ( this.m_answerList.hasValue(this.m_valueList.get(this.m_checkNumber)) ||
					this.m_valueList.get(this.m_checkNumber).isUnavailable() )
			{
				++l_count;
				++this.m_checkNumber;
				if ( this.m_checkNumber >= this.m_valueList.size() ){
					this.m_checkNumber = 0;
				}
			}
			MyValue l_temp = this.m_valueList.get(this.m_checkNumber);
			if ( this.m_checkedList.hasValue( l_temp ) )
			{
				m_changeMode = 2;
				this.m_checkNumber = this.m_answerNumber;
				this.m_answerTempList = this.m_answerList.clone();
			}
			else
			{
				this.m_answerList.change(this.m_answerNumber, l_temp);
				this.m_checkedList.add( l_temp );
			}
			break;
		case 2:
			// 最後尾に至っているなら、回答リスト内で値交換
			this.m_answerList = this.m_answerTempList.clone();
			++this.m_checkNumber;
			MyValue temp = this.m_answerList.get(this.m_answerNumber);
			this.m_answerList.change(this.m_answerNumber, this.m_answerTempList.clone().get(this.m_checkNumber));
			this.m_answerList.change(this.m_checkNumber, temp);
			break;
		case 3:
			// 回答リスト内の値交換時に、HIT数の増加があった場合、使用禁止値と交換
			this.m_answerTempList2 = this.m_answerList.clone();
			this.m_answerTempList2.change(this.m_answerNumber, this.getUnavailable());
			break;
		}
	}

	/**
	 * 使用禁止値取得
	 * @return 使用禁止値
	 */
	private MyValue getUnavailable()
	{
		for ( int i = 0; i < this.m_valueList.size(); ++i )
		{
			if( this.m_valueList.get(i).isUnavailable() )
			{
				return this.m_valueList.get(i);
			}
		}
		return null;
	}

	/**
	 * ゲーム状況変化有無確認
	 * @return true 変化あり
	 * @return false 変化なし
	 */
	public boolean situationIsChanged()
	{
		if ( !this.m_beforeSituation.equals( this.m_situation ) )
		{
			return true;
		}
		return false;
	}

	/**
	 * ヒット数増減確認
	 * @return 1 ヒット数が前回より増加
	 * @return -1 ヒット数が前回より減少
	 * @return 0 変化なし
	 */
	public int hitIsChanged()
	{
		if ( this.m_beforeSituation.equalsHit( this.m_situation ) < 0 )
		{
			// 前回より増加した
			return 1;
		}
		else if ( this.m_beforeSituation.equalsHit( this.m_situation ) > 0 )
		{
			// 前回より減少した
			return -1;
		}
		return 0;
	}

	/**
	 * ブロウ数増減確認
	 * @return 1 ブロウ数が前回より増加
	 * @return -1 ブロウ数が前回より減少
	 * @return 0 変化なし
	 */
	public int blowIsChanged()
	{
		if ( this.m_beforeSituation.equalsBlow( this.m_situation ) < 0 )
		{
			// 前回より増加した
			return 1;
		}
		else if ( this.m_beforeSituation.equalsBlow( this.m_situation ) > 0 )
		{
			// 前回より減少した
			return -1;
		}
		return 0;
	}

	/**
	 * ヒット数増加時の処理
	 */
	public void hitPlus()
	{
		++this.m_answerNumber;
		this.m_checkNumber = 0;
		this.m_changeMode = 1;
		this.m_valueList.refresh();
		this.m_checkedList.clear();
	}

	/**
	 * ヒット数減少時の処理
	 */
	public void hitMinus()
	{
		this.m_answerList = this.m_answerTempList.clone();
		++this.m_answerNumber;
		this.m_checkNumber = 0;
		this.m_changeMode = 1;
		this.m_situation = this.m_beforeSituation.clone();
		this.m_valueList.refresh();
		this.m_checkedList.clear();
	}

	/**
	 * ブロウ数増加時の処理
	 */
	public void blowPlus()
	{
	}

	/**
	 * ブロウ数減少時の処理
	 */
	public void blowMinus()
	{
		MyValue l_check = this.m_answerList.get(this.m_answerNumber);
		this.m_valueList.changeUnavailableFromValue(l_check);
		this.m_situation = this.m_beforeSituation.clone();
	}

	/**
	 * プログラミングコンテスト用回答数
	 * @return 6 回答数
	 */
	private final int answerNum()
	{
		return 6;
	}
}
