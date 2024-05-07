const carouselInner = document.querySelector('.carousel-inner');
const images = document.querySelectorAll('.carousel-inner img');

let counter = 1;
let size; // Variable para almacenar el tamaño de la imagen

// Esperar a que todas las imágenes se carguen completamente
window.addEventListener('load', () => {
  size = images[0].clientWidth;

  // Asignar el tamaño del carrusel interior
  carouselInner.style.width = `${size * images.length}px`;

  setInterval(nextSlide, 3000); // Cambia la imagen cada 3 segundos
});
