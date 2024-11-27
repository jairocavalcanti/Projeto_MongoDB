Instruções para Configurar e Executar o Projeto no Linux

Pré-requisitos
1. Java 17: Certifique-se de que o Java Development Kit (JDK) 17 está instalado:
   sudo apt update
   sudo apt install openjdk-17-jdk
   java -version

2. Apache Maven: Instale o Maven para gerenciar as dependências e compilar o projeto:
   sudo apt install maven
   mvn -version

3. MongoDB: Configure o banco de dados MongoDB:
   - Instale o MongoDB:
     sudo apt update
     sudo apt install -y mongodb
   - Inicie o serviço:
     sudo systemctl start mongodb
     sudo systemctl enable mongodb
   - Verifique se o MongoDB está rodando:
     systemctl status mongodb

Passo a Passo para Executar o Projeto
1. Clone ou baixe o projeto:
   Certifique-se de que o projeto está acessível no diretório local:
   git clone <URL_DO_REPOSITORIO>
   cd crud

2. Configure o application.properties:
   Adicione ou edite o arquivo src/main/resources/application.properties para configurar a conexão com o MongoDB:
   spring.data.mongodb.uri=mongodb://localhost:27017/seu_banco_de_dados
   spring.data.mongodb.database=seu_banco_de_dados

3. Baixe as dependências e compile o projeto:
   Execute o Maven para baixar todas as dependências especificadas no arquivo pom.xml e compilar o projeto:
   mvn clean install

4. Inicie o servidor Spring Boot:
   Execute o comando para iniciar a aplicação:
   mvn spring-boot:run
   O servidor estará acessível em http://localhost:8080

5. Testando o Projeto:
   - Verifique os endpoints disponíveis usando ferramentas como Postman, cURL ou navegador.
   - Exemplo de teste básico com cURL:
     curl -X GET http://localhost:8080/eventos
