#A1 INSERIMENTO COMPAGNIA AEREA
INSERT INTO compagnia_aerea(codCompagnia, nome, sede)
VALUES (?, ?, ?);

#A2 INSERIMENTO DIPENDENTE AEROPORTO
INSERT INTO dipendente_aeroporto(codDipendente, nome, cognome, genere, citta, indirizzo, ruolo, ruoloInterno)
VALUES (?, ?, ?, ?, ?, ?, ?, ?);


#A3 INSERIMENTO TASSE
	#conto il numero di voli effettuati dalla compagnia nel mese precedente alla data
    # di fatturazione inserita
	INSERT INTO tassa(codCompagnia, dataFatturazione, dataPagamento, importo)
	VALUES (?, ?, NULL, (SELECT COUNT(V.codVolo) AS numero_voli
										FROM compagnia_aerea CA, volo V, calendario C
										WHERE CA.codCompagnia = V.codCompagnia
										AND C.codCompagnia = V.codCompagnia
										AND C.codVolo = V.codVolo
										AND CA.codCompagnia = ?
										AND C.dataPartenza BETWEEN ? AND ?) * 5000);
    

#A4 VISUALIZZARE TASSE COMPAGNIA
SELECT CA.codCompagnia, CA.nome, T.dataFatturazione, T.dataPagamento, T.importo 
FROM tassa T, compagnia_aerea CA
WHERE CA.codCompagnia = T.codCompagnia
AND T.codCompagnia = ?;


#A5 VISUALIZZAZIONE IMPORTO ANNUO VERSATO DA UNA  COMPAGNIA
SELECT IFNULL(SUM(T.importo), 0)
FROM compagnia_aerea CA, tassa T
WHERE CA.codCompagnia = T.codCompagnia
AND CA.codCompagnia = ?
AND YEAR(T.dataFatturazione) = ?
AND T.dataPagamento IS NOT NULL;

#A6 VISUALIZZAZIONE DIPENDENTI PER RUOLO
SELECT *
FROM dipendente_aeroporto
WHERE ruolo = ?;

#A7 FATTURATO ANNUO DELL’AEROPORTO
SELECT IFNULL(SUM(importo), 0) AS Fatturato_Totale
FROM tassa
WHERE dataPagamento IS NOT NULL;

#A8 VISUALIZZAZIONE DELLE MANUTENZIONI
SELECT CA.codCompagnia, A.codAereo, M.codDipendente, DA.nome, DA.cognome, A.modelloAeromobile
FROM compagnia_aerea CA, aereo A, dipendente_aeroporto DA, manutenzione M
WHERE M.codAereo = A.codAereo
AND M.codDipendente = DA.codDipendente
AND A.codCompagnia = CA.codCompagnia;

#A9 LISTA DESTINAZIONI COMPAGNIA AEREA
SELECT DISTINCT V.destinazioneICAO, A.IATA, A.citta

FROM compagnia_aerea CA, volo V, aeroporto A
WHERE CA.codCompagnia = ?
AND CA.codCompagnia = V.codCompagnia
AND V.destinazioneICAO = A.ICAO;


#A10 INSERIMENTO AEREO DA MANUTENERE
INSERT INTO manutenzione(idManutenzione, dataInizio, dataFine, codAereo, codDipendente)
VALUES (?, ?, ?, ?, ?);

#A11 VOLI TOTALI DI UN AEREO
SELECT A.codAereo, COUNT(codVolo)
FROM aereo A, volo V
WHERE A.codAereo = V.codAereo
AND V.codAereo = ?;

#C1 INSERIMENTO DIPENDENTI
INSERT INTO dipendente_compagnia(codDipendente, codCompagnia, nome, cognome, genere, citta, indirizzo, ruolo, grado)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);

#C2, C3, C4 INSERIMENTO DI UN NUOVO VOLO
INSERT INTO servizio_volo(idServizioVolo, volo_codCompagnia, volo_codVolo, codDipendente, codCompagnia)
VALUES (?, ?, ?, ?, ?);

INSERT INTO calendario(idCalendario, codCompagnia, codVolo, dataPartenza, oraPartenza, dataArrivo, oraArrivo)
VALUES (?, ?, ?, ?, ?, ?, ?);



#C5 VISUALIZZAZIONE DIPENDENTI PER RUOLO
SELECT codDipendente, nome, cognome, genere, citta, indirizzo, ruolo, grado
FROM dipendente_compagnia
WHERE ruolo = ?
AND codCompagnia = ?;

#C6 VISUALIZZAZIONE DATI VOLO
SELECT V.codVolo, V.codCompagnia, V.origineICAO, V.destinazioneICAO, DC.codDipendente, DC.nome, DC.cognome, DC.ruolo, DC.grado
FROM volo V, servizio_volo SV, dipendente_compagnia DC
WHERE V.codVolo = SV.volo_codVolo
AND V.codCompagnia = SV.volo_codCompagnia
AND SV.codCompagnia = DC.codCompagnia
AND SV.codDipendente = DC.codDipendente
AND V.codCompagnia = ?
AND V.codVolo = ?;

#C7 TASSE PAGATE IN UN ANNO
SELECT YEAR(dataPagamento) anno, IFNULL(SUM(importo), 0) importo
FROM tassa
WHERE codCompagnia = ?
AND YEAR(dataPagamento) = ?;

#C8 PAGAMENTO DI UNA TASSA
UPDATE tassa
SET dataPagamento = ?
WHERE codCompagnia = ?
AND dataFatturazione = ?;

#C9 INSERIMENTO DI UN NUOVO AEREO
INSERT INTO aereo(codAereo, modelloAeromobile, numeroPosti, flagManutenzione, codCompagnia)
VALUES (?, ?, ?, ?, ?);

#C10 AGGIORNAMENTO STATO DI UN AEREO
UPDATE aereo
SET flagManutenzione = ?
WHERE codAereo = ?;

#C11 INSERIMENTO PERSONALE IN UN VOLO
INSERT INTO servizio_volo(idServizioVolo, volo_codCompagnia, volo_codVolo, codDipendente, codCompagnia)
VALUES(?, ?, ?, ?, ?);




