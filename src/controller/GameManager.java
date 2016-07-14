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
	 * �񓚊m�F�p���X�g1
	 */
	ValueList m_anserTempList;
	/**
	 * �񓚊m�F�p���X�g2
	 */
	ValueList m_anserTempList2;
	/**
	 * �m�F�ς݃��X�g
	 */
	ValueList m_checkedList;
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
		this.m_checkedList = new ValueList();
	}
	
	/**
	 * ������
	 */
	public void initialize()
	{
		// �l�ꗗ���X�g�̎擾
		this.m_valueList = ValueListFactory.getInstance().create();
		// �񓚃��X�g�̎擾
		this.m_anserList = ValueListFactory.getInstance().createFirstAnserList();
		// �񓚐��������ɃQ�[���󋵂�������
		this.m_situation = new MySituation( anserNum() );
		this.m_beforeSituation = new MySituation( anserNum() );
		// �m�F�ΏۓY�����ԍ���0�Ԗڂ�
		m_checkNumber = 0;
		
		m_anserNumber = 0;
		
		m_changeMode = 1;
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
	 * ���͂Ɠ��l�̒l�������X�g�̗v�f���g�p�֎~�ɕύX
	 * @param i_value ���͒l
	 */
	public void changeUnavailableFromValue( MyValue i_value )
	{
		m_valueList.changeUnavailableFromValue(i_value);
	}
	
	/**
	 * �񓚃��X�g�X�V
	 */
	public void changeNumber()
	{	
		// �m�F�ԍ������łɊm�F���X�g�̍Ō���Ɏ����Ă��邩
		switch( this.m_changeMode )
		{
		case 1:
			// �����Ȃ�A�񓚃��X�g�ɑ��݂��Ȃ��l�ƌ���
			this.m_anserTempList = this.m_anserList.clone();
			// while�J�E���g����m_valueList�̑傫���𒴂��邩�̊m�F�J�E���^
			int l_count = 0;
			while ( this.m_anserList.hasValue(this.m_valueList.get(this.m_checkNumber)) ||
					this.m_valueList.get(this.m_checkNumber).isUnavailable() )
			{
				++l_count;
				++this.m_checkNumber;
				if ( this.m_checkNumber >= this.m_valueList.size() ){
					this.m_checkNumber = 0;
				}
			}
			MyValue l_temp = this.m_valueList.get(this.m_checkNumber);
			if ( this.m_checkedList.hasValue( l_temp ) )
			{
				m_changeMode = 2;
				this.m_checkNumber = this.m_anserNumber;
				this.m_anserTempList = this.m_anserList.clone();
			}
			else
			{
				this.m_anserList.change(this.m_anserNumber, l_temp);
				this.m_checkedList.add( l_temp );
			}
			break;
		case 2:
			// �Ō���Ɏ����Ă���Ȃ�A�񓚃��X�g���Œl����
			this.m_anserList = this.m_anserTempList.clone();
			++this.m_checkNumber;
			MyValue temp = this.m_anserList.get(this.m_anserNumber);
			this.m_anserList.change(this.m_anserNumber, this.m_anserTempList.clone().get(this.m_checkNumber));
			this.m_anserList.change(this.m_checkNumber, temp);
			break;
		case 3:
			// �񓚃��X�g���̒l�������ɁAHIT���̑������������ꍇ�A�g�p�֎~�l�ƌ���
			this.m_anserTempList2 = this.m_anserList.clone();
			this.m_anserTempList2.change(this.m_anserNumber, this.getUnavailable());
			break;
		}
	}
	
	/**
	 * �g�p�֎~�l�擾
	 * @return �g�p�֎~�l
	 */
	private MyValue getUnavailable()
	{
		for ( int i = 0; i < this.m_valueList.size(); ++i )
		{
			if( this.m_valueList.get(i).isUnavailable() )
			{
				return this.m_valueList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * �Q�[���󋵕ω��L���m�F
	 * @return true �ω�����
	 * @return false �ω��Ȃ�
	 */
	public boolean situationIsChanged()
	{
		if ( !this.m_beforeSituation.equals( this.m_situation ) )
		{
			return true;
		}
		return false;
	}

	/**
	 * �q�b�g�������m�F
	 * @return 1 �q�b�g�����O���葝��
	 * @return -1 �q�b�g�����O���茸��
	 * @return 0 �ω��Ȃ�
	 */
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
	
	/**
	 * �u���E�������m�F
	 * @return 1 �u���E�����O���葝��
	 * @return -1 �u���E�����O���茸��
	 * @return 0 �ω��Ȃ�
	 */
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
	
	/**
	 * �q�b�g���������̏���
	 */
	public void hitPlus()
	{
		++this.m_anserNumber;
		this.m_checkNumber = 0;
		this.m_changeMode = 1;
		this.m_valueList.refresh();
		this.m_checkedList.clear();
	}
	
	/**
	 * �q�b�g���������̏���
	 */
	public void hitMinus()
	{
		this.m_anserList = this.m_anserTempList.clone();
		++this.m_anserNumber;
		this.m_checkNumber = 0;
		this.m_changeMode = 1;
		this.m_situation = this.m_beforeSituation.clone();
		this.m_valueList.refresh();
		this.m_checkedList.clear();
	}
	
	/**
	 * �u���E���������̏���
	 */
	public void blowPlus()
	{
	}
	
	/**
	 * �u���E���������̏���
	 */
	public void blowMinus()
	{
		MyValue l_check = this.m_anserList.get(this.m_anserNumber);
		this.m_valueList.changeUnavailableFromValue(l_check);
		this.m_situation = this.m_beforeSituation.clone();
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
