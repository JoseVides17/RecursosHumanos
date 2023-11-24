package com.vides.rh.services.empleado;

import com.vides.rh.models.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> listar();
    public Empleado findById(Integer id);
    public Empleado registrar(Empleado empleado);
    public void eliminar(Empleado empleado);

}
