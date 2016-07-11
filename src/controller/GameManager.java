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
	 * ゲーム状況
	 */
	MySituation m_situation;
	/**
	 * 確認対象添え字番号
	 */
	int m_checkNumber;
	
	/**
	 * コンストラクタ
	 */
	public GameManager()
	{
		this.m_valueList = new ValueList();
		this.m_anserList = new ValueList();
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
		// 値一覧リストから回答リストを作成
		createFirstAnserList();
		// 確認対象添え字番号を0番目に
		m_checkNumber = 0;
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
	 * プログラミングコンテスト用回答数
	 * @return 6 回答数
	 */
	private final int anserNum()
	{
		return 6;
	}
}
