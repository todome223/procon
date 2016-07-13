package game;

import java.io.IOException;

import model.MySituation;
import model.ValueList;
import controller.GameManager;
import controller.GameReader;

/**
 * ゲームクラス
 * @author nakajima
 */
public class HitAndBlow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		plan_b();
		
	}
	
	private static void plan_b()
	{
		// ゲーム開始に伴う初回判定フラグ
		boolean l_isFirstTime = true;
		// ゲーム管理インスタンスの宣言
		GameManager l_manager = new GameManager();
		// 初期化メソッド呼び出し
		l_manager.initialize();
		// 初期入力待ち
		GameReader.readLine();
		// 初期表示
		System.out.println(l_manager.getAnserList());
		// ループ
		while( true )
		{
			// 入力待ち
			String l_input = GameReader.readLine();
			// 入力を下にゲーム状況更新
			l_manager.updateGameSituation( l_input );
			
			// ゲーム状況が終了条件を満たした場合終了
			if ( l_manager.isWin() )
			{
				// 終了条件達成
				break;
			}
			else
			{
				// 終了条件未達成
				if ( l_isFirstTime )
				{
					// 初回はゲーム状況変化に伴うHit箇所特定が不可能なため、番号変更を行って終了
					l_isFirstTime = false;
				}
				else
				{
					// 状況内容を確認
					if ( l_manager.situationIsChanged() )
					{
						
						//System.out.println("hc : " + l_manager.hitIsChanged() );
						// 変化内容を確認
						switch( l_manager.hitIsChanged() )
						{
						case 0:
							// ヒット数変化なし
							break;
						case 1:
							// ヒット数増加
							l_manager.hitPlus();
							break;
						case -1:
							// ヒット数減少
							l_manager.hitMinus();
							break;
						}
						switch( l_manager.blowIsChanged() )
						{
						case 0:
							// ブロウ数変化なし
							break;
						case 1:
							// ブロウ数増加
							l_manager.blowPlus();
							break;
						case -1:
							// ブロウ数減少
							l_manager.blowMinus();
							break;
						}
					}
					else
					{
						//l_manager.gameContinue();
						// 変化なし
					}
				}
				// 回答更新
				l_manager.changeNumber();
				System.out.println( l_manager.getAnserList() );
			}
			
		}
		
		// 一桁目の文字の変更メソッド
	}
}
