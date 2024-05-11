document.addEventListener('DOMContentLoaded', (event) => {

    // Get the modal
    var modal = document.getElementById("popup");

    // Get the image and link it with the modal
    var img = document.getElementById("actividad1");
    img.onclick = function(){
        showModal(this.id); // Call the showModal function with the image id
    }
    var img = document.getElementById("actividad2");
    img.onclick = function(){
        showModal(this.id); // Call the showModal function with the image id
    }
    var img = document.getElementById("actividad3");
    img.onclick = function(){
        showModal(this.id); // Call the showModal function with the image id
    }
    var img = document.getElementById("actividad4");
    img.onclick = function(){
        showModal(this.id); // Call the showModal function with the image id
    }
    var img = document.getElementById("actividad5");
    img.onclick = function(){
        showModal(this.id); // Call the showModal function with the image id
    }
    var img = document.getElementById("actividad6");
    img.onclick = function(){
        showModal(this.id); // Call the showModal function with the image id
    }

    // When the user clicks on the close button (x), close the modal
    var span = document.getElementsByClassName("close")[0];
    span.onclick = function() {
        var modal = document.getElementById("popup");
        modal.style.display = "none";
    }

    function showModal(imgId) {

        // Create a new XMLHttpRequest object
        var xhttp = new XMLHttpRequest();

        // Define the function that will run when the request is complete
        xhttp.onload = function() {
            // Parse the XML
            var xmlDoc = this.responseXML;

            // Search for the image in the XML
            var imagenes = xmlDoc.getElementsByTagName("imagen");
            var imagen;
            for (var i = 0; i < imagenes.length; i++) {
                if (imagenes[i].getAttribute("id") === imgId) {
                    imagen = imagenes[i];
                    break;
                }
            }

            // If the image was not found, end the function
            if (!imagen) {
                return;
            }

            // Extract the image information
            var title = imagen.getElementsByTagName("titulo")[0].textContent;
            var text = imagen.getElementsByTagName("texto")[0].textContent;
            var responsables = imagen.getElementsByTagName("responsables")[0].textContent;
            var horario = imagen.getElementsByTagName("horario")[0].textContent;
            var descripcion = imagen.getElementsByTagName("descripcion")[0].textContent;
            var clases = imagen.getElementsByTagName("clases")[0].textContent;
            var imgSrcElements = imagen.getElementsByTagName("imgSrc");
            var videoSrcElements = imagen.getElementsByTagName("videoSrc");

            // Get the modal and the elements where the content will be displayed
            var modal = document.getElementById("popup");
            var modalTitle = document.getElementById("modalTitle");
            var modalText = document.getElementById("modalText");
            var modalResponsables = document.getElementById("modalResponsables");
            var modalHorario = document.getElementById("modalHorario");
            var modalDescripcion = document.getElementById("modalDescripcion");
            var modalClases = document.getElementById("modalClases");
            var modalMedia = document.getElementById("modalMedia");

            // Clear the media container
            while (modalMedia.firstChild) {
                modalMedia.removeChild(modalMedia.firstChild);
            }

            // Create an img element for each imgSrc
            for (var i = 0; i < imgSrcElements.length; i++) {
                var imgSrc = imgSrcElements[i].textContent;
                var img = document.createElement("img");
                img.src = imgSrc;
                modalMedia.appendChild(img);
            }

            // Create a video element for each videoSrc
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

            // Change the content of the modal
            modalTitle.textContent = title;
            modalText.textContent = "Descripción: " + text;
            modalResponsables.textContent = "Profesores responsables: " + responsables;
            modalHorario.textContent = "Fecha de la actividad: " + horario;
            modalDescripcion.textContent = descripcion;
            modalClases.textContent = "Cursos participntes: " + clases;

            // Display the modal
            modal.style.display = "block";
        };

        // Open the request
        xhttp.open("GET", "OTROS/actividades.xml", true);

        // Send the request
        xhttp.send();
    }
});