package com.Livre.exemple_Bibliotheque;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "modifierLivreRequest")

public class ModifierLivreResponse {


    @XmlElement(required = true)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String s) {

    }
}
