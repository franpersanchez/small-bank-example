# Small Bank Example

Este es un ejemplo de aplicación para la gestión de usuarios y cuentas, que incluye operaciones de depósito, transferencia y asignación de cuentas.  
La aplicación utiliza **Spring Boot**, **Java 21**, y está preparada para ejecutarse en **Docker**, utilizando como persistencia una instancia de **PostgreSQL** mediante **docker-compose**.

## Características

- Gestión de usuarios (creación, consulta).
- Gestión de cuentas (creación, asignación, consultas, depósitos, transferencias).
- Persistencia en base de datos (configurada con JPA).
- Arquitectura hexagonal, principios DDD.


## Requisitos

- **Docker** instalado.

## Instalación y ejecución

1. Clona este repositorio:
   
```bash
   git clone https://github.com/franpersanchez/small-bank-example.git
   cd small-bank-example
````
2.  Es necesario hacer build con maven para que docker pueda levantar la aplicación.
````angular2html
mvn clean package
````
3. Levantar docker-compose
````
docker-compose build --no-cache
````
4. 
````angular2html
docker-compose up    
````
## Endpoints principales
## Usuarios
- POST /users: Crear un usuario.
- POST /users/{id}/{wallet}: Asignar una cuenta a un usuario.
- GET /users: Listar todos los usuarios.
- GET /users/{id}: Obtener un usuario por ID.

## Cuentas (wallets)
- POST /wallets: Crear una nueva cuenta.
- POST /deposit/{id}/{amount}: Depositar dinero en una cuenta.
- POST /transfer/{idOrigin}/{amount}/{idDestination}: Transferir dinero entre cuentas.
- GET /wallets: Listar todas las cuentas.
- GET /wallets/{id}: Consultar el saldo de una cuenta.


Francisco Pérez Sánchez
