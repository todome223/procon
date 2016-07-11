package game;

import model.MySituation;
import model.ValueList;
import controller.GameManager;

/**
 * ゲームクラス
 * @author nakajima
 */
public class HitAndBlow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
			System.out.println( l_manager.getAnserList() );
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
			{ // 変化あり
				// ヒット数が増えたなら
				// 確認対象を一桁右に移動
				
				// ヒット数が減ったなら
				// 前回の同じ添え字番号の値に戻す
				// 確認対象を一桁右に移動
				
				// ブロウ数が増えたなら
				// 前回の同じ添え字番号の文字を使用禁止に設定
				
				// ブロウ数が減ったなら
				// 今回の同じ添え字番号の文字を使用禁止に設定
			}
			else
			{ // 変化なし
				// 今回の同じ添え字番号の文字を使用禁止に設定
			}
			
			// 確認対象添え字番号の値を、値一覧リストの先頭から取得
			
			// 値一覧リストの先頭を後ろに移動
		}	
	}

}
