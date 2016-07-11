package model;

import java.util.ArrayList;

/**
 * �l���X�g�N���X
 * @author ichimura
 */
public class ValueList
{
	/**
	 * �l���X�g
	 */
	private ArrayList<MyValue> m_list;
	
	/**
	 * �R���X�g���N�^
	 */
	public ValueList()
	{
		this.m_list = new ArrayList<MyValue>();
	}

	/**
	 * �����Ԗڂ̒l���g�p����
	 * @param i_num ����
	 */
	public MyValue useValue( int i_num )
	{
		m_list.get(i_num).used();
		return m_list.get(i_num);
	}
	
	/**
	 * �l�ǉ�
	 * @param i_value �l
	 */
	public void add( MyValue i_value )
	{
		this.m_list.add(i_value);
	}
	
	/**
	 * �T�C�Y�m�F
	 */
	public int size()
	{
		return this.m_list.size();
	}
	
	/**
	 * �l�擾
	 * @param i_num �Y�����ԍ�
	 * @return �l
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
