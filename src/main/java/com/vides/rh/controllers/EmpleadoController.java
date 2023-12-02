package com.vides.rh.controllers;

import com.vides.rh.excepciones.RecursoNoEncontradoExcepcion;
import com.vides.rh.models.Empleado;
import com.vides.rh.services.empleado.IEmpleadoService;
import org.hibernate.id.IntegralDataTypeHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a registrar: " + empleado);
        return iEmpleadoService.registrar(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> findById(@PathVariable Integer id){
        Empleado empleado = iEmpleadoService.findById(id);
        if(empleado == null)
            throw new RecursoNoEncontradoExcepcion("Empleado no encontrado, id: " + id);
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = iEmpleadoService.findById(id);
        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("Empleado no existe id: " + id);
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setDepartamento(empleadoRecibido.getDepartamento());
        empleado.setSueldo(empleadoRecibido.getSueldo());
        return ResponseEntity.ok(iEmpleadoService.registrar(empleado));
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = iEmpleadoService.findById(id);
        if(empleado == null)
            throw new RecursoNoEncontradoExcepcion("No encontrado.");
        iEmpleadoService.eliminar(empleado);
        // Json {"Eliminado", "true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
