-- Test des différentes requetes

select R.CodeReservation, R.NbPersonnes, R.Jour, R.Heure, R.Minutes, R.NomService, C.NomClient, C.NumTel
from Reservation R, Client C
where R.CodeClient = C.CodeClient;

select T.CodeTable, T.NbPlace0, T.NbPlace1, T.NbPlace2, T.Localisation
from TableRepas T
minus ( select T2.CodeTable, T2.NbPlace0, T2.NbPlace1, T2.NbPlace2, T2.Localisation
	from TableRepas T2, Occupe O, Reservation R
	where T2.CodeTable = O.CodeTable
	and O.CodeReservation = R.CodeReservation
	and R.Jour = to_date('2016-04-30', 'YYYY-MM-DD')
	and R.NomService = 'SOIR' );

select SeqClient.currval from Dual;



select A.NomArticle, A.TypeArticle, A.NomSpecialite
from Article A, ContientPlat Pl
where A.NomArticle = Pl.NomArticlePlat
	and Pl.NomArticleMenu = 'GROSSE FAIM';

select A.NomArticle, A.TypeArticle, A.NomSpecialite
from Article A, ContientAutreArticle Au
where A.NomArticle = Au.NomArticleAutre
	and Au.NomArticleMenu = 'GROSSE FAIM';

select distinct A.TypeArticle
from Article A, ContientAutreArticle Au
where A.NomArticle = Au.NomArticleAutre
	and Au.NomArticleMenu = 'GROSSE FAIM';

select A.NomArticle, A.TypeArticle, A.NomSpecialite
from Article A, EstElement El
where A.NomArticle = El.NomArticle
and El.CodeCarte = 3;

select S.CodeCarte
from Reservation R, Service S
where R.Jour = S.Jour
and R.NomService = S.NomService
and R.CodeReservation = 4;

