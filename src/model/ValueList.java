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
	 * 入力と同様の値を持つリストの要素を使用禁止に変更
	 * @param i_value 入力値
	 */
	public void changeUnavailableFromValue( MyValue i_value )
	{
		for ( int i = 0; i < this.m_list.size(); ++i )
		{
			if ( i_value.equals(this.m_list.get(i)) )
			{
				this.m_list.get(i).unavailable();
				break;
			}
		}
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
	
	public void change( int i_index, MyValue i_value )
	{
		this.m_list.set(i_index, i_value);
	}
	
	public MyValue get( int i_index )
	{
		if ( this.m_list.size() > i_index )
		{
			return this.m_list.get(i_index);
		}
		return null;
	}
	
	public boolean hasValue( MyValue i_value )
	{
		for ( int i = 0; i < this.m_list.size(); ++i )
		{
			if ( i_value.equals( this.m_list.get(i) ) )
			{
				return true;
			}
		}
		return false;
	}
	
	public String toString()
	{
		String l_rString = new String("");
		for( int i = 0; i < this.m_list.size(); ++i )
		{
			l_rString += this.m_list.get(i).toString();
		}
		return l_rString;
	}
	
	public ValueList clone()
	{
		ValueList l_rList = new ValueList();
		for ( int i = 0; i < this.m_list.size(); ++i )
		{
			l_rList.add( this.m_list.get(i).clone() );
		}
		return l_rList;
	}
}
