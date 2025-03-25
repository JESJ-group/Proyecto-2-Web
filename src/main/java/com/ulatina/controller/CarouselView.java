package com.ulatina.controller;

import com.ulatina.service.ServicioCarousel;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CarouselView implements Serializable {

    private List<String> images;

    private ServicioCarousel servicioCarousel = new ServicioCarousel();

    @PostConstruct
    public void init() throws SQLException {
        try {
            // Obtener rutas de imágenes desde la base de datos
            images = servicioCarousel.obtenerRutasImagenes();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            images = new ArrayList<>(); // Para evitar null en caso de error
        }
    }

    public List<String> getImages() {
        return images;
    }
}

