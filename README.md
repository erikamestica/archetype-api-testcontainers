# Archetype with test containers
This is an archetype of a simple project with TestContainers perfect to start
developing an app with TDD, is a simple API REST with a DB connection.

## Resources
- Dockerized PostgresSQL DB in /compose.yaml
- Default run config for IntelliJ in /.run/Run.run.xml
- Example DB initialization with flyway
- API First

## How to run
- At root dir run this to start the dockerized DB:
  > docker-compose up
- Run app with the default intellij configuration or with the following command:
  > mvn spring-boot:run

## Next steps
- Multiple adapters for different DB, APIs and Kafka

## License
This project is under MIT license - check the [LICENSE](./LICENSE) file for more information.