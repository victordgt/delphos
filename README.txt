1)GIT:

git init
git config --global user.name "usuario"
git config --global user.email "usuario@gmail.com"

2)para baixar projeto pela primeira vez:
git clone git@github.com:victordgt/delphos.git

3)para receber atualiza��es do projeto:
git pull origin master

4)para enviar atualiza��es para o reposit�rio GITHUB:
git add .
git commit (abre o editor vim, onde deve inserir o coment�rio e teclar esc e depois  :wq e enter)
git push origin master

5)para ignorar arquivos no GIT:
criar arquivo .gitignore no raiz
colocar as extens�es a serem ignoradas, por exemplo "target/*". Isto significa todos os arquivos na pasta target
git add .
git commit
git push origin master


6)ao baixar, instalar o GOOGLE APP ENGINE SDK:
mvn gae:unpack

7)para instalar os pacotes necess�rios do reposit�rio:
mvn install

8)caso falhe algum jar do reposit�rio(exemplo: jdo ou datanucleus):
mvn install:install-file -DgroupId=javax.jdo -DartifactId=jdo2-api -Dversion=2.3-ec -Dpackaging=jar -Dfile=lib/jdo2-api-2.3-ec.jar
mvn install:install-file -DgroupId=com.google.appengine.orm -DartifactId=datanucleus-appengine -Dversion=1.0.5 -Dpackaging=jar -Dfile=lib/datanucleus-appengine-1.0.5.jar

9)para criar um projeto no eclipse:
mvn eclipse:eclipse

10)para executar o projeto:
mvn gae:run

11)para fazer o deploy:
mvn gae:deploy

12)para executar o console de administra��o de banco de dados no localhost do GAE:
http://localhost:8080/_ah/admin

13) LINKS:
* Tortoisegit para windows - http://code.google.com/p/tortoisegit
* Git para windows - http://code.google.com/p/msysgit/
* Esqueleto wicket para google app engine - http://code.google.com/p/wicket-gae-template/
* Arqu�tipos para google app engine em java - http://code.google.com/p/maven-gae-plugin/
* Google App Engine para java - http://code.google.com/appengine/downloads.html#Google_App_Engine_SDK_for_Java
* Reposit�rio compartilhado GITHUB do projeto - http://github.com/victordgt/delphos
* Aplica��o publicada no GAE - http://delphosconhecimento.appspot.com
