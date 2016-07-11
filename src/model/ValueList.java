package model;

import java.util.ArrayList;

/**
 * 値リストクラス
 * @author ichimura
 */
public class ValueList
{
	/**
	 * 値リスト
	 */
	private ArrayList<MyValue> m_list;
	
	/**
	 * コンストラクタ
	 */
	public ValueList()
	{
		this.m_list = new ArrayList<MyValue>();
	}

	/**
	 * 引数番目の値を使用する
	 * @param i_num 引数
	 */
	public MyValue useValue( int i_num )
	{
		m_list.get(i_num).used();
		return m_list.get(i_num);
	}
	
	/**
	 * 値追加
	 * @param i_value 値
	 */
	public void add( MyValue i_value )
	{
		this.m_list.add(i_value);
	}
	
	/**
	 * サイズ確認
	 */
	public int size()
	{
		return this.m_list.size();
	}
	
	/**
	 * 値取得
	 * @param i_num 添え字番号
	 * @return 値
	 */
	public MyValue front()
	{
		if ( null != this.m_list.get(0) )
		{
			return this.m_list.get(0);
		}
		return null;
	}
	
	public void changeTop()
	{
		MyValue l_temp = this.m_list.get(0);
		this.m_list.remove(0);
		this.m_list.add(l_temp);
	}
}
