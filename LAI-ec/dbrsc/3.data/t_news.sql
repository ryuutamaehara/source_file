DELETE FROM t_news;
SET sql_mode='PIPES_AS_CONCAT';
-- 関数名と'('の間に空白を入れると正しく認識されないので要注意
INSERT INTO t_news values
(1, 2, '2015/12/01 00:00:00', 
  DATE_ADD(CURDATE(), INTERVAL -1 SECOND), 1, true, true,
  'ニュースのお試し（過去）', 'これはニュースの表示されないテスト用。<br>これは出ないはず。'),
(2, 2, CURDATE(), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 60 DAY), INTERVAL -1 SECOND), 1, true, true,
  '村のお店オープン！', '本日めでたくみなさまの村のお店がスタートしました。<br>あなたの冒険ライフに是非お役立てください。'),
(3, 2, CURDATE(), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 60 DAY), INTERVAL -1 SECOND), 2, true, true,
  'テンチョーのイチオシ！', 'トップページにテンチョーのイチオシ！を掲載しました。何を買おうか迷ったらとりあえずここから！'),
(4, 2, CURDATE(), DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 60 DAY), INTERVAL -1 SECOND), 3, true, true,
  'オープニングキャンペーンでプレゼントをゲット！',
  DATE_FORMAT(CURDATE(), '%m/%d') || '～' || DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 21 DAY), '%m/%d') || 
  'の間に村のお店サイトで10,000VM以上ご購入いただいたお客様の中から抽選で素敵なプレゼントをあげちゃいます。'
  ),
(5, 2, DATE_ADD(CURDATE(), INTERVAL 60 DAY), 
  DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 120 DAY), INTERVAL -1 SECOND), 1, true, true,
  'ニュースのお試し（未来）', 'これはニュースの表示されないテスト用。<br>これは出ないはず。')
;
commit;
