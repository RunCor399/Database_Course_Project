INSERT INTO aeroporto(ICAO, IATA, citta)
VALUES 
	#("LIRF", "FCO", "Roma"),
   # ("LIMC", "MXP", "Milano"),
   # ("EHAM", "AMS", "Amsterdam"),
   # ("EGLL", "LHR", "Londra"),
   # ("LFPG", "CDG", "Parigi"),
   # ("EDDT", "TXL", "Berlino");
    
    
INSERT INTO dipendente_aeroporto(codDipendente, nome, cognome, genere, citta, indirizzo, ruolo, ruoloInterno)
VALUES
	("AA00", "Marco", "Bianchi", "M", "Roma", "Via verdi 12", "Controllore", "Tower"),
    ("AB10", "Luca", "Rossi", "M", "Milano", "Via dante 20", "Meccanico", NULL),
    ("AC20", "Matteo", "Carti", "M", "Genova", "Via rossi 40", "Meccanico", NULL),
    ("AD30", "Giacomo", "Gialli", "M", "Torino", "Via petrarca 1", "Sicurezza", NULL),
    ("AE40", "Maria", "Grandi", "F", "Pesaro", "Via gialli 5", "Controllore", "Ground"),
    ("AF50", "Lucia", "Vivi", "F", "Rimini", "Via lori 7", "Operaio", NULL);
    
INSERT INTO compagnia_aerea(codCompagnia, nome, sede)
VALUES
	("AZA", "Alitalia", "Roma"),
    ("AFR", "Air France", "Parigi"),
    ("RYN", "RyanAir", "Dublin"),
    ("LFH", "Lufthansa", "Berlino"),
    ("KLM", "Royal Dutch Airlines", "Amsterdam");
    
INSERT INTO aereo(codAereo, modelloAeromobile, numeroPosti, flagManutenzione, codCompagnia)
VALUES
	("1237", "Boeing 747", 400, FALSE, "AFR"),
    ("3546", "Airbus A380", 600, FALSE, "AFR"),
    ("6285", "Boeing 737", 200, FALSE, "RYN"),
    ("0149", "Boeing 737", 200, FALSE, "RYN"),
    ("7845", "Airbus 320", 250, FALSE, "LFH"),
    ("6223", "Airbus 340", 300, FALSE, "LFH"),
    ("5455", "Airbus 318", 400, FALSE, "AZA"),
    ("7890", "Airbus 319", 600, FALSE, "AZA"),
    ("6422", "MD 11", 200, FALSE, "KLM"),
    ("1221", "MD 5", 400, FALSE, "KLM");
    
INSERT INTO dipendente_compagnia(codDipendente, codCompagnia, nome, cognome, genere, citta, indirizzo, ruolo, grado)
VALUES
	("FD11", "AZA", "Mattia", "Rossi", "M", "Roma", "Via gino 40", "Pilota", "Capitano"),
    ("DF12", "AZA", "Luca", "Gill", "M", "Viterbo", "Via cali 30", "Pilota", "Ufficiale"),
	("LM67", "AZA", "Charlotte", "Ryan", "F", "Roma", "Via gori 12", "Assistente", NULL),
	
    ("AD11", "RYN", "Brandon", "Walters", "M", "Dublino", "Via waters 12", "Pilota", "Ufficiale"),
	("GT32", "RYN", "Tina", "Rivera", "F", "Dublino", "Via paul 22", "Pilota", "Capitano"),
	("LK98", "RYN", "Vera", "Duncan", "F", "Dublino", "Via bryan 54", "Assistente", NULL),

	("FF44", "KLM", "Erik", "Fox", "M", "Amsterdam", "Via nas 14", "Pilota", "Capitano"),
	("GH12", "KLM", "Brian", "Yates", "M", "Amsterdam", "Via black 56", "Pilota", "Ufficiale"),
	("HJ89", "KLM", "Carla", "Austin", "F", "Amsterdam", "Via newman 15", "Assistente", NULL),

	("DD43", "AFR", "Dustin", "Franklin", "M", "Parigi", "Via blake 55", "Pilota", "Capitano"),
	("LK99", "AFR", "Marylin", "Horton", "F", "Parigi", "Via garza 89", "Pilota", "Ufficiale"),
    ("RT45", "AFR", "Jose", "Doyle", "M", "Parigi", "Via king 1", "Assistente", NULL),
    
    ("RT45", "LFH", "Rosie", "Price", "F", "Berlino", "Via porter 67", "Pilota", "Capitano"),
    ("YU77", "LFH", "Daniel", "Boyd", "M", "Monaco", "Via dean 51", "Pilota", "Ufficiale"),
    ("KJ87", "LFH", "Jesse", "Logan", "F", "Berlino", "Via pratt 12", "Assistente", NULL);
    
    

INSERT INTO volo(codCompagnia, codVolo, origineICAO, destinazioneICAO, codAereo)
VALUES
	("AZA", "4569", "LIRF", "LFPG", "7890"),
    ("AZA", "3442", "LIRF", "EDDT", "5455"),
    
    ("AFR", "3564", "LFPG", "LIRF", "1237"),
    ("AFR", "2332", "EDDT", "LFPG", "3546"),
    
    ("KLM", "6890", "EHAM", "LFPG", "6422"),
    ("KLM", "1523", "EHAM", "LIRF", "1221"),
    
    ("RYN", "7888", "LIRF", "EHAM", "6285"),
    ("RYN", "1222", "EHAM", "EDDT", "0149"),
    
    ("LFH", "5466", "EDDT", "LIRF", "7845"),
    ("LFH", "1299", "EHAM", "EDDT", "6223");
    
    
INSERT INTO calendario(idCalendario, codCompagnia, codVolo, dataPartenza, oraPartenza, dataArrivo, oraArrivo)
VALUES
	("AE33", "AZA", "4569", "2020-01-01", "12:00", "2019-01-01", "16:00"),
    ("AE65", "AZA", "3442", "2020-01-10", "20:00", NULL, NULL),
    
    ("ER44", "RYN", "7888", "2020-01-02", "09:00", "2019-01-02", "16:00"),
    ("GH66", "RYN", "1222", "2020-01-6", "21:00", NULL, NULL),

    ("HJ88", "KLM", "6890", "2020-01-01", "15:00", "2019-01-01", "17:00"),
    ("QQ32", "KLM", "1523", "2020-01-10", "15:00", NULL, NULL),
    
    ("GT55", "LFH", "5466", "2020-01-02", "12:00", "2019-01-02", "13:00"),
    ("JI99", "LFH", "1299", "2020-01-11", "19:00", NULL, NULL),
    
    ("LO98", "AFR", "2332", "2020-01-02", "08:00", "2019-01-02", "12:00"),
    ("SS22", "AFR", "3564", "2020-01-12", "20:00", NULL, NULL);
    
    
INSERT INTO servizio_volo(idServizioVolo, volo_codCompagnia, volo_codVolo, codDipendente, codCompagnia)
VALUES
	("SE33", "AZA", "4569", "FD11", "AZA"),
    ("AE22", "AZA", "4569", "DF12", "AZA"),
	("FG55", "AZA", "4569", "LM67", "AZA"),
    
    ("GH22", "KLM", "6890", "FF44", "KLM"),
    ("DE13", "KLM", "6890", "GH12", "KLM"),
	("HY11", "KLM", "6890", "HJ89", "KLM"),
    
    ("JK23", "AFR", "2332", "DD43", "AFR"),
    ("QW22", "AFR", "2332", "LK99", "AFR"),
	("FR33", "AFR", "2332", "RT45", "AFR"),
    
    ("GT44", "LFH", "5466", "RT45", "LFH"),
    ("HY77", "LFH", "5466", "YU77", "LFH"),
	("JU88", "LFH", "5466", "KJ87", "LFH"),
    
    ("LO99", "RYN", "7888", "AD11", "RYN"),
    ("DF44", "RYN", "7888", "GT32", "RYN"),
	("GT66", "RYN", "7888", "LK98", "RYN");
    

INSERT INTO manutenzione(idManutenzione, dataInizio, dataFine, codAereo, codDipendente)
VALUES
	("DG56", "2020-01-05", NULL, "5455", "AB10"),
    ("FG55", "2020-02-06", NULL, "1237", "AB10"),
    ("RT43", "2020-01-10", NULL, "6285", "AC20");
    