package org.example.desafiodesignpatternsbootcampsantanderdio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * DTO = objeto só para transporte de dados, sem lógica de negócio.
 * Representa o JSON da Google Books — NÃO vai direto pro banco.
 * @JsonIgnoreProperties = ignora campos do JSON que não mapeamos.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponse {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String id;
        private VolumeInfo volumeInfo;

        public String getId() {return id;}

        public VolumeInfo getVolumeInfo() {
            return volumeInfo;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private ImageLinks imageLinks;
        public String getTitle() { return title; }
        public List<String> getAuthors() { return authors; }
        public ImageLinks getImageLinks() { return imageLinks; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageLinks {
        private String thumbnail;
        public String getThumbnail() { return thumbnail; }
    }

}
