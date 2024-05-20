function confirmLogout() {
            var confirmation = confirm('Êtes-vous sûr de vouloir vous déconnecter ?');
            if (confirmation) {
                window.location.href = 'deconnexion'; // Redirige vers la page de déconnexion
            }
        }