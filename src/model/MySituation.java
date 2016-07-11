package model;

/**
 * ゲーム状況クラス
 * @author ichimura
 */
public class MySituation {
	/**
	 * ヒット数
	 */
	int m_hit;
	/**
	 * ブロウ数
	 */
	int m_blow;
	/**
	 * ノーヒット数
	 */
	int m_noHit;
	/**
	 * 値総種
	 */
	int m_size;
	
	/**
	 * コンストラクタ
	 * @param i_size 値総種
	 */
	public MySituation( int i_size )
	{
		this.m_size = i_size;
		this.m_hit = 0;
		this.m_blow = 0;
		this.m_noHit = this.m_size;
	}
	
	/**
	 * 同値確認
	 * @param i_situation 確認先インスタンス
	 * @return check 確認結果
	 */
	public boolean equals( MySituation i_situation )
	{
		boolean check = true;
		if ( !this.equalsHit(i_situation) )
		{
			check = false;
		}
		else if ( !this.equalsBlow(i_situation) )
		{
			check = false;
		}
		else if ( this.m_noHit != i_situation.m_noHit )
		{
			check = false;
		}
		else if ( this.m_size != i_situation.m_size )
		{
			check = false;
		}
		return check;
	}
	
	/**
	 * ヒット数同値確認
	 * @param i_situation 確認先インスタンス
	 * @return check 確認結果
	 */
	public boolean equalsHit( MySituation i_situation ){
		boolean check = true;
		if ( this.m_hit != i_situation.m_hit )
		{
			check = false;
		}
		return check;
	}
	
	/**
	 * ブロウ数同値確認
	 * @param i_situation 確認先インスタンス
	 * @return check 確認結果
	 */
	public boolean equalsBlow( MySituation i_situation ){
		boolean check = true;
		if ( this.m_blow != i_situation.m_blow )
		{
			check = false;
		}
		return check;
	}
	
	/**
	 * 勝利確認
	 * @return 勝利確認結果
	 */
	public boolean isWin()
	{
		if ( this.m_hit == this.m_size )
		{
			return true;
		}
		return false;
	}
	
	/**
	 * ゲーム状況変化に伴うメンバの更新
	 * @param i_situation ゲーム状況
	 */
	public void changeSituation( String i_situation )
	{
		String[] l_result = i_situation.split(" ");
		this.m_hit = Integer.parseInt(l_result[0]);
		this.m_blow = Integer.parseInt(l_result[1]);
		this.m_noHit = this.m_size - ( this.m_hit + this.m_blow );
	}
}
