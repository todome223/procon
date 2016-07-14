package factory;

import model.MyValue;
import model.ValueList;

/**
 * ファクトリクラス（値リストクラス）
 * @author ichimura
 */
public class ValueListFactory {
	/**
	 * シングルトンインスタンス
	 */
	private static ValueListFactory m_single = new ValueListFactory();
	
	/**
	 * コンストラクタ
	 * プライベート宣言し明示的に不使用
	 */
	private ValueListFactory()
	{
	}
	
	/**
	 * オブジェクト生成
	 * @return
	 */
	public ValueList create()
	{
		return createProgramingContest();
	}
	/**
	 * プロコン用オブジェクト作成
	 * @return l_valueList プロコン出題範囲内の値のリスト
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
	 * シングルトンインスタンス取得
	 * @return m_single シングルトンインスタンス
	 */
	public static ValueListFactory getInstance()
	{
		return m_single;
	}
}
