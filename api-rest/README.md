# API Pichincha

## Descripción
API RESTful para la gestión de clientes, cuentas y movimientos de una entidad bancaria. Este proyecto implementa operaciones CRUD y proporciona funcionalidades adicionales como el registro de movimientos y generación de reportes.

## Características
- Manejo de verbos HTTP: GET, POST, PUT, DELETE.
- Entidades gestionadas:
  - **Persona**: nombre, género, edad, identificación, dirección, teléfono.
  - **Cliente**: hereda de Persona, incluye clienteID, contraseña, estado.
  - **Cuenta**: número de cuenta, tipo de cuenta, saldo inicial, estado.
  - **Movimientos**: fecha, tipo de movimiento, valor, saldo.
- Funcionalidades:
  - CRUD para Cliente, Cuenta y Movimiento.
  - Registro y validación de movimientos en cuentas.
  - Generación de reportes de estado de cuenta por rango de fechas y cliente.
- Pruebas unitarias e integración.
- Dockerización para fácil despliegue.

## Requisitos
- Java 17
- Maven 3.6+
- Docker

## Instalación y Ejecución
Sigue los siguientes pasos para configurar y ejecutar la API:
- Ubicar la ruta raiz del proyecto donde se encuentra el archivo docker.
- mvn clean install
- docker build -t apirest:latest .
- docker run -p 8080:8080 apirest:latest

### Clonar el repositorio
```bash
git clone https://github.com/ricardo1021/api-pichincha.git
cd api-pichincha
