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
  * Representación Directa de Información
  * Funcionalidad
  * Estilo

[4. Aplicación](#aplicacion)  
  * Diagrama de clases
  * Javadoc
  * Cursos/Grupos y numero de alumnos
  * Funcionalidad

[5. Conclusiones](#conclusiones)  


---

# 1. BASE DE DATOS <a name="base-de-datos"></a>
  ### Descripcion de la base de datos. 
   
  #### 1. Tabla Profesores
    Columnas: id,dni,nombre,apellidos,correo,contraseña,password, nivel, activo, dept_id, foto  
    Clave primaria: id

  #### 2. Tabla Cursos
    Columnas: id_curso, cod_curso, descripcion, etapa, activo  
    Clave primaria: id_curso

  #### 3. Tabla Grupos
    Columnas: id_grupo, curso, cod_grupo, num_alumnos, activo  
    Clave primaria: id_grupo

  #### 4. Tabla Departamentos
    Columnas: id_depar, codigo, nombre, jefe  
    Clave primaria: id_depar

  #### 5. Tabla Mediostransporte
    Columnas: id,nombre  
    Clave primaria: id

  #### 6. Tabla Actividades
    Columnas: id_actividad, solicitante, titulo, tipo, fini, ffin, hini, hfin, prevista, transporte_req, coment_transporte, alojamiento_req, coment_alojamiento, comentarios, estado, coment_estado  
    Clave primaria: id_actividad

  #### 7. Tabla Programadas
    Columnas: id_programada, id_actividad, solicitante, titutlo, tipo, fini, ffin, hini, hfin, prevista, transporte_req, coment_transporte, alojamiento_req, coment_alojamiento, comentarios, estado, coment_estado, empresa_transporte, precio_transporte  
    Clave primaria: id_programada, id_actividad

  #### 8. Tabla Responsables
    Columnas: id, id_actividad, id_responsable  
    Clave primaria: id, id_actividad, id_responsable

  #### 9. Tabla Participantes
    Columnas: id, id_actividad, id_participante  
    Clave primaria: id, id_actividad, id_participante

  #### 10. Tabla Responsables_programada
    Columnas: id, id_programada, id_responsable  
    Clave primaria: id, id_programada, id_responsable

  #### 11. Tabla Grupos_participa
    Columnas: id, id_actividad, id_grupo  
    Clave primaria: id, id_actividad, id_grupo

  #### 12. Tabla Cursos_participa
    Columnas: id, id_actividad, id_curso  
    Clave primaria: id, id_actividad, id_curso

  #### 13. Tabla Transporte
    Columnas: id, id_actividad, id_transporte  
    Clave primaria: id, id_actividad, id_transporte

  #### 14. Tabla Cursos_programadas
    Columnas: id, id_programada, id_curso  
    Clave primaria: id, id_programada, id_curso

  #### 15. Tabla Grupos_programadas
    Columnas: id, id_programada, id_grupo  
    Clave primaria: id, id_programada, id_grupo

  #### 16. Tabla Participantes_programada
    Columnas: id, id_programada, id_participante  
    Clave primaria: id, id_programada, id_participante

  #### 17. Tabla Imagenes
    Columnas: id, url, descripcion, programadas_id_programada  
    Clave primaria: id, programadas_id_programada

  #### 18. Tabla Transporte_programado
    Columnas: id, id_programada, id_transporte  
    Clave primaria: id, programadas_id_programada


  ### Imagen de E/R. <a name="diagramaE/R"></a>

![Diagrama Entidad Relación](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Markdown/EsquemaER.png)

  ### Diagrama relacional <a name="diagramaR"></a>

![Diagrama Relacional](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Markdown/EsquemaRelacional.png)

  ### Archivo de importacion SQL 

[Script importacion BD](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Base%20de%20Datos/DBReto.sql)

[Script importacion BD con datos de prueba](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Base%20de%20Datos/DBTest.sql)

# 2. SERVIDORES <a name="servidores"></a>

  ## SERVIDOR BASE DE DATOS MARIADB

  La elección del servidor de base de datos se realizó en base a varias consideraciones:  

    Licencia de Código Abierto: MariaDB es una base de datos de código abierto. Esto es especialmente relevante para aplicaciones educativas o institucionales, ya que ayuda a reducir los gastos. 
    
    Compatibilidad con MySQL: MariaDB es una bifurcación de MySQL, por lo que es compatible con las aplicaciones y herramientas existentes que utilizan MySQL. 

    Rendimiento y Escalabilidad: MariaDB ofrece un rendimiento sólido y una escalabilidad eficiente. Puede manejar grandes volúmenes de datos y múltiples conexiones simultáneas, lo que es crucial si la aplicación de gestión de actividades crece más de lo esperado. 

    Seguridad: MariaDB incluye características de seguridad robustas, como cifrado de datos en reposo y en tránsito, autenticación avanzada y control de acceso granular. Esto es fundamental para proteger la información sensible de los estudiantes y el personal.


  En el contexto de nuestra aplicación, hemos configurado el servidor MariaDB en una máquina virtual Alpine Linux mediante un contenedor Docker. El servidor está escuchando en el puerto **3306**. Para acceder a él, utilizamos las credenciales de **desarrollo** y contraseña **retacantabria24**. Este enfoque nos permite gestionar eficientemente la base de datos y garantizar la seguridad de nuestras operaciones.

  
  ## SERVIDOR WEB NGINX

  Contenedor Docker con Nginx para el Servidor Web:

    ¿Por qué Nginx? Nginx es un servidor web/proxy inverso ligero y de alto rendimiento. Es ideal para servir contenido estático, como archivos HTML, CSS y JavaScript.

  Ventajas de Nginx en Docker:

    Aislamiento: Los contenedores Docker proporcionan un entorno aislado para Nginx, lo que garantiza que no afecte al sistema anfitrión.  

    Portabilidad: Puedes crear, distribuir y ejecutar fácilmente contenedores Nginx en diferentes entornos sin preocuparte por las dependencias del sistema.  

    Escalabilidad: Docker permite escalar horizontalmente los contenedores Nginx según las necesidades de tráfico.  

  El servidor Nginx se encuentra escuchando el puerto **8080**. Para facilitara la transferencia de archivos al servidor web se ha instalado un servidor SFTP que escucha el puerto **2222** y utilizará las credenciales **reto** y contraseña **retacantabria**.  
  Contenedor Docker SFTP para el Servidor FTP:

    ¿Qué es SFTP? SFTP es un servidor SFTP (SSH File Transfer Protocol) basado en OpenSSH. Proporciona una forma segura de compartir archivos a través de conexiones cifradas.

  Ventajas de SFTP en Docker:

    Seguridad: SFTP utiliza SSH para transferir archivos, lo que garantiza la confidencialidad y autenticación segura.  

    Chrooting: Los usuarios se chrootizan a sus directorios de inicio, lo que mejora la seguridad y evita que accedan a otros archivos del sistema.  
    
    Personalización: Puedes definir usuarios y sus permisos en un archivo de configuración, lo que facilita la gestión.  

En resumen, la elección de estos contenedores proporciona una infraestructura flexible, segura y escalable para la aplicación.

# 3. WEB <a name="web"></a>

### Representación Directa de Información:

  Desde el inicio, nuestra visión para la página web era que representara directamente la información relacionada con las actividades realizadas. Queríamos que los visitantes pudieran acceder fácilmente a los detalles relevantes y que los administradores pudieran publicitar las actividades facilmente. Ateniendonos a esta vision, la pagina muestra imagenes de las actividades realizadas, a traves de las cuales, pinchandolas, nos mostraran informacion mas completa sobre la actividad.  

    Simplicidad: Al evitar la conexión a una base de datos, mantenemos la simplicidad y reducimos la complejidad del sistema. 

    Rendimiento: La carga de datos directamente desde la página web garantiza un rendimiento rápido y una experiencia fluida para los usuarios.  

  Aunque no era necesario conectar la web a una base de datos, decidimos estructurar la información de manera que sea fácil de implementar en el futuro. Por lo tanto, optamos por utilizar un archivo XML para almacenar los datos. El formato XML es ampliamente compatible y se puede procesar en diferentes plataformas y lenguajes de programación. Además los archivos XML permiten una edicion manual sencilla que facilita la actualización de la información.

  Tambien hemos añadido una página de contacto para fomentar la comunicacion con los usuarios o con potenciales empresas organizadores de actividades. Esto permite que los visitantes nos envíen consultas, comentarios o sugerencias directamente desde la web.

  En resumen, estas elecciones se basaron en la simplicidad, la portabilidad y la facilidad de implementación, lo que contribuye a una página web eficiente y funcional.

### Funcionalidad

  La página como dijimos anteriormente se centra en mostrar la información relacionada con las actividades realizadas por el centro. Por eso la presentación se centra en este objetivo.

  Tanto la pagina principal

  ![](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Markdown/Inicio.png)

  como la galeria

  ![](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Markdown/Galeria.png)

  muestran imagenes que representan diferentes actividades realizadas por el instituto. Clicando cualquiera de las imagenes obtendremos mas información sobre las mismas ademas de una galeria con imagenes y videos. 

  ![](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Markdown/actividades.png)

  Además hemos añadido un formulario de contacto para que posibles promotores, o empresas interesadas puedan contactar con el instituto para ofrecer sus servicios.

  ![](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Markdown/Contacto.png)

### Estilo

  Para mostrar en la pagina principal las imagenes relacionadas con las actividades decidimos hacerlo a través de un slider que nos muestre una secuencia dinamica de imagenes.  

<details>
  <summary>Código CSS</summary>

  ```css
  /* Estilos del slider*/
  .slider{
    width: 100%;
    height: 80vh;
    overflow: hidden;
    padding-top: 90px;
  }

  .slider ul{
    display: flex;
    animation: cambio 20s infinite alternate linear;
    width: 600%;
    height: 60vh;
  }

  .slider li{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    list-style: none;
  }

  .slider img{
    margin-top: 30px;
    width: 100%;
    height: 100%;
    object-fit: cover; 
  }

  .slider img:hover {
    box-shadow: 0 0 20px 15px rgba(42, 206, 247, 0.3);
    transition: box-shadow 0.3s ease-in-out, margin 0.3s ease-in-out;
    transform: scale(1.05);
    margin: 30px 30px 0 30px;
  }

  .texto-slider {
    background: rgba(255, 255, 255, 0.5);
    border: 2px solid red;
    border-radius: 10px;
    padding: 20px;
    width: fit-content;
    margin: 20px;
    text-align: center;
  }

  @keyframes cambio{
    0%{margin-left: 0;}
    15%{margin-left: 0;}

    20%{margin-left: -100%;}
    35%{margin-left: -100%;}

    40%{margin-left: -200%;}
    55%{margin-left: -200%;}

    60%{margin-left: -300%;}
    75%{margin-left: -300%;}

    80%{margin-left: -400%;}
    90%{margin-left: -400%;}

    95%{margin-left: -500%;}
    100%{margin-left: -500%;}
  }
```
</details>  
En la sección galería la opción escogida fue una tabla que muestre las imagenes en un formato similar a las fotografías Polaroid con un pequeño titulo debajo.  
<details>
  <summary>Código CSS</summary>

```css
/* Estilo pagina galeria */
#galeria {
  height: 80vh;
  text-align: center;
  background: linear-gradient(rgba(37, 71, 30, 0.5), rgba(0, 0, 0, 0.5));
}

#galeria::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-image: url('../img/fondo.jpg');
  background-size: cover;
  opacity: 0.5;
  z-index: -1;
}

#galeria-fotos {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  width: 78vw;
  margin-left: 10vw;
}

#galeria-fotos div {
  flex: 0 1 calc(26% - 20px);
  margin: 10px;
  padding: 15px;
  border: 1px solid #ccc;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
  background-color: #f9f9f9;
  text-align: center;
  height: 35vh;
  overflow: hidden;
  align-items: center;
  justify-content: center;
}

#galeria-fotos div:hover {
  transform: scale(1.05);
  box-shadow: 0 0 5px rgba(42, 206, 247, 0.3);
}

#galeria img {
  max-width: 100%;
  height: auto;
  display: block;
  object-fit: cover;
  margin: auto;
  min-height: 80%;
  max-height: 80%;
}

#galeria label {
  margin: 10px 0;
  font-size: 14px;
  color: #666;
}
```
</details>

Para mostrar la información concreta de las actividades, decidimos desplegar una ventana modal, que recibe sus datos de un archivo XML. En el futuro se podrá sincronizar, si se desea, las acciones realizadas en la aplicación para que la página web se actualice automáticamente.

<details>
  <summary>Ventana modal</summary>

```css

/* ///////////////// MODAL ///////////////// */

.modal {
  display: none;
  position: fixed;
  z-index: 1;
  padding-top: 100px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.4);
}

.modal-content {
  background: linear-gradient(rgba(96, 185, 78, 0.5), rgba(212, 59, 59, 0.5)), url('../img/fondo.jpg'), #fefefe;
  margin: auto;
  padding: 40px;
  border: 1px solid #888;
  border-radius: 10px;
  width: 80%;
  height: 80vh;
  overflow: auto;
}

.modal-content h1{
  text-align: center;
  font-size: 30px;
  font-weight: 300;
  color: black;
  margin-bottom: 50px;
}

.modal-content img {
  width: 50%;
  height: 30vh;
  margin: 0 0 0 0 !important;
  object-fit: contain;
  padding: 10px 10px 10px 10px
}

.modal-content img:hover {
  box-shadow: none !important;
  transform: none !important;
  margin: 0 0 0 0 !important;
  padding: 0 !important;
  position: static !important;
}

.modal-content video {
  width: 50%;
  height: 30vh;
  margin: 0 0 0 0 !important;
  object-fit: contain;  
}

.modal-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-auto-rows: minmax(100px, auto);
}

.modal-info{
  grid-column: 1;
  grid-row: 1 / span 2;
  text-align: left;
  position: sticky;
  top: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.modal-info-centro {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-115%, -50%);
  text-align: center;
  padding: 30px;
  border-radius: 10px;
  max-width: 30%;
  background-color: rgba(96, 185, 78, 0.5);
}

.modal-info-centro p {
  font-size: 20px;
  font-weight: 300;
  color: black;
  margin: 10px 0;
}

.modalMedia > * {
  grid-column: 2;
}

.close {
  color: #000000;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
```
</details>

<details>
  <summary>Javascript lectura XML</summary>

```js
document.addEventListener('DOMContentLoaded', (event) => {

    var modal = document.getElementById("popup");

    var img = document.getElementById("actividad1");
    img.onclick = function(){
        showModal(this.id);
    }
    var img = document.getElementById("actividad2");
    img.onclick = function(){
        showModal(this.id);
    }
    var img = document.getElementById("actividad3");
    img.onclick = function(){
        showModal(this.id);
    }
    var img = document.getElementById("actividad4");
    img.onclick = function(){
        showModal(this.id);
    }
    var img = document.getElementById("actividad5");
    img.onclick = function(){
        showModal(this.id);
    }
    var img = document.getElementById("actividad6");
    img.onclick = function(){
        showModal(this.id);
    }

    var span = document.getElementsByClassName("close")[0];
    span.onclick = function() {
        var modal = document.getElementById("popup");
        modal.style.display = "none";
    }

    function showModal(imgId) {

        var xhttp = new XMLHttpRequest();

        xhttp.onload = function() {
            var xmlDoc = this.responseXML;

            var imagenes = xmlDoc.getElementsByTagName("imagen");
            var imagen;
            for (var i = 0; i < imagenes.length; i++) {
                if (imagenes[i].getAttribute("id") === imgId) {
                    imagen = imagenes[i];
                    break;
                }
            }

            if (!imagen) {
                return;
            }

            var title = imagen.getElementsByTagName("titulo")[0].textContent;
            var text = imagen.getElementsByTagName("texto")[0].textContent;
            var responsables = imagen.getElementsByTagName("responsables")[0].textContent;
            var horario = imagen.getElementsByTagName("horario")[0].textContent;
            var descripcion = imagen.getElementsByTagName("descripcion")[0].textContent;
            var clases = imagen.getElementsByTagName("clases")[0].textContent;
            var imgSrcElements = imagen.getElementsByTagName("imgSrc");
            var videoSrcElements = imagen.getElementsByTagName("videoSrc");

            var modal = document.getElementById("popup");
            var modalTitle = document.getElementById("modalTitle");
            var modalText = document.getElementById("modalText");
            var modalResponsables = document.getElementById("modalResponsables");
            var modalHorario = document.getElementById("modalHorario");
            var modalDescripcion = document.getElementById("modalDescripcion");
            var modalClases = document.getElementById("modalClases");
            var modalMedia = document.getElementById("modalMedia");

            while (modalMedia.firstChild) {
                modalMedia.removeChild(modalMedia.firstChild);
            }

            for (var i = 0; i < imgSrcElements.length; i++) {
                var imgSrc = imgSrcElements[i].textContent;
                var img = document.createElement("img");
                img.src = imgSrc;
                modalMedia.appendChild(img);
            }

            for (var i = 0; i < videoSrcElements.length; i++) {
                var videoSrc = videoSrcElements[i].textContent;
                var video = document.createElement("video");
                video.controls = true;
                var source = document.createElement("source");
                source.src = videoSrc;
                source.type = "video/mp4";
                video.appendChild(source);
                modalMedia.appendChild(video);
            }

            modalTitle.textContent = title;
            modalText.textContent = "Descripción: " + text;
            modalResponsables.textContent = "Profesores responsables: " + responsables;
            modalHorario.textContent = "Fecha de la actividad: " + horario;
            modalDescripcion.textContent = descripcion;
            modalClases.textContent = "Cursos participntes: " + clases;

            modal.style.display = "block";
        };

        xhttp.open("GET", "OTROS/actividades.xml", true);

        xhttp.send();
    }
});

```
</details>

# 4.- APLICACION <a name="aplicacion"></a>

  ### Diagrama de clases

![Diagrama de clases](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Markdown/UML.png)

  ### Javadoc

  [Documentacion Javadoc](https://raw.githack.com/DAMRetoCantabria/RetoDAM2/Documentaci%C3%B3n/App/acex/target/site/apidocs/index.html)

  ### Cursos/Grupos y numero de alumnos

  > Optamos por modelar una relación entre cursos y grupos, donde cada grupo tiene un atributo que indica a qué curso pertenece. Esta estructura jerárquica nos permite organizar los grupos de manera lógica y facilita la gestión de los alumnos. La relación curso-grupo refleja la estructura real de la organización educativa y ayuda a los usuarios a comprender la jerarquía. Los grupos se asocian directamente con un curso específico, lo que simplifica la administración y búsqueda.
  > 
  > Decidimos asignar el número de alumnos solo a los grupos, ya que los cursos están formados por varios grupos. El número de alumnos en un curso es simplemente la suma de los alumnos en sus grupos asociados. Al calcular el total de alumnos a nivel de curso, evitamos duplicar información y reducimos la carga de mantenimiento. La coherencia entre los datos de grupos y cursos garantiza que siempre tengamos una visión precisa del número total de alumnos.

  ### Funcionalidad


# 5.- CONCLUSIONES <a name="conclusiones"></a>