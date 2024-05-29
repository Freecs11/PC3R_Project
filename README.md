# Virtual Farm 🌾

Bienvenue sur **Virtual Farm**, une application de jeu en ligne développée dans le cadre du cours PC3R (Programmation Concurrente, Réactive, Répartie et Réticulaire) à Sorbonne Université pour l'année scolaire 2023-2024.

## Aperçu du projet

Virtual Farm est un jeu en ligne où les joueurs peuvent générer et explorer des donjons en utilisant des animaux de compagnie virtuels. Ces animaux peuvent être achetés, vendus et utilisés pour diverses activités en jeu. L'application intègre des technologies web modernes et des concepts avancés de programmation pour assurer évolutivité et sécurité.

## Technologies utilisées

- **Backend** : Jakarta Servlet, Tomcat
- **Frontend** : ReactJS, Vite, MUI
- **Base de données** : Hibernate JPA, PostgreSQL
- **APIs externes** : API de la géolocalisation , openstreetmap et WeatherAPI, et autres pour les attributs des donjons
- **Middleware** : Authentification et gestion des CORS personnalisées

## Fonctionnalités

- **Authentification des utilisateurs** : Inscription et connexion sécurisées avec gestion des sessions.
- **Gestion des animaux de compagnie** : Créer, acheter, vendre et gérer des animaux de compagnie virtuels.
- **Exploration des donjons** : Donjons générés dynamiquement en fonction de la géolocalisation de l'utilisateur et des conditions météorologiques.
- **Combat en temps réel** : Participer à des combats dans les donjons en utilisant les attributs et les stratégies des animaux de compagnie.

## Commencer

### Prérequis

- JDK 11+
- Node.js 14+
- PostgreSQL
- Apache Tomcat 9+

### Installation

1. **Cloner le dépôt** :
    ```bash
    git clone https://github.com/Freecs11/PC3R_Project.git
    cd PC3R_Project
    ```

2. **Configuration du backend** :
    - Configurez votre base de données MySQL et mettez à jour le fichier `application.properties` avec vos identifiants de base de données.
    - Construisez et lancez le serveur backend :
      ```bash
      ./mvnw clean install
      ```
   - Déployez le fichier WAR généré (`target/VFarm-1.0-SNAPSHOT.war`) sur votre serveur Tomcat.


3. **Configuration du frontend** :
    - Naviguez vers le répertoire frontend et installez les dépendances :
      ```bash
      cd frontend
      npm install
      npm start
      ```

### Exécution de l'application

- Le serveur backend fonctionnera sur `http://localhost:8080`.
- Le serveur frontend fonctionnera sur `http://localhost:5173`.

## Endpoints de l'API

### Authentification des utilisateurs
- **POST /user/signup** : Inscrire un nouvel utilisateur.
- **POST /login** : Connecter un utilisateur existant.

### Gestion des animaux de compagnie
- **GET /api/v1/pet/all** : Obtenir tous les animaux de compagnie.
- **POST /api/v1/pet** : Créer un nouvel animal de compagnie.
- **PUT /api/v1/pet/{id}** : Mettre à jour les détails d'un animal de compagnie.
- **DELETE /api/v1/pet/{id}** : Supprimer un animal de compagnie.

### Exploration des donjons
- **GET /api/v1/dungeons** : Obtenir les donjons à proximité.

### Gestion des combats
- **POST /api/v1/DungeonService/{id}/combat** : Participer à un combat dans un donjon.


## Contributeurs

- **Rachid Bouhmad**
- **Elhadj Alseiny Diallo**
- **Do Truong Thinh Truong**
