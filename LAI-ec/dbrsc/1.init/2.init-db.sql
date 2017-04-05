create database ec character set = 'utf8';
create user 'ec'@'localhost' identified by 'ecwork';
grant all privileges on ec.* to ec@localhost;
