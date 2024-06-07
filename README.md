Pour lancer le projet, il faut démarrer les 4 projets microservices SpringBoot à travers IntelliJ ou votre IDE préféré.

Pour les bases de données, il faut avoir installer docker :
 - Ouvrir un cmd
 - docker pull neo4j
 - docker pull postgres
 - aller dans le répertoire du projet
 - docker-compose -f  docker-compose.yml up

Pour les Appel REST via Postman :

Service User : http://localhost:8081/api/mongo/...
Service Relations : http://localhost:8081/api/neo4j/...
Services Sports & Sites : http://localhost:8081/api/postgres/...