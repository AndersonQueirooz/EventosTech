# EventosTech

API de um projeto de eventos de tecnologia, feita em Java com Spring Boot.

## Stack

- Java 17 e Spring Boot 4
- Spring Data JPA com PostgreSQL
- Flyway para as migrations do banco
- MapStruct e Lombok
- AWS SDK para S3
- JUnit, Mockito e H2 nos testes, JaCoCo para cobertura
- Docker

## Integração com S3

O projeto usa o SDK da AWS para S3. Para isso funcionar localmente, você precisa de um bucket e de credenciais com permissão nele.
