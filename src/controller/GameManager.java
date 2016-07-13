package controller;

import factory.ValueListFactory;
import model.MySituation;
import model.MyValue;
import model.ValueList;

public class GameManager {
	/**
	 * �l�ꗗ���X�g
	 */
	ValueList m_valueList;
	/**
	 * �񓚃��X�g
	 */
	ValueList m_anserList;
	/**
	 * �񓚊m�F�p���X�g
	 */
	ValueList m_anserTempList;
	/**
	 * �Q�[����
	 */
	MySituation m_situation;
	/**
	 * �O��Q�[����
	 */
	MySituation m_beforeSituation;
	/**
	 * �m�F�ΏۓY�����ԍ�
	 */
	int m_checkNumber;
	/**
	 * �񓚃��X�g�ύX�Ώ۔ԍ�
	 */
	int m_anserNumber;
	/**
	 * �������@
	 */
	int m_changeMode;
	
	/**
	 * �R���X�g���N�^
	 */
	public GameManager()
	{
		this.m_valueList = new ValueList();
		this.m_anserList = new ValueList();
		this.m_anserTempList = new ValueList();
	}
	
	/**
	 * ������
	 */
	public void initialize()
	{
		// �l�ꗗ���X�g�̎擾
		this.m_valueList = ValueListFactory.getInstance().create();
		// �񓚐��������ɃQ�[���󋵂�������
		this.m_situation = new MySituation( anserNum() );
		this.m_beforeSituation = new MySituation( anserNum() );
		// �l�ꗗ���X�g����񓚃��X�g���쐬
		createFirstAnserList();
		// �m�F�ΏۓY�����ԍ���0�Ԗڂ�
		m_checkNumber = 0;
		
		m_anserNumber = 0;
		
		m_changeMode = 1;
	}
	
	/**
	 * ����񓚃��X�g�̍쐬
	 * �����l�͓Y����0����K�v�񓚐��܂ł̃T�C�Y
	 */
	private void createFirstAnserList()
	{
		for ( int i = 0; i < anserNum(); ++i )
		{
			this.m_anserList.add( this.m_valueList.useValue(i) );
		}
	}
	
	/**
	 * �Q�[���󋵍X�V
	 * @param i_inputList ����
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
	 * �񓚃��X�g�擾 
	 * @return m_anserList �񓚃��X�g
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
	 * �Q�[���󋵎擾
	 * @return m_situation �Q�[����
	 */
	public MySituation getSituation()
	{
		return this.m_situation;
	}
	
	/**
	 * �����m�F
	 * @return �����m�F����
	 */
	public boolean isWin()
	{
		return this.m_situation.isWin();
	}
	
	/**
	 * �m�F�ΏۓY�����ԍ��ړ�
	 */
	public void checkNextSuffix()
	{
		++m_checkNumber;
	}

	/**
	 * ���͂Ɠ��l�̒l�������X�g�̗v�f���g�p�֎~�ɕύX
	 * @param i_value ���͒l
	 */
	public void changeUnavailableFromValue( MyValue i_value )
	{
		m_valueList.changeUnavailableFromValue(i_value);
	}
	
	public void changeNumber()
	{
		// �m�F�ԍ������łɊm�F���X�g�̍Ō���Ɏ����Ă��邩
		switch( this.m_changeMode )
		{
		case 1:
			// �����Ȃ�A�񓚃��X�g�ɑ��݂��Ȃ��l�ƌ���
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
			// �Ō���Ɏ����Ă���Ȃ�A�񓚃��X�g���Œl����
			this.m_anserList = this.m_anserTempList.clone();
			++this.m_checkNumber;
			MyValue temp = this.m_anserList.get(this.m_anserNumber);
			this.m_anserList.change(this.m_anserNumber, this.m_valueList.get(this.m_checkNumber));
			this.m_anserList.change(this.m_checkNumber, temp);
			break;
		case 3:
			// �񓚃��X�g���̒l�������ɁAHIT���̑������������ꍇ�A�g�p�֎~�l�ƌ���
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
			// �O���葝������
			return 1;
		}
		else if ( this.m_beforeSituation.equalsHit( this.m_situation ) > 0 )
		{
			// �O���茸������
			return -1;
		}
		return 0;
	}
	
	public int blowIsChanged()
	{
		if ( this.m_beforeSituation.equalsBlow( this.m_situation ) < 0 )
		{
			// �O���葝������
			return 1;
		}
		else if ( this.m_beforeSituation.equalsBlow( this.m_situation ) > 0 )
		{
			// �O���茸������
			return -1;
		}
		return 0;
	}
	
	public void hitPlus()
	{
		// �q�b�g��������
		++this.m_anserNumber;
		this.m_checkNumber = 0;
		this.m_changeMode = 1;
	}
	
	public void hitMinus()
	{
		// �q�b�g��������
		this.m_anserList = this.m_anserTempList.clone();
		++this.m_anserNumber;
		this.m_checkNumber = 0;
		this.m_changeMode = 1;
	}
	
	/**
	 * �v���O���~���O�R���e�X�g�p�񓚐�
	 * @return 6 �񓚐�
	 */
	private final int anserNum()
	{
		return 6;
	}
}
