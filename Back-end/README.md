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

#### End-Points
##### 1. Peticion POST
```bash
   /horarios
```
Se debe pasar un body de la forma:
```bash
   {"horas": //Horas libres,
      "lista": //Lista de materias elegidas}
 ```
Las horas libres son de la forma
```bash
   {
      "lunes":[//Horas en formato 24horas "7:00" o "15:00"],
      "martes":[//lo mismo],
      ...
      "sabados":[//Este seria el ultimo]
   }
```
La lista de materias es de la forma
```bash
   [
      {"materia":"//AppWeb","profesores":[//nombres en str],"codigos":[//puede ser una lista vacia]},
      ...
      {//**Se pueden poner cuantas materias se quiera}
   ]
   ```
#### Respuesta 1
```bash
   {
      "origenTexto":{
         "listMates":[],
         "horariosTexto":[]
      },
      "mejoresTextos":{//Todos son iguales},
      "interTextos":{},
      "malosTextos":{},
   }
```
Para lista mates, es una lista de listas con objetos con los siguientes datos
```bash
   {
      "materia": //nombre,
      "profesor": //Nombre profesor,
      "codigo": //codigo de clase
   }
```
Para horarios texto, es un array ordenado con la forma de la semana 12filas x 6columns:
```bash
   Se van pintando por filas de la semana
```


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
