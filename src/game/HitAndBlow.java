package game;

import controller.GameManager;
import controller.GameReader;
import controller.GameWriter;

/**
 * ゲームクラス
 * @author nakajima
 */
public class HitAndBlow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// HitAndBlowゲームのメソッド呼び出し
		doGame();
	}

	/**
	 * HitAndBlowゲームメソッド
	 */
	private static void doGame() {
		// ゲーム開始に伴う初回判定フラグ
		boolean l_isFirstTime = true;

		// ゲーム管理インスタンスの宣言
		GameManager l_manager = new GameManager();

		// 初期化メソッド呼び出し
		l_manager.initialize();

		// 初期入力待ち
		GameReader.readLine();

		// 初期表示
		GameWriter.writeLine(l_manager.printAnswerList());

		// ループ
		while (true) {
			// 入力待ち
			String l_input = GameReader.readLine();

			// 入力を下にゲーム状況更新
			l_manager.updateGameSituation (l_input);

			// ゲーム状況が終了条件を満たした場合終了

			// 終了条件達成
			if (l_manager.isWin()) {
				break;
			} else {
				// 終了条件未達成
				if (l_isFirstTime){
					// 初回はゲーム状況変化に伴うHit箇所特定が不可能なため、番号変更を行って終了
					l_isFirstTime = false;
				} else {
					// 現在の状態を確認
					if (l_manager.situationIsChanged()) {
						// 変化を確認
						switch (l_manager.hitIsChanged()) {
						// ヒット数変化なし
						case 0:
							switch (l_manager.blowIsChanged()) {

							// ブロウ数変化なし
							case 0:
								break;

							// ブロウ数増加
							case 1:
								l_manager.blowPlus();
								break;

							// ブロウ数減少
							case -1:
								l_manager.blowMinus();
								break;
							}
							break;

						// ヒット数増加
						case 1:
							l_manager.hitPlus();
							break;

						// ヒット数減少
						case -1:
							l_manager.hitMinus();
							break;
						}
					}
				}
				// 回答更新
				l_manager.changeNumber();

				// 回答表示
				GameWriter.writeLine(l_manager.printAnswerList());
			}
		}
	}
}
