package game;

import controller.GameManager;
import controller.GameReader;
import controller.GameWriter;

/**
 * �Q�[���N���X
 * @author nakajima
 */
public class HitAndBlow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// HitAndBlow�Q�[���̃��\�b�h�Ăяo��
		doGame();
	}

	/**
	 * HitAndBlow�Q�[�����\�b�h
	 */
	private static void doGame() {
		// �Q�[���J�n�ɔ������񔻒�t���O
		boolean l_isFirstTime = true;

		// �Q�[���Ǘ��C���X�^���X�̐錾
		GameManager l_manager = new GameManager();

		// ���������\�b�h�Ăяo��
		l_manager.initialize();

		// �������͑҂�
		GameReader.readLine();

		// �����\��
		GameWriter.writeLine(l_manager.printAnswerList());

		// ���[�v
		while (true) {
			// ���͑҂�
			String l_input = GameReader.readLine();

			// ���͂����ɃQ�[���󋵍X�V
			l_manager.updateGameSituation (l_input);

			// �Q�[���󋵂��I�������𖞂������ꍇ�I��

			// �I�������B��
			if (l_manager.isWin()) {
				break;
			} else {
				// �I���������B��
				if (l_isFirstTime){
					// ����̓Q�[���󋵕ω��ɔ���Hit�ӏ����肪�s�\�Ȃ��߁A�ԍ��ύX���s���ďI��
					l_isFirstTime = false;
				} else {
					// ���݂̏�Ԃ��m�F
					if (l_manager.situationIsChanged()) {
						// �ω����m�F
						switch (l_manager.hitIsChanged()) {
						// �q�b�g���ω��Ȃ�
						case 0:
							switch (l_manager.blowIsChanged()) {

							// �u���E���ω��Ȃ�
							case 0:
								break;

							// �u���E������
							case 1:
								l_manager.blowPlus();
								break;

							// �u���E������
							case -1:
								l_manager.blowMinus();
								break;
							}
							break;

						// �q�b�g������
						case 1:
							l_manager.hitPlus();
							break;

						// �q�b�g������
						case -1:
							l_manager.hitMinus();
							break;
						}
					}
				}
				// �񓚍X�V
				l_manager.changeNumber();

				// �񓚕\��
				GameWriter.writeLine(l_manager.printAnswerList());
			}
		}
	}
}
