Le répertoire `rapport` contient le rapport d'avancement et est structuré en sous dossiers.
Le répertoire `client` contient le code source l'application android en Java.
Le répertoire `server` contient le code source de l'application en Java sur le serveur.
Adresse du serveur: izeat.r2.enst.fr

**Structure des URI du web service**
Root: **http://izeat.r2.enst.fr/ws/Izeat/webresources**
Accueil: /service
Infos produit: /product/{code barre du produit}
Recherche de produits: /product/search/{mots clés}
Utilisateurs: /user/{identifiant utilisateur}




Le rapport d'avancement est rédigé  en utilisant le langage [**AsciiDoc**](http://asciidoc.org/).

Le document final sera généré en utilisant l'outil [Asciidoctor](http://asciidoctor.org/) qui supporte les même extensions que GitLab (pour les équations par exemple).
Un résumé de la syntaxe supportée est accessible [ici](http://asciidoctor.org/docs/asciidoc-syntax-quick-reference/).

