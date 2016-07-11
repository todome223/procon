package model;

/**
 * 値クラス
 * @author ichimura
 */
public class MyValue
{
	/**
	 * 値情報
	 */
	private String m_value;
	/**
	 * 使用中有無
	 */
	private boolean m_used;
	/**
	 * 使用禁止有無
	 */
	private boolean m_unavailable;
	
	/**
	 * コンストラクタ
	 */
	public MyValue( String i_value )
	{
		this.m_value = i_value;
		this.m_used = false;
		this.m_unavailable = false;
	}
	
	/**
	 * 使用中
	 */
	public void used()
	{
		this.m_used = true;
	}
	/**
	 * 非使用中
	 */
	public void notUsed()
	{
		this.m_used = false;
	}
	/**
	 * 使用禁止
	 */
	public void unavailable()
	{
		this.m_unavailable = true;
	}
	
	/**
	 * 値取得
	 */
	public String getValue()
	{
		return this.m_value;
	}
	/**
	 * 使用有無確認
	 */
	public boolean isUsed()
	{
		return this.m_used;
	}
	/**
	 * 使用禁止有無確認
	 */
	public boolean isUnavailable()
	{
		return this.m_unavailable;
	}
	
	public boolean equals( MyValue i_value )
	{
		if ( this.m_value != i_value.m_value )
		{
			return false;
		}
		return true;
	}
}
