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