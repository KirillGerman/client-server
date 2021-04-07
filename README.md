1 config.properties - db.url, адрес хоста 

2 resources скрипты для бд (1_create_database.sql, 2_create_clients_table.sql)

3 mvn clean compile assembly:single

4 cd target

5 java -jar client-server-1.0-SNAPSHOT-jar-with-dependencies.jar 8080

