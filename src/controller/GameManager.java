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
	 * �Q�[����
	 */
	MySituation m_situation;
	/**
	 * �m�F�ΏۓY�����ԍ�
	 */
	int m_checkNumber;
	
	/**
	 * �R���X�g���N�^
	 */
	public GameManager()
	{
		this.m_valueList = new ValueList();
		this.m_anserList = new ValueList();
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
		// �l�ꗗ���X�g����񓚃��X�g���쐬
		createFirstAnserList();
		// �m�F�ΏۓY�����ԍ���0�Ԗڂ�
		m_checkNumber = 0;
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
	 * �v���O���~���O�R���e�X�g�p�񓚐�
	 * @return 6 �񓚐�
	 */
	private final int anserNum()
	{
		return 6;
	}
}
