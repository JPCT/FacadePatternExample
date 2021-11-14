package com.example.courseinscriptionexamplefacade.facade;

import com.example.courseinscriptionexamplefacade.exceptions.CouldNotInscribeException;

public interface IInscriptionServiceFacade {
    Boolean inscribeCourse(Long studentId, Long courseId) throws CouldNotInscribeException;
}
