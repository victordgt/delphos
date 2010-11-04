1)GIT:

git init
git config --global user.name "usuario"
git config --global user.mail "usuario@gmail.com"

2)para baixar projeto pela primeira vez:
git clone git@github.com:victordgt/delphos.git

3)para receber atualizações do projeto:
git pull origin master

4)para enviar atualizações para o repositório GITHUB:
git add .
git commit (abre o editor vim, onde deve inserir o comentário e teclar esc e depois  :wq e enter)
git push origin master

5)ao baixar, instalar o GOOGLE APP ENGINE SDK:
mvn gae:unpack

6)para instalar os pacotes necessários do repositório:
mvn install

7)caso falhe algum jar do repositório(exemplo: jdo):
mvn install:install-file -DgroupId=javax.jdo -DartifactId=jdo2-api -Dversion=2.3-ec -Dpackaging=jar -Dfile=src/main/resources/jdo2-api-2.3-ec.jar

8)para criar um projeto no eclipse:
mvn eclipse:eclipse

9)para executar o projeto:
mvn gae:run

10)para fazer o deploy:
mvn gae:deploy

11) LINKS:
* Tortoisegit para windows - http://code.google.com/p/tortoisegit
* Git para windows - http://code.google.com/p/msysgit/
* Esqueleto wicket para google app engine - http://code.google.com/p/wicket-gae-template/
* Arquétipos para google app engine em java - http://code.google.com/p/maven-gae-plugin/
* Google App Engine para java - http://code.google.com/appengine/downloads.html#Google_App_Engine_SDK_for_Java
* Repositório compartilhado GITHUB do projeto - http://github.com/victordgt/delphos
* Aplicação publicada no GAE - http://delphosconhecimento.appspot.com