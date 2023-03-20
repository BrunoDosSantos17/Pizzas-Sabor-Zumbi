drop database if exists forum ;
create database forum;
use forum;

create table jogador(
	id_jogador int primary key auto_increment,
    nickname varchar(60) not null,
	senha varchar(60) not null
);

create table jogada(
	id_jogada int primary key auto_increment,
    score bigint,
    modo_jogo enum('CAMPANHA','INFINITO') not null,
    id_jogador int not null,
    foreign key(id_jogador) references jogador(id_jogador)
);

create table forum(
	nome varchar(15) not null,
	msg varchar(100) not null,
	strData varchar(100) not null,
	id int not null auto_increment primary key
);

create table login (
    login varchar(60) primary key,
    senha varchar(60)
);

insert into login values ("698DC19D489C4E4DB73E28A713EAB07B", "202CB962AC59075B964B07152D234B70");
