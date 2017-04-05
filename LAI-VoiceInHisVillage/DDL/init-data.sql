insert into user_profile values (
'test', 'poipoi', '村の人Ａ', false, 'MALE', 23
);
commit;


insert into voice values (
'20150608164212-1212',
'test', CURRENT_TIMESTAMP,
'最初の投稿です。',
'最初の投稿の本文です。
どうでっしゃろ？'
);
commit;

insert into voice values (
'20150608164923-1254',
'test', CURRENT_TIMESTAMP,
'２番目の投稿です。',
'２番目の投稿の本文です。'
);
commit;
