# Api-Tecno-Java
 API Rest JAva creada para entregar como final de la materia Tecnologia Java
Se utilizo el framework Springboot y tiene la UI de Swagger.
La api tiene funcionalidades para manejar los siguientes aspectos:
1) Usuarios: Posee un CRUD de usuarios y funcionalidades de Login y deshabilitar (no eliminar) usuarios
2) Productos: Posee un CRUD de Productos
3) Pedidos: Posee un CRUD de Pedidos
4) Envios: Posee un CRUD de Envios
5) Transferencias: El sistema cuenta con una funcionalidad de dinero en cuenta, que permite unicamente transferir dinero y consultar las transferencias segun el rol del usuario.
6) Roles: Crear y ver roles
7) Reportes: Permite visualizar reportes de ventas totals y por productos. Y genera un excel
8) Factuas: Generador de PDF de facturas. Se puede personalizar segun el template.

La API es un monolito que posee varias funcionalidades integradas que pueden ser desacopladas para tener una arquitectura de microservicios y darle mas escalabilidad.
