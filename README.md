# Exemple Rest API Blog par Spring boot

## Exigence

Pour construire et exécuter l'application, vous avez besoin de:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Exécution de l'application en local
D'abord faire un git clone de l'application sur votre machine 

```shell
git clone https://github.com/ibrahimamrani/Backend-blog.git
```

Il y a plusieurs méthode pour exécuter une application Spring Boot sur votre ordinateur local,
La première méthode est d'exécuter la méthode `main` de la classe `com.example.blog.BlogApplication` à partir de votre IDE.

Vous pouvez aussi utiliser le plugin Spring Boot Maven plugin comme suit:
                          
                  
```shell
mvn spring-boot:run
```

## Outils utilisés

Les outils utilisés dans l'exemple sont :

- [Spring Boot](https://spring.io/projects/spring-boot) 
- [Lombok](https://projectlombok.org/)
Permet de ne plus écrire les méthode getter, setter et ou equals.
- [Swagger](https://swagger.io/) 
Framework Open-Source aide a concevoir, créer, documenter et utiliser des services Web RESTful.
- La base de données utilisée est H2, base de données mémoire. 
Le fichier <b>data.sql</b> permet d'inséré des posts dans la base des données au démarrage de l'application.

## Architecture

model — contient les entities;

repository — contient les classes qui communiquent avec la base de données;

service — le logic métier;

resource — contient les controllers.

application.yml — contient des propriétés de paramètrage de l'application. Vous pouvez définir le port par défaut du serveur, le chemin de contexte du serveur, les URL de base de données, etc. dans ce fichier.;
 
test/ - contient les tests unitaires et les tests d'intégration

pom.xml - contient les informations nécessaires à Maven pour traiter le projet 

## Documentation API
- [Voir page Swagger de l'application](http://localhost:8080/swagger-ui.html) 