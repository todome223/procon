package game;

import java.io.IOException;

import model.MySituation;
import model.ValueList;
import controller.GameManager;
import controller.GameReader;

/**
 * �Q�[���N���X
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
		// �Q�[���Ǘ��C���X�^���X�̐錾
		GameManager l_manager = new GameManager();
		// ���������\�b�h�Ăяo��
		l_manager.initialize();
		// ���͑҂�
		
		// �������͊m�ۂɂ��Q�[���X�^�[�g�A��{�������[�v
		while(true){
			// �O��̃Q�[����Ԃ��ꎞ�ޔ�
			MySituation l_befSituation = l_manager.getSituation();
			// �O��̉񓚃��X�g���ꎞ�ޔ�
			ValueList l_befAnserList = l_manager.getAnserList();
			// �񓚂��o��
			System.out.println( l_manager.getAnserList().toString() );
			// ���͑҂�
			
			// ���͊m�ۂɂ��Q�[���󋵍X�V
			
			// �Q�[���󋵂��I����Ԃɂ��邩�ۂ�
			if ( l_manager.isWin() )
			{
				// �Q�[���I��
				break;
			}
			
			// �ω������������ۂ�
			if ( !l_befSituation.equals( l_manager.getSituation() ) )
			{
			}
			else
			{ // �ω��Ȃ�
				// ����̓����Y�����ԍ��̕������g�p�֎~�ɐݒ�
				//l_manager.changeUnavailableFromValue(i_value);
			}
			
			// �m�F�ΏۓY�����ԍ��̒l���A�l�ꗗ���X�g�̐擪����擾
			
			// �l�ꗗ���X�g�̐擪�����Ɉړ�
		}	
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
		System.out.println(l_manager.getAnserList());
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
						
						//System.out.println("hc : " + l_manager.hitIsChanged() );
						// �ω����e���m�F
						switch( l_manager.hitIsChanged() )
						{
						case 0:
							// �q�b�g���ω��Ȃ�
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
						switch( l_manager.blowIsChanged() )
						{
						case 0:
							// �u���E���ω��Ȃ�
							break;
						case 1:
							// �u���E������
							break;
						case -1:
							// �u���E������
							break;
						}
					}
					else
					{
						// �ω��Ȃ�
					}
				}
				// �񓚍X�V
				l_manager.changeNumber();
				System.out.println( l_manager.getAnserList() );
			}
			
		}
		
		// �ꌅ�ڂ̕����̕ύX���\�b�h
	}
}
