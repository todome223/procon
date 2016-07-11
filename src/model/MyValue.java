package model;

/**
 * �l�N���X
 * @author ichimura
 */
public class MyValue
{
	/**
	 * �l���
	 */
	private String m_value;
	/**
	 * �g�p���L��
	 */
	private boolean m_used;
	/**
	 * �g�p�֎~�L��
	 */
	private boolean m_unavailable;
	
	/**
	 * �R���X�g���N�^
	 */
	public MyValue( String i_value )
	{
		this.m_value = i_value;
		this.m_used = false;
		this.m_unavailable = false;
	}
	
	/**
	 * �g�p��
	 */
	public void used()
	{
		this.m_used = true;
	}
	/**
	 * ��g�p��
	 */
	public void notUsed()
	{
		this.m_used = false;
	}
	/**
	 * �g�p�֎~
	 */
	public void unavailable()
	{
		this.m_unavailable = true;
	}
	
	/**
	 * �l�擾
	 */
	public String getValue()
	{
		return this.m_value;
	}
	/**
	 * �g�p�L���m�F
	 */
	public boolean isUsed()
	{
		return this.m_used;
	}
	/**
	 * �g�p�֎~�L���m�F
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
