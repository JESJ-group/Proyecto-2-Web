package com.ulatina.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para el carrusel de imágenes.
 */
@Named
@ViewScoped
public class CarouselView implements Serializable {

    private List<String> images; // Solo necesitamos una lista de imágenes

    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        images.add("amazon.jpg");
        images.add("bostonscientific.jpg");
        images.add("google.jpg");
        images.add("microsoft.jpg");
        images.add("pepsi.jpg");
        images.add("starbucks.jpg");
        images.add("tekexperts.jpg");
    }

    public List<String> getImages() {
        return images;
    }
}

