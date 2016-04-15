select R.CodeReservation, R.NbPersonnes, R.Jour, R.Heure, R.Minutes, R.NomService, C.NomClient, C.NumTel
from Reservation R, Client C
where R.CodeClient = C.CodeClient;

select T.CodeTable, T.NbPlace0, T.NbPlace1, T.NbPlace2, T.Localisation
from Occupe O, TableRepas T
where (CodeReservation = 8)
and O.CodeTable = T.CodeTable;
