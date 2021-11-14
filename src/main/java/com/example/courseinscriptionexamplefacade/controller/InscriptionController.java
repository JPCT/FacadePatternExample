package com.example.courseinscriptionexamplefacade.controller;

import com.example.courseinscriptionexamplefacade.classes.InscribeResponse;
import com.example.courseinscriptionexamplefacade.exceptions.CouldNotInscribeException;
import com.example.courseinscriptionexamplefacade.facade.InscriptionServiceFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/inscription")
public class InscriptionController {
    private final InscriptionServiceFacade facade;

    public InscriptionController(InscriptionServiceFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/inscribe")
    ResponseEntity<InscribeResponse> inscribeNewCourse(@RequestBody Map<String, String> json){
        Long studentId = Long.parseLong(json.get("studentId"));
        Long courseId = Long.parseLong(json.get("courseId"));

        Boolean inscribed;
        try {
            inscribed = facade.inscribeCourse(studentId, courseId);
        }catch (CouldNotInscribeException e){
            return new ResponseEntity<>(new InscribeResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (inscribed) {
            return new ResponseEntity<>(new InscribeResponse(true, "Inscrito exitosamente"), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(new InscribeResponse(false, "Se ha producido un error inesperado"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
