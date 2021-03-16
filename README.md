# Study Task List

В учебном проекте представлена реализация таск листа:
- подключение клиета
- добавление задачи в список
- удаление задачи
- обовление задачи
- вывод всего списка задач
- вывод списка не решеных задач
- удаление клиента


В учебных целях реализовано хранение данных:
- в InMemoryDataStorage (MAP без использования базы данных)
- в JDBCDataStorage (исползуя JDBC connection с прямыми SQL запросами (H2DB))
- в JpaDataStorage (используя Spring Data (H2DB))

Из интересного:
- сборка Maven
- Реализован REST API в AppController
- для передачи сообщений использовался патерн Команда
- реализован LockProvider для предотвращения состояния гонки
  с истользованием ReentrantLock
- использование catched исключений
- использовано Dependency Injection, IoC
- Spring Framework(mvc, di, IoC, data, jdbc template)
- JUnit тесты





