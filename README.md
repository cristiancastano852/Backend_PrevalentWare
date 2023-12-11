# Backend_PrevalentWare

## Prueba técnica Ingeniero Backend

#### Cristian Alexander Castaño Montoya

#### E-mail: cristiancastano852@gmail.com

#### LinkedIn: https://www.linkedin.com/in/cristiancastano852/

### Entorno de desarrollo:

- Se utilizo Spring Boot version 3.2.0 con maven y java 17.
- Desarrollado en Windows y con el editor visual studio code.

### Decisiones y justificaciones:

- Para las pruebas unitarias se uso JUnit, disponibles en la rama feature/unit_tes_junit.
- Se uso ramificación para las diferentes features del proyecto para separar funcionalidades y versiones.
- Las ramas fueron protegidas y los merge hechos con pullRequest.
- Para la autenticacón basada en roles, no se utilizo Spring Security, pues en la version 3.2.0 se implementa de una forma distinta, ya que no es compatible con WebSecurityConfigurerAdapter por lo que se se decide usar un archivo propio de configuración AuthorizationUtil.java para validar los tokens con los roles y evitar las configuraciones por temas de tiempo.
- Algunas entities utilizan @Data (lombok.Data) para crear getters, setters, etc. En otras se crearon manualmente para demotrar ambas funcionalidades.
- El metodo que usa raw SQL es el de obtener los tres usuarios con más registros en UserMonitoring, disponible en el archivo IUserMonitoringRepository.java.
- Se usa physical_naming_strategy propio, se encuentra en el archivo MyPhysicalNamingStrategy.java, esto para evitar los conflictos con la notación por defecto de hibernate.

### Endpoints:

#### Los endpoints se puedes descargar desde el archivo Insomnia_endpoints_prevalentWarePR.json y abrir con Insomnia.

#### Todos los endpoints requieren en el Header el campo "Authorization" con el token del usuario.

#### Todos los endpoints tiene paginación, menos los que solo regresan un dato. Como obtener el usuario por email.

- /api/user/lists?page=0&size=10, obtiene todos los usuarios del sistema, si el usuario es un User Role, regresa solo los datos de ese user.
- /api/user/search, si el usuario es un Admin o Manager regresa los datos de un usuario con el correo electronico como parametro, pero si es un User solo regresa los datos si es su propio correo.
- /api/country/lists?page=0&size=10, Obtener todos los países (Countries) | Admin o Manager
- /api/monitoring/user?email=michael.lopez@test.com&startDate=2023-02-12T00:00:00&endDate=2023-11-28T23:59:59, Obtener UserMonitoring de un usuario en un rango de tiempo | Admin o User
- [ raw SQL ] /api/monitoring/top-users?startDate=2023-12-01T00:00:00&endDate=2023-04-28T23:59:59, Obtener los tres usuarios con más registros en UserMonitoring | Admin
- Adicional, se crearon otros endpoints para consultar información general. Se pueden ver en el archivo en Insomnia.

### Instrucciones para ejecutar la solución | Docker - Docker compose

En la carpeta raiz del proyecto se encuentran los dos archivos "dockerfile" y "docker-compose.yml"

1. En el archivo docker-compose.yml se debe agregar las variables de entorno de la base de datos:
   - DATABASE_URL=......
   - DATABASE_USERNAME=.....
   - DATABASE_PASSWORD=.....
2. Ejecutar el comando "./mvnw clean package -DskipTests" para crear el archivo .jar en la carpeta target.
3. Ejecutar el comando "docker-compose build app_pr" para construir la imagen docker.
4. Ejecutar el comando "docker-compose up" para correr el contenedor a partir de la imagen.
5. Todos listo! la aplicación se encuentra corriendo el el puerto 8080.

- Si no desea usar Docker puede simplemente ejecutar desde la consola el comando "mvn spring-boot:run" desde la raiz del proyecto. Debe de agregar las variables de entorno en el archivo aplication.properties.

### Propuesta de despliegue en AWS:

### - Implementación con AWS Lambda:

- La autenticación con API Gateway y VPC, para exponer endpoints que activen las funciones lambda. Para limitar el acceso a servicios externos y asegurar una conexión más controlada.
- Amazon Cognito en VPC: Utiliza Amazon Cognito dentro de una VPC para gestionar sesiones de usuario y tokens de forma segura.
- CloudWatch para monitorizar el rendimiento y los registros de las funciones Lambda.
- AWS Lambda con autoscaling para manejar automáticamente la demanda de tráfico.
- CloudFront para distribuir la API a través de su red de ubicaciones de borde, así reducir la latencia y mejorar la velocidad de respuesta a nivel global (suponiendo que la aplicación se usa alrededor del mundo).
- Configuración roles y políticas IAM para otorgar permisos específicos a las funciones Lambda y restringir el acceso a recursos.

De esta forma y con estos recursos haría la implementación en Lambda, puede variar según el trafico que se espera, los tipos de usuarios, los paises en los que va a estar disponible, etc.

### - Implementación con AWS ECS

ECS es más cercana a la aplicación si se desea tener contenerizada.

- Clúster ECS utilizando EC2 para un mayor control.
- Amazon ECR para almacenar nuestra imagen o imagenes de Docker.
- Load Balancer (ALB) para distribuir el tráfico entre los contenedores ECS.
- Autoscaling para escalar automáticamente el número de contenedores en función de la carga.
- AWS VPC para aislar tu entorno y controlar las conexiones de red.
- Configurar CloudWatch para monitorear la salud de tus contenedores ECS y la utilización de recursos.
- Igualmente implementando CloudFront y IAM para los roles.

Ambas soluciones fueron pensadas teniendo en cuenta que la base de datos ya esta desplegada y no hay que tenerla en cuenta en esta arquitectura.

### Mejoras futuras:

Estas son algunas features para mejorar el aplicativo en un futuro.

- Añadir documentación de la API con Swagger o Springfox.
- Implementar más metodos para los endpoints según los nuevos requerimientos.
- Hacer un despliegue real en AWS.
- Culminar las pruebas unitarias en su totalidad.
