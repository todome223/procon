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
			System.out.println( l_manager.getAnserList() );
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
			{ // �ω�����
				// �q�b�g�����������Ȃ�
				// �m�F�Ώۂ��ꌅ�E�Ɉړ�
				
				// �q�b�g�����������Ȃ�
				// �O��̓����Y�����ԍ��̒l�ɖ߂�
				// �m�F�Ώۂ��ꌅ�E�Ɉړ�
				
				// �u���E�����������Ȃ�
				// �O��̓����Y�����ԍ��̕������g�p�֎~�ɐݒ�
				
				// �u���E�����������Ȃ�
				// ����̓����Y�����ԍ��̕������g�p�֎~�ɐݒ�
			}
			else
			{ // �ω��Ȃ�
				// ����̓����Y�����ԍ��̕������g�p�֎~�ɐݒ�
			}
			
			// �m�F�ΏۓY�����ԍ��̒l���A�l�ꗗ���X�g�̐擪����擾
			
			// �l�ꗗ���X�g�̐擪�����Ɉړ�
		}	
	}

}
