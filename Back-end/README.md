# Backend del Proyecto de Creación de Horarios

Este README describe la parte de **backend** del proyecto de creación de horarios. El backend está dividido en dos partes principales: una desarrollada en **Java** utilizando **Spring Framework** y otra que puede ser implementada en cualquier lenguaje de programación, manteniendo una comunicación clara mediante **endpoints REST**.

## Tecnologías:

1. **Java con Spring Boot**:

   - Framework principal para la creación del backend.
   - Permite una rápida configuración de APIs RESTful, manejo de dependencias, y gestión de seguridad.

2. **Otra parte en un lenguaje flexible**:
   - Esta sección puede ser implementada en cualquier lenguaje que se integre fácilmente mediante API REST (por ejemplo: Node.js, Python, etc.).
   - La idea es permitir flexibilidad en esta parte del desarrollo.

## Arquitectura

### 1. **Java + Spring Boot**

- **Controladores**: Gestionan las solicitudes HTTP entrantes.
- **Servicios**: Encargados de la lógica del negocio.
- **Repositorios**: Comunican la lógica del negocio con la base de datos.
- **Entidades**: Representan los datos que serán persistidos en la base de datos.

### 2. **API Externa**

- Implementación flexible que puede encargarse de funcionalidades como el manejo de tiempos o integración con servicios externos.
- Se comunicará con el backend de Spring a través de **endpoints REST**.

## Endpoints:

### **Horario Management (Spring Boot)**

-**Vacio**

### **API Externa**

-**Vacio**

## Requisitos para el Backend:

1. **Java 11+**: Asegúrate de tener la versión correcta instalada.
2. **Spring Boot**: Configura tu entorno para iniciar una aplicación Spring.
3. **Base de Datos**: Se recomienda utilizar una base de datos relacional como **PostgreSQL** o **MySQL**.
4. **API REST**: Diseño e implementación de endpoints que faciliten la comunicación entre el backend y otros módulos.

## Instalación:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/maosuarez/CREACION-HORARIOS.git
   ```
