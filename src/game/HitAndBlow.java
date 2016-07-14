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

		plan_b();
		
	}
	
	private static void plan_b()
	{	
		// �Q�[���J�n�ɔ������񔻒�t���O
		boolean l_isFirstTime = true;
		// �Q�[���Ǘ��C���X�^���X�̐錾
		GameManager l_manager = new GameManager();
		// ���������\�b�h�Ăяo��
		l_manager.initialize();
		// �������͑҂�
		GameReader.readLine();
		// �����\��
		GameWriter.writeLine( l_manager.printAnserList() );
		// ���[�v
		while( true )
		{
			// ���͑҂�
			String l_input = GameReader.readLine();
			// ���͂����ɃQ�[���󋵍X�V
			l_manager.updateGameSituation( l_input );
			
			// �Q�[���󋵂��I�������𖞂������ꍇ�I��
			if ( l_manager.isWin() )
			{
				// �I�������B��
				break;
			}
			else
			{
				// �I���������B��
				if ( l_isFirstTime )
				{
					// ����̓Q�[���󋵕ω��ɔ���Hit�ӏ����肪�s�\�Ȃ��߁A�ԍ��ύX���s���ďI��
					l_isFirstTime = false;
				}
				else
				{
					// �󋵓��e���m�F
					if ( l_manager.situationIsChanged() )
					{
						// �ω����e���m�F
						switch( l_manager.hitIsChanged() )
						{
						case 0:
							// �q�b�g���ω��Ȃ�
							switch( l_manager.blowIsChanged() )
							{
							case 0:
								// �u���E���ω��Ȃ�
								break;
							case 1:
								// �u���E������
								l_manager.blowPlus();
								break;
							case -1:
								// �u���E������
								l_manager.blowMinus();
								break;
							}
							break;
						case 1:
							// �q�b�g������
							l_manager.hitPlus();
							break;
						case -1:
							// �q�b�g������
							l_manager.hitMinus();
							break;
						}
					}
					else
					{
						//l_manager.gameContinue();
						// �ω��Ȃ�
					}
				}
				// �񓚍X�V
				l_manager.changeNumber();
				GameWriter.writeLine( l_manager.printAnserList() );
			}
			
		}
		GameWriter.outputCount();
	}
}
