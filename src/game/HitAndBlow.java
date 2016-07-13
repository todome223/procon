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
		int mode = 2;
		
		switch(mode)
		{
		case 1:
			plan_a();
			break;
		case 2:
			plan_b();
			break;
		}
	}
	
	private static void plan_a()
	{
		// ゲーム管理インスタンスの宣言
		GameManager l_manager = new GameManager();
		// 初期化メソッド呼び出し
		l_manager.initialize();
		// 入力待ち
		
		// 初期入力確保によりゲームスタート、基本無限ループ
		while(true){
			// 前回のゲーム状態を一時退避
			MySituation l_befSituation = l_manager.getSituation();
			// 前回の回答リストを一時退避
			ValueList l_befAnserList = l_manager.getAnserList();
			// 回答を出力
			System.out.println( l_manager.getAnserList().toString() );
			// 入力待ち
			
			// 入力確保によりゲーム状況更新
			
			// ゲーム状況が終了状態にあるか否か
			if ( l_manager.isWin() )
			{
				// ゲーム終了
				break;
			}
			
			// 変化があったか否か
			if ( !l_befSituation.equals( l_manager.getSituation() ) )
			{
			}
			else
			{ // 変化なし
				// 今回の同じ添え字番号の文字を使用禁止に設定
				//l_manager.changeUnavailableFromValue(i_value);
			}
			
			// 確認対象添え字番号の値を、値一覧リストの先頭から取得
			
			// 値一覧リストの先頭を後ろに移動
		}	
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
							break;
						case -1:
							// ブロウ数減少
							break;
						}
					}
					else
					{
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
