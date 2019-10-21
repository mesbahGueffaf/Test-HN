package fr.hn.hntest.model;

public class ImageInformations {
    private String message;
    private String status;

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public ImageInformations(final String message, final String status) {
        this.message = message;
        this.status = status;
    }

    public ImageInformations() {
    }
}
