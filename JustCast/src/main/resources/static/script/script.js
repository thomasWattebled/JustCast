function confirmLogout() {
            var confirmation = confirm('Êtes-vous sûr de vouloir vous déconnecter ?');
            if (confirmation) {
                window.location.href = 'deconnexion'; // Redirige vers la page de déconnexion
            }
        }
        
function eventDC() {  
         window.location.href = 'evenementDC';          
        }
        
function creationEvenement() {  
         window.location.href = 'creationEvenementDC';          
        }
        
function modificationEvenement() {  
         window.location.href = 'myEvenementDC';          
        }

function mesInformations() {  
    window.location.href = 'informations';          
    }

function emploiDuTemps () {  
    window.location.href = 'emploiDuTemps';          
    }
    
function lesAnnonces() {
	window.location.href = 'annonces';
	}
	
function mesPostulations(){
	window.location.href = 'postulationsActeur';
}

function listPostulation(x) {
	console.log(1);
}

function handleFilmClick(button) {
            var filmId = button.getAttribute('data-film-id') +'';
            console.log('ID du film sélectionné:', filmId);
            window.location.href='/application/detailsCasting/'+filmId;
        }

function envoieForm(idForm) {
	const form = document.getElementById(idForm);
	form.submit();
}

function gererActeurs(){
	window.location.href = 'gererActeur';
}

function trouverActeur(){
	window.location.href = 'trouverActeur';
}


function voirActeur(){
	window.location.href = 'voirActeur';
}

function chargerSemaine(semaine) {
    window.location.href = semaine;
}
