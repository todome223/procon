package model;

/**
 * �Q�[���󋵃N���X
 * @author ichimura
 */
public class MySituation {
	/**
	 * �q�b�g��
	 */
	int m_hit;
	/**
	 * �u���E��
	 */
	int m_blow;
	/**
	 * �m�[�q�b�g��
	 */
	int m_noHit;
	/**
	 * �l����
	 */
	int m_size;
	
	/**
	 * �R���X�g���N�^
	 * @param i_size �l����
	 */
	public MySituation( int i_size )
	{
		this.m_size = i_size;
		this.m_hit = 0;
		this.m_blow = 0;
		this.m_noHit = this.m_size;
	}
	
	/**
	 * ���l�m�F
	 * @param i_situation �m�F��C���X�^���X
	 * @return check �m�F����
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
	 * �q�b�g�����l�m�F
	 * @param i_situation �m�F��C���X�^���X
	 * @return check �m�F����
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
	 * �u���E�����l�m�F
	 * @param i_situation �m�F��C���X�^���X
	 * @return check �m�F����
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
	 * �����m�F
	 * @return �����m�F����
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
	 * �Q�[���󋵕ω��ɔ��������o�̍X�V
	 * @param i_situation �Q�[����
	 */
	public void changeSituation( String i_situation )
	{
		String[] l_result = i_situation.split(" ");
		this.m_hit = Integer.parseInt(l_result[0]);
		this.m_blow = Integer.parseInt(l_result[1]);
		this.m_noHit = this.m_size - ( this.m_hit + this.m_blow );
	}
}
