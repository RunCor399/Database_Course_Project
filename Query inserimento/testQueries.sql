
#SELECT DC.codDipendente, DC.codCompagnia, DC.nome, DC.cognome 
#FROM dipendente_compagnia DC, servizio_volo SV, volo V
#WHERE DC.codCompagnia = SV.codCompagnia
#AND DC.codDipendente = SV.codDipendente
#AND DC.codDipendente = SV.codDipendente
#AND SV.volo_codVolo = V.codVolo
#AND SV.volo_codCompagnia = V.codCompagnia
#AND V.origineICAO = "EHAM";


   
#RIGHT QUERY
#INSERT INTO tassa(codCompagnia, dataFatturazione, dataPagamento, importo)
#VALUES 
#	("KLM", "2020-01-31", NULL, (SELECT COUNT(V.codVolo) AS numero_voli
	#							FROM compagnia_aerea CA, volo V, calendario C
	#							WHERE CA.codCompagnia = V.codCompagnia
	#							AND C.codCompagnia = V.codCompagnia
		#						AND C.codVolo = V.codVolo
		#						AND CA.codCompagnia = "KLM"
		#						AND C.dataPartenza BETWEEN "2020-01-01" AND "2020-01-31") * 5000);

SELECT * FROM volo WHERE codCompagnia = "AZA";
#SELECT V.codVolo, V.codCompagnia, V.origineICAO, V.destinazioneICAO, DC.codDipendente, DC.nome, DC.cognome, DC.ruolo, DC.grado 
 #               FROM volo V  LEFT JOIN servizio_volo SV ON (V.codVolo = SV.volo_codVolo) 
  #              AND (V.codCompagnia = SV.volo_codCompagnia) AND (SV.volo_codCompagnia = "AZA");
              #  AND (SV.codCompagnia = "AZA") AND (V.codCompagnia = "AZA")
              #  LEFT  JOIN dipendente_compagnia DC ON (SV.codCompagnia = DC.codCompagnia) 
             #   AND (SV.codDipendente = DC.codDipendente);