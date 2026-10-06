create table tipos_animal (
    id bigint not null,
    categoria varchar(255),
    descricao varchar(255),
    alimentacao varchar(255),
    porte varchar(255),
    primary key (id)
) engine = InnoDB;

create table animais (
    id bigint not null,
    nome varchar(255),
    especie varchar(255),
    idade varchar(255),
    habitat varchar(255),
    primary key (id)
) engine = InnoDB;

create table animais_seq (next_val bigint) engine = InnoDB;

insert into animais_seq (next_val) values (1);
