Dépôt Git du projet pact du groupe 2.3 de Télécom Paris, année 2019-2020.

Il s'agit d'une application mobile. Pour plus d'informations, voir le rapport.

Le répertoire `rapport` contient le rapport d'avancement et est structuré en sous dossiers.
Le répertoire `client` contient le code source l'application android codée en Java.
Le répertoire `server` contient le code source du service web codé en Java en production sur le serveur.

Adresse du serveur: izeat.r2.enst.fr

**Structure des URI du web service**    
**Root:** http://izeat.r2.enst.fr/ws/Izeat/webresources       
**Accueil:** /service   
**Infos produit:** /product/{code barre du produit}     
**Recherche de produits:** /product/search/{mots clés}      
**Utilisateurs:** /user/{identifiant utilisateur}       

Les Scripts Pythons utilisés pour la reconnaissance d'image sont localisés dans `server/engine`.

Le rapport d'avancement est rédigé  en utilisant le langage [**AsciiDoc**](http://asciidoc.org/).

Le document final sera généré en utilisant l'outil [Asciidoctor](http://asciidoctor.org/) qui supporte les même extensions que GitLab (pour les équations par exemple).
Un résumé de la syntaxe supportée est accessible [ici](http://asciidoctor.org/docs/asciidoc-syntax-quick-reference/).

