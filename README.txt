Xavier Laperriere (20157146)
Alban Guyon (20206315)

https://github.com/3nabla3/TP1_3913

Testé sur OS: Ubuntu 22.04.3 LTS

Prérequis
```shell
sudo apt update
sudo apt install -y git openjdk-11-jdk maven
git clone https://github.com/3nabla3/TP1_3913.git
cd TP1_3913
```

Pour générer les jar dans le dossier artifacts/
```shell
mvn package
mkdir artifacts
mv target/{tloc,tassert,tls,tropcomp}.jar artifacts/
```

Pour rouler les jar
```shell
java -jar artifacts/tloc.jar
java -jar artifacts/tassert.jar
java -jar artifacts/tls.jar
java -jar artifacts/tropcomp.jar
```


Informations supplémentaires :

Programmation en paire : mardi 3 octobre 2023 et jeudi 5 octobre 2023
Utilisation de ChatGPT pour génération de code. Voir fichier GPT prompts.yml
