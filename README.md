# APLICACION DE GESTION DE ACTIVIDADES EXTRAESCOLARES

---

## Índice

[1. Base de Datos](#base-de-datos)  
  * Descripcion de la base de datos.
  * Imagen de E/R. [Diagrama E/R](#diagramaE/R)
  * Diagrama relacional [Diagrama relacinal](#diagramaR)
  * Archivo de importacion SQL 
  
[2. Servidores](#servidores)  

[3. Web](#web)  

[4. Aplicación](#aplicacion)  

[5. Conclusiones](#conclusiones)  


---

# 1. BASE DE DATOS
  * Descripcion de la base de datos. 
   
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


![Diagrama Entidad Relación](https://github.com/DAMRetoCantabria/RetoDAM2/blob/Documentaci%C3%B3n/Documentacion/EntidadRelacion.png)


2. SERVIDORES



