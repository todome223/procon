package game;

import model.MySituation;
import model.ValueList;
import controller.GameManager;

/**
 * �Q�[���N���X
 * @author nakajima
 */
public class HitAndBlow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
				if ( l_befSituation.equalsHit( l_manager.getSituation() ) )
				{ // �q�b�g���ɕω�����
					// �q�b�g���������Ă���ꍇ
					// �q�b�g���������Ă���ꍇ
					// �m�F�Ώۂ��ꌅ�E�Ɉړ�
					l_manager.checkNextSuffix();
				}
				else if ( l_befSituation.equalsBlow( l_manager.getSituation() ) )
				{ // �u���E���ɕω�����
					// �u���E���������Ă���ꍇ
					// �O��̓����Y�����ԍ��̕������g�p�֎~�ɐݒ�
					// �u���E���������Ă���ꍇ
					// ����̓����Y�����ԍ��̕������g�p�֎~�ɐݒ�
				}	
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

}
