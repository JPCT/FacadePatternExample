package com.example.courseinscriptionexamplefacade.classes;

public class InscribeResponse {
    private Boolean inscribed;
    private String message;

    public InscribeResponse(Boolean inscribed, String message) {
        this.inscribed = inscribed;
        this.message = message;
    }

    public Boolean getInscribed() {
        return inscribed;
    }

    public void setInscribed(Boolean inscribed) {
        this.inscribed = inscribed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
