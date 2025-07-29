package com.example.catalog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType; // <--- ASEGÚRATE DE IMPORTAR ESTO
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- ¡ESTE ES EL CAMBIO CLAVE!
    private Long id;

    private String name;
}