package factory;

import model.MyValue;
import model.ValueList;

/**
 * �t�@�N�g���N���X�i�l���X�g�N���X�j
 * @author ichimura
 */
public class ValueListFactory {
	/**
	 * �V���O���g���C���X�^���X
	 */
	private static ValueListFactory m_single = new ValueListFactory();
	
	/**
	 * �R���X�g���N�^
	 * �v���C�x�[�g�錾�������I�ɕs�g�p
	 */
	private ValueListFactory()
	{
	}
	
	/**
	 * �I�u�W�F�N�g����
	 * @return
	 */
	public ValueList create()
	{
		return createProgramingContest();
	}
	/**
	 * �v���R���p�I�u�W�F�N�g�쐬
	 * @return l_valueList �v���R���o��͈͓��̒l�̃��X�g
	 */
	private ValueList createProgramingContest()
	{
		ValueList l_valueList = new ValueList();
		l_valueList.add(new MyValue("6"));
		l_valueList.add(new MyValue("7"));
		l_valueList.add(new MyValue("8"));
		l_valueList.add(new MyValue("9"));
		l_valueList.add(new MyValue("A"));
		l_valueList.add(new MyValue("B"));
		l_valueList.add(new MyValue("C"));
		l_valueList.add(new MyValue("D"));
		l_valueList.add(new MyValue("E"));
		l_valueList.add(new MyValue("F"));
		l_valueList.add(new MyValue("0"));
		l_valueList.add(new MyValue("1"));
		l_valueList.add(new MyValue("2"));
		l_valueList.add(new MyValue("3"));
		l_valueList.add(new MyValue("4"));
		l_valueList.add(new MyValue("5"));
		return l_valueList;
	}
	
	public ValueList createFirstAnserList()
	{
		ValueList l_valueList = new ValueList();
		l_valueList.add(new MyValue("0"));
		l_valueList.add(new MyValue("1"));
		l_valueList.add(new MyValue("2"));
		l_valueList.add(new MyValue("3"));
		l_valueList.add(new MyValue("4"));
		l_valueList.add(new MyValue("5"));
		return l_valueList;
	}
	
	/**
	 * �V���O���g���C���X�^���X�擾
	 * @return m_single �V���O���g���C���X�^���X
	 */
	public static ValueListFactory getInstance()
	{
		return m_single;
	}
}
