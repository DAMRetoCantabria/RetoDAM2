# APLICACION DE GESTION DE ACTIVIDADES EXTRAESCOLARES

---

## Índice

[1. Base de Datos](#base-de-datos)  
  * Descripcion de la base de datos.
  * Imagen de E/R. [Diagrama E/R](#diagramaE/R)
  * Diagrama relacional [Diagrama relacinal](#diagramaR)
  * Archivo de importacion SQL 
  
[2. Servidores](#servidores)  
  * Servidor MariaDB.
  * Servidor web Nginx.

[3. Web](#web)  

[4. Aplicación](#aplicacion)  

[5. Conclusiones](#conclusiones)  


---

# 1. BASE DE DATOS
  ## Descripcion de la base de datos. 
   
  ### 1. Tabla Profesores
    Columnas: id,dni,nombre,apellidos,correo,contraseña,password, nivel, activo, dept_id, foto  
    Clave primaria: id

  ### 2. Tabla Cursos
    Columnas: id_curso, cod_curso, descripcion, etapa, activo  
    Clave primaria: id_curso

  ### 3. Tabla Grupos
    Columnas: id_grupo, curso, cod_grupo, num_alumnos, activo  
    Clave primaria: id_grupo

  ### 4. Tabla Departamentos
    Columnas: id_depar, codigo, nombre, jefe  
    Clave primaria: id_depar

  ### 5. Tabla Mediostransporte
    Columnas: id,nombre  
    Clave primaria: id

  ### 6. Tabla Actividades
    Columnas: id_actividad, solicitante, titulo, tipo, fini, ffin, hini, hfin, prevista, transporte_req, coment_transporte, alojamiento_req, coment_alojamiento, comentarios, estado, coment_estado  
    Clave primaria: id_actividad

  ### 7. Tabla Programadas
    Columnas: id_programada, id_actividad, solicitante, titutlo, tipo, fini, ffin, hini, hfin, prevista, transporte_req, coment_transporte, alojamiento_req, coment_alojamiento, comentarios, estado, coment_estado, empresa_transporte, precio_transporte  
    Clave primaria: id_programada, id_actividad

  ### 8. Tabla Responsables
    Columnas: id, id_actividad, id_responsable  
    Clave primaria: id, id_actividad, id_responsable

  ### 9. Tabla Participantes
    Columnas: id, id_actividad, id_participante  
    Clave primaria: id, id_actividad, id_participante

  ### 10. Tabla Responsables_programada
    Columnas: id, id_programada, id_responsable  
    Clave primaria: id, id_programada, id_responsable

  ### 11. Tabla Grupos_participa
    Columnas: id, id_actividad, id_grupo  
    Clave primaria: id, id_actividad, id_grupo

  ### 12. Tabla Cursos_participa
    Columnas: id, id_actividad, id_curso  
    Clave primaria: id, id_actividad, id_curso

  ### 13. Tabla Transporte
    Columnas: id, id_actividad, id_transporte  
    Clave primaria: id, id_actividad, id_transporte

  ### 14. Tabla Cursos_programadas
    Columnas: id, id_programada, id_curso  
    Clave primaria: id, id_programada, id_curso

  ### 15. Tabla Grupos_programadas
    Columnas: id, id_programada, id_grupo  
    Clave primaria: id, id_programada, id_grupo

  ### 16. Tabla Participantes_programada
    Columnas: id, id_programada, id_participante  
    Clave primaria: id, id_programada, id_participante

  ### 17. Tabla Imagenes
    Columnas: id, url, descripcion, programadas_id_programada  
    Clave primaria: id, programadas_id_programada

  ### 18. Tabla Transporte_programado
    Columnas: id, id_programada, id_transporte  
    Clave primaria: id, programadas_id_programada


  ## Imagen de E/R.

![Diagrama Entidad Relación](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Documentacion/EntidadRelacion.png)

  ## Diagrama relacional

![Diagrama Relacional](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Documentacion/EntidadRelacion.png)

  ## Archivo de importacion SQL 

[Script importacion BD](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Documentacion/EntidadRelacion.png)

[Script importacion BD con datos de prueba](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Documentacion/EntidadRelacion.png)

# 2. SERVIDORES

  ## SERVIDOR BASE DE DATOS MARIADB

  La elección del servidor de base de datos se realizó en base a varias consideraciones:  

    Licencia de Código Abierto: MariaDB es una base de datos de código abierto. Esto es especialmente relevante para aplicaciones educativas o institucionales, ya que ayuda a reducir los gastos. 
    
    Compatibilidad con MySQL: MariaDB es una bifurcación de MySQL, por lo que es compatible con las aplicaciones y herramientas existentes que utilizan MySQL. 

    Rendimiento y Escalabilidad: MariaDB ofrece un rendimiento sólido y una escalabilidad eficiente. Puede manejar grandes volúmenes de datos y múltiples conexiones simultáneas, lo que es crucial si la aplicación de gestión de actividades crece más de lo esperado. 

    Seguridad: MariaDB incluye características de seguridad robustas, como cifrado de datos en reposo y en tránsito, autenticación avanzada y control de acceso granular. Esto es fundamental para proteger la información sensible de los estudiantes y el personal.


  En el contexto de nuestra aplicación, hemos configurado el servidor MariaDB en una máquina virtual Alpine Linux mediante un contenedor Docker. El servidor está escuchando en el puerto 3306. Para acceder a él, utilizamos las credenciales de **desarrollo** y contraseña **retacantabria24**. Este enfoque nos permite gestionar eficientemente la base de datos y garantizar la seguridad de nuestras operaciones.

  
  ## SERVIDOR WEB NGINX

  Contenedor Docker con Nginx para el Servidor Web:

    ¿Por qué Nginx? Nginx es un servidor web/proxy inverso ligero y de alto rendimiento. Es ideal para servir contenido estático, como archivos HTML, CSS y JavaScript.

  Ventajas de Nginx en Docker:

    Aislamiento: Los contenedores Docker proporcionan un entorno aislado para Nginx, lo que garantiza que no afecte al sistema anfitrión.  

    Portabilidad: Puedes crear, distribuir y ejecutar fácilmente contenedores Nginx en diferentes entornos sin preocuparte por las dependencias del sistema.  

    Escalabilidad: Docker permite escalar horizontalmente los contenedores Nginx según las necesidades de tráfico.  

  Contenedor Docker SFTP para el Servidor FTP:

    ¿Qué es SFTP? SFTP es un servidor SFTP (SSH File Transfer Protocol) basado en OpenSSH. Proporciona una forma segura de compartir archivos a través de conexiones cifradas.

  Ventajas de SFTP en Docker:

    Seguridad: SFTP utiliza SSH para transferir archivos, lo que garantiza la confidencialidad y autenticación segura.  

    Chrooting: Los usuarios se chrootizan a sus directorios de inicio, lo que mejora la seguridad y evita que accedan a otros archivos del sistema.  
    
    Personalización: Puedes definir usuarios y sus permisos en un archivo de configuración, lo que facilita la gestión.  

En resumen, la elección de estos contenedores proporciona una infraestructura flexible, segura y escalable para la aplicación.

# 3. WEB

Representación Directa de Información:
Desde el inicio, nuestra visión para la página web era que representara directamente la información relacionada con las actividades realizadas. Queríamos que los visitantes pudieran acceder fácilmente a los detalles relevantes sin necesidad de consultar una base de datos.
Ventajas:
Simplicidad: Al evitar la conexión a una base de datos, mantenemos la simplicidad y reducimos la complejidad del sistema.
Rendimiento: La carga de datos directamente desde la página web garantiza un rendimiento rápido y una experiencia fluida para los usuarios1.
Implementación a Través de un Archivo XML:
Aunque no era necesario conectar la web a una base de datos, decidimos estructurar la información de manera que sea fácil de implementar en el futuro. Por lo tanto, optamos por utilizar un archivo XML para almacenar los datos.
Ventajas:
Portabilidad: El formato XML es ampliamente compatible y se puede procesar en diferentes plataformas y lenguajes de programación.
Flexibilidad: Si en el futuro decidimos migrar a una base de datos, la transición será más sencilla gracias a la estructura XML existente.
Facilidad de Edición: Los archivos XML son legibles y editables manualmente, lo que facilita la actualización de la información2.
Formulario de Contacto:
Agregamos un formulario de contacto para fomentar la comunicación con los usuarios. Esto permite que los visitantes nos envíen consultas, comentarios o sugerencias directamente desde la web.
Ventajas:
Interacción: El formulario de contacto mejora la interacción con los usuarios y crea una vía de comunicación bidireccional.
Recopilación de Datos: Podemos recopilar información relevante de los visitantes y utilizarla para mejorar nuestros servicios o responder a sus necesidades.
Facilita la Comunicación: Los usuarios pueden expresar sus inquietudes sin salir de la página, lo que mejora la experiencia general3.
En resumen, estas elecciones se basaron en la simplicidad, la portabilidad y la facilidad de implementación, lo que contribuye a una página web eficiente y funcional.

# 4.- APLICACION



# 5.- CONCLUSIONES