package com.vides.rh.controllers;

import com.vides.rh.models.Empleado;
import com.vides.rh.services.empleado.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rh-app")
@CrossOrigin("http://localhost:3000")
public class EmpleadoController {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private IEmpleadoService iEmpleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados(){
        var empleados = iEmpleadoService.listar();
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }

}
