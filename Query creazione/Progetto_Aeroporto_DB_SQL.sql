-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.1              
-- * Generator date: Dec  4 2018              
-- * Generation date: Fri Jun  5 16:07:34 2020 
-- * LUN file: C:\Users\manue\OneDrive\Desktop\Materiale didattico università\Secondo anno\Basi di Dati\Progetto\Progetto_Aeroporto_DB.lun 
-- * Schema: Aeroporto_Logico/1 
-- ********************************************* 


-- Database Section
-- ________________ 

create database aeroporto_gestionale;
use aeroporto_gestionale;


create table AEREO (
     codAereo varchar(4) not null,
     modelloAeromobile varchar(20) not null,
     numeroPosti int not null,
     flagManutenzione boolean not null,
     codCompagnia varchar(3) not null,
     constraint IDAEREO primary key (codAereo));

create table AEROPORTO (
     ICAO varchar(4) not null,
     IATA varchar(3) not null,
     citta varchar(20) not null,
     constraint IDAEROPORTO primary key (ICAO));

create table CALENDARIO (
     idCalendario varchar(4) not null,
     codCompagnia varchar(3) not null,
     codVolo varchar(4) not null,
     dataPartenza date not null,
     oraPartenza time not null,
     dataArrivo date,
     oraArrivo time,
     constraint IDCALENDARIO_1 primary key (idCalendario),
     constraint IDCALENDARIO_1_1 unique (codCompagnia, codVolo, dataPartenza, oraPartenza));

create table COMPAGNIA_AEREA (
     codCompagnia varchar(3) not null,
     nome varchar(20) not null,
     sede varchar(20) not null,
     constraint IDCOMPAGNIA_AEREA primary key (codCompagnia));

create table DIPENDENTE_AEROPORTO (
     codDipendente varchar(4) not null,
     nome varchar(30) not null,
     cognome varchar(30) not null,
     genere varchar(10) not null,
     citta varchar(20) not null,
     indirizzo varchar(20) not null,
     ruolo varchar(20) not null,
     ruoloInterno varchar(20),
     constraint IDDIPENDENTE_AEROPORTO primary key (codDipendente));

create table DIPENDENTE_COMPAGNIA (
     codDipendente varchar(4) not null,
     codCompagnia varchar(3) not null,
     nome varchar(30) not null,
     cognome varchar(30) not null,
     genere varchar(10) not null,
     citta varchar(20) not null,
     indirizzo varchar(20) not null,
     grado varchar(10),
     constraint IDDIPENDENTE_COMPAGNIA primary key (codDipendente, codCompagnia));

create table MANUTENZIONE (
     idManutenzione varchar(4) not null,
     dataInizio date not null,
     dataFine date,
     codAereo varchar(4) not null,
     codDipendente varchar(4) not null,
     constraint IDMANUTENZIONE primary key (idManutenzione),
     constraint IDMANUTENZIONE_1 unique (dataInizio, codAereo, codDipendente));

create table SERVIZIO_VOLO (
     idServizioVolo varchar(4) not null,
     volo_codCompagnia varchar(3) not null,
     volo_codVolo varchar(4) not null,
     codDipendente varchar(4) not null,
     codCompagnia varchar(3) not null,
     constraint IDSERVIZIO_VOLO primary key (idServizioVolo),
     constraint IDSERVIZIO_VOLO_1 unique (volo_codCompagnia, volo_codVolo, codDipendente, codCompagnia));

create table TASSA (
     codCompagnia varchar(3) not null,
     dataFatturazione date not null,
     dataPagamento date,
     importo int not null,
     constraint IDTASSA primary key (codCompagnia, dataFatturazione));

create table VOLO (
     codCompagnia varchar(3) not null,
     codVolo varchar(4) not null,
     origineICAO varchar(4) not null,
     destinazioneICAO varchar(4) not null,
     codAereo varchar(4) not null,
     constraint IDVOLO primary key (codCompagnia, codVolo));


-- Constraints Section
-- ___________________ 

alter table AEREO add constraint FKpossiede
     foreign key (codCompagnia)
     references COMPAGNIA_AEREA (codCompagnia);

alter table CALENDARIO add constraint FKschedulazione
     foreign key (codCompagnia, codVolo)
     references VOLO (codCompagnia, codVolo);

alter table DIPENDENTE_COMPAGNIA add constraint FKlavora
     foreign key (codCompagnia)
     references COMPAGNIA_AEREA (codCompagnia);

alter table MANUTENZIONE add constraint FKman_AER
     foreign key (codAereo)
     references AEREO (codAereo);

alter table MANUTENZIONE add constraint FKman_DIP
     foreign key (codDipendente)
     references DIPENDENTE_AEROPORTO (codDipendente);

alter table SERVIZIO_VOLO add constraint FKdipendente
     foreign key (codDipendente, codCompagnia)
     references DIPENDENTE_COMPAGNIA (codDipendente, codCompagnia);

alter table SERVIZIO_VOLO add constraint FKvolo
     foreign key (volo_codCompagnia, volo_codVolo)
     references VOLO (codCompagnia, codVolo);

alter table TASSA add constraint FKpagamento
     foreign key (codCompagnia)
     references COMPAGNIA_AEREA (codCompagnia);

alter table VOLO add constraint FKorigine
     foreign key (origineICAO)
     references AEROPORTO (ICAO);

alter table VOLO add constraint FKdestinazione
     foreign key (destinazioneICAO)
     references AEROPORTO (ICAO);

alter table VOLO add constraint FKsvolge
     foreign key (codAereo)
     references AEREO (codAereo);

alter table VOLO add constraint FKeffettua
     foreign key (codCompagnia)
     references COMPAGNIA_AEREA (codCompagnia);

