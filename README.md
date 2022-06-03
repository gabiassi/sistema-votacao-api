## Pré-requisitos

- Java 8+
- Banco PostgreSQL:
    - usuário: postgress senha: 123 - configurados no `application.properties` ;
    - utilizado porta default do postgres 5432;
    - database `votacao`;
- Executar classe `Application`.

## Banco de dados

- Foi utilizado script `schema.sql` para criação das tabelas ao invés de o próprio Hibernate criá-las para garantir a estrutura do banco. Também poderiamos utilizar o flyway para versionamento de scripts.
- Utilizei os construtores dos Controller's no lugar de Autowired por questões de performance ao subir a aplicação
- Para campos que seriam booleanos (Sim/Não) foi utilizado um inteiro a fim de permitir portabilidade para
  bancos sem tipo de dados booleano.

## Framework e Bibliotecas

- **Spring Boot**: Framework completo para fácil desenvolvimento.
- **ModelMapper**: Para conversão de DTOs para Objetos do banco para não expor a estrutura da tabela.
- **Swagger**: Para documentação e testes de endpoints.
- **Maven**: Para solução de dependências e build.
- **Hibernate**: Ferramenta ORM.
- **Lombook**: Para deixar o código mais limpo.

## API

Documentação dos endpoins: http://localhost:8080/swagger-ui/index.html
Versionamento com a estratégia `path`, onde a versão fica na própria URL. Vejo como uma das formas mais fáceis 
pra quem usa saber qual versão está usando.





