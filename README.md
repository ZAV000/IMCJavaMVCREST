# IMCJavaMVCREST

Aplicación web desarrollada en Java para registrar usuarios, iniciar sesión, calcular el índice de masa corporal (IMC) y consultar el historial de mediciones.

## Tecnologías utilizadas

- Java
- Maven
- JSP
- Servlets
- JDBC
- MySQL
- Servicios REST
- JSON y XML
- Arquitectura MVC

## Funcionalidades principales

- Registro de usuario con validación de edad y estatura.
- Inicio de sesión obligatorio para calcular IMC.
- Cálculo de IMC a partir del peso y la estatura registrada.
- Clasificación del resultado de IMC.
- Almacenamiento de usuarios y cálculos en base de datos.
- Consulta del historial desde la base de datos mediante servicio REST.
- Manejo de excepciones para respuestas JSON y XML.

## Base de datos

La base de datos utilizada es `imc_app`.

Tablas principales:

- `usuarios`
- `calculos_imc`

El script SQL se encuentra en:

`src/main/resources/db.sql`

## Arquitectura

El proyecto usa el patrón Modelo-Vista-Controlador:

- Modelo: clases de dominio y servicios.
- Vista: páginas JSP.
- Controlador: servlets y recursos REST.
- Persistencia: clases DAO para conexión con MySQL.

## Observación del avance

Se implementó la observación recibida en el avance: después de registrar información o guardar un cálculo, la aplicación vuelve a consultar la base de datos para recuperar los datos persistidos y mostrarlos en la vista.

## Repositorio

El proyecto cuenta con los branches:

- master
- develop
- feature/file
- feature/directory
- feature/notify
- feature/user

También cuenta con el tag de versión estable:

- v1.0.0
