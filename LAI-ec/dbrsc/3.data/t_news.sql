DELETE FROM t_news;
SET sql_mode='PIPES_AS_CONCAT';
-- �֐�����'('�̊Ԃɋ󔒂�����Ɛ������F������Ȃ��̂ŗv����
INSERT INTO t_news values
(1, 2, '2015/12/01 00:00:00', 
  DATE_ADD(CURDATE(), INTERVAL -1 SECOND), 1, true, true,
  '�j���[�X�̂������i�ߋ��j', '����̓j���[�X�̕\������Ȃ��e�X�g�p�B<br>����͏o�Ȃ��͂��B'),
(2, 2, CURDATE(), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 60 DAY), INTERVAL -1 SECOND), 1, true, true,
  '���̂��X�I�[�v���I', '�{���߂ł����݂Ȃ��܂̑��̂��X���X�^�[�g���܂����B<br>���Ȃ��̖`�����C�t�ɐ��񂨖𗧂Ă��������B'),
(3, 2, CURDATE(), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 60 DAY), INTERVAL -1 SECOND), 2, true, true,
  '�e���`���[�̃C�`�I�V�I', '�g�b�v�y�[�W�Ƀe���`���[�̃C�`�I�V�I���f�ڂ��܂����B���𔃂�������������Ƃ肠������������I'),
(4, 2, CURDATE(), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 60 DAY), INTERVAL -1 SECOND), 3, true, true,
  '�I�[�v�j���O�L�����y�[���Ńv���[���g���Q�b�g�I',
  DATE_FORMAT(CURDATE(), '%m/%d') || '�`' || DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 21 DAY), '%m/%d') || 
  '�̊Ԃɑ��̂��X�T�C�g��10,000VM�ȏゲ�w���������������q�l�̒����璊�I�őf�G�ȃv���[���g���������Ⴂ�܂��B'
  ),
(5, 2, DATE_ADD(CURDATE(), INTERVAL 60 DAY), 
  DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 120 DAY), INTERVAL -1 SECOND), 1, true, true,
  '�j���[�X�̂������i�����j', '����̓j���[�X�̕\������Ȃ��e�X�g�p�B<br>����͏o�Ȃ��͂��B')
;
commit;
