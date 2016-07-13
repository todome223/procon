package controller;

import factory.ValueListFactory;
import model.MySituation;
import model.MyValue;
import model.ValueList;

public class GameManager {
	/**
	 * 値一覧リスト
	 */
	ValueList m_valueList;
	/**
	 * 回答リスト
	 */
	ValueList m_anserList;
	/**
	 * 回答確認用リスト
	 */
	ValueList m_anserTempList;
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
	int m_anserNumber;
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
		this.m_anserList = new ValueList();
		this.m_anserTempList = new ValueList();
	}
	
	/**
	 * 初期化
	 */
	public void initialize()
	{
		// 値一覧リストの取得
		this.m_valueList = ValueListFactory.getInstance().create();
		// 回答数を引数にゲーム状況を初期化
		this.m_situation = new MySituation( anserNum() );
		this.m_beforeSituation = new MySituation( anserNum() );
		// 値一覧リストから回答リストを作成
		createFirstAnserList();
		// 確認対象添え字番号を0番目に
		m_checkNumber = 0;
		
		m_anserNumber = 0;
		
		m_changeMode = 1;
	}
	
	/**
	 * 初回回答リストの作成
	 * 初期値は添え字0から必要回答数までのサイズ
	 */
	private void createFirstAnserList()
	{
		for ( int i = 0; i < anserNum(); ++i )
		{
			this.m_anserList.add( this.m_valueList.useValue(i) );
		}
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
	 * 
	 */
	public void changeList()
	{
		MyValue top = this.m_valueList.front();
		this.m_valueList.changeTop();
		while( top.isUnavailable() )
		{
			top = this.m_valueList.front();
			this.m_valueList.changeTop();
		}
	}
	
	/**
	 * 回答リスト取得 
	 * @return m_anserList 回答リスト
	 */
	public ValueList getAnserList()
	{
		return this.m_anserList;
	}
	
	public String printAnserList()
	{
		return this.m_anserList.toString();
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
	 * 確認対象添え字番号移動
	 */
	public void checkNextSuffix()
	{
		++m_checkNumber;
	}

	/**
	 * 入力と同様の値を持つリストの要素を使用禁止に変更
	 * @param i_value 入力値
	 */
	public void changeUnavailableFromValue( MyValue i_value )
	{
		m_valueList.changeUnavailableFromValue(i_value);
	}
	
	public void changeNumber()
	{
		// 確認番号がすでに確認リストの最後尾に至っているか
		switch( this.m_changeMode )
		{
		case 1:
			// 未満なら、回答リストに存在しない値と交換
			this.m_anserTempList = this.m_anserList.clone();
			while (this.m_anserList.hasValue(this.m_valueList.get(this.m_checkNumber)))
			{
				++this.m_checkNumber;
			}
			this.m_anserList.change(this.m_anserNumber, this.m_valueList.get(this.m_checkNumber));
			++this.m_checkNumber;
			if ( this.m_checkNumber >= this.m_valueList.size() )
			{
				m_changeMode = 2;
				this.m_checkNumber = this.m_anserNumber;
				this.m_anserTempList = this.m_anserList.clone();
			}
			break;
		case 2:
			// 最後尾に至っているなら、回答リスト内で値交換
			this.m_anserList = this.m_anserTempList.clone();
			++this.m_checkNumber;
			MyValue temp = this.m_anserList.get(this.m_anserNumber);
			this.m_anserList.change(this.m_anserNumber, this.m_valueList.get(this.m_checkNumber));
			this.m_anserList.change(this.m_checkNumber, temp);
			break;
		case 3:
			// 回答リスト内の値交換時に、HIT数の増加があった場合、使用禁止値と交換
			this.m_anserTempList = this.m_anserList.clone();
			this.m_anserTempList.change(this.m_anserNumber, this.getUnavailable());
			break;
		}
	}
	
	private MyValue getUnavailable()
	{
		for ( int i = 0; i < this.m_valueList.size(); ++i )
		{
			if( this.m_valueList.get(i).isUnavailable() )
			{
				return this.m_valueList.get(i);
			}
		}
		return this.m_valueList.front();
	}
	
	public boolean situationIsChanged()
	{
		if ( !this.m_beforeSituation.equals( this.m_situation ) )
		{
			return true;
		}
		return false;
	}
	
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
	
	public void hitPlus()
	{
		// ヒット数増加時
		++this.m_anserNumber;
		this.m_checkNumber = 0;
		this.m_changeMode = 1;
	}
	
	public void hitMinus()
	{
		// ヒット数減少時
		this.m_anserList = this.m_anserTempList.clone();
		++this.m_anserNumber;
		this.m_checkNumber = 0;
		this.m_changeMode = 1;
	}
	
	/**
	 * プログラミングコンテスト用回答数
	 * @return 6 回答数
	 */
	private final int anserNum()
	{
		return 6;
	}
}
