USE negozio;

# 1) Una selezione ordinata su un attributo di una tabella con condizioni in And e OR
# Ordinare gli articoli di colore "Bianco" in ordine crescente rispetto al prezzo, maggiore di 30€ (Articolo). 
SELECT *
FROM Articolo
WHERE Prezzo > 30 AND Colore = "Bianco"
ORDER BY Prezzo;


# 2) Una selezione su due o più tabelle con condizioni.
# Restituire il codice_cliente e i punti dei clienti maggiorenni (codice_cliente, punti). 
SELECT D.Codice_cliente as Codice, C.punti
FROM Carta_fedelta C, Dati_cliente D
WHERE C.Codice_cliente = D.Codice_cliente 
AND (DATEDIFF(CURDATE(), D.ddn) / 365) >= 18;


# 3) Una selezione aggregata su tutti i valori (es. somma di tutti gli stipendi).
# La somma del prezzo degli articoli totali presenti nel magazzino "AA1" 
SELECT SUM(A.Prezzo * A.Quantita) AS Somma_articoli, M.ID_Magazzino
FROM Articolo A JOIN Magazzino M ON (A.ID_magazzino = M.ID_magazzino)
WHERE M.ID_Magazzino = "AA1";


# 4) Una selezione aggregata su raggruppamenti (es. somma stipendi per dipartimenti).
# Restituire gli articoli, raggruppati per tipo, con il prezzo minore (Tipo, Prezzo).
SELECT Tipo, MIN(Prezzo) AS Prezzo
FROM Articolo
GROUP BY Tipo;


# 5) Una selezione aggregata su raggruppamenti con condizioni (es. dipartimenti la cui somma degli stipendi dei dipendenti è > 100k).
# Selezionare gli ordini tra quelli effettuati in contanti, il cui totale di ogni ordine è maggiore di 30€.
SELECT O.ID_ordine AS ID, M.ID_pagamento AS Contanti, SUM(A.prezzo * C.num_articolo) AS Prezzo_completo
FROM Ordine O, Metodo_pagamento M, Composizione C, Articolo A
WHERE O.ID_ordine = C.ID_ordine AND C.ID_articolo = A.ID_articolo AND  O.ID_Pagamento = M.ID_Pagamento 
AND M.ID_pagamento = 1 
GROUP BY O.ID_ordine
HAVING SUM(A.prezzo * C.num_articolo) > 30;


# 6) Una selezione aggregata su raggruppamenti con condizioni che includano un’altra funzione di raggruppamento (es. dipartimenti la cui somma degli stipendi è la più alta).
# Il magazzino la cui somma dei prezzi degli articoli è la più alta.
WITH Somma (ID, Prezzo_totale) AS(
	SELECT M.ID_magazzino, SUM(A.Prezzo*A.quantita)
	FROM Articolo A, Magazzino M
	WHERE A.ID_magazzino = M.ID_magazzino
	GROUP BY M.ID_Magazzino
)
SELECT  ID, Prezzo_totale
FROM Somma
WHERE Prezzo_totale = (SELECT MAX(Prezzo_totale)
						FROM Somma
                        );


# 7) Una selezione con operazioni sugli insiemi (IN oppure NOT IN).
# Selezionare i magazzini che contengono sia maglie che pantaloni.
SELECT DISTINCT M.ID_Magazzino
FROM Articolo A join Magazzino M on (A.ID_magazzino = M.ID_magazzino)
WHERE A.Tipo = "Maglia" AND  M.ID_magazzino IN (SELECT M.ID_magazzino
												FROM Articolo A join Magazzino M on (A.ID_magazzino = M.ID_magazzino)
												WHERE A.Tipo = "Pantalone");


# 8) Uso dell’operatore quoziente.
# Gli ordini che contengono tutti gli articoli.
SELECT D.Cognome, D.Nome, O.*
FROM Ordine O, Dati_Cliente D
WHERE O.Codice_Cliente = D.Codice_Cliente
AND NOT EXISTS ( SELECT *
				FROM Articolo A
				WHERE NOT EXISTS (SELECT *
								  FROM Composizione Cs
                                  WHERE O.ID_Ordine = Cs.ID_Ordine AND Cs.ID_Articolo = A.ID_Articolo
                                 ));