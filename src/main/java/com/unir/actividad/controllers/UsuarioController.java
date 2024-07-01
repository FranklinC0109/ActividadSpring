package com.unir.actividad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.actividad.entities.StandardResponse;
import com.unir.actividad.entities.Usuario;
import com.unir.actividad.enums.EStatusReponse;
import com.unir.actividad.services.UsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    UsuarioService usuario;

    @Autowired
	public void setUsuario(UsuarioService usuario) {
		this.usuario = usuario;
	}


    @GetMapping(path = { "/consultarIngreso/{usu}/{contra}" })
    public ResponseEntity<StandardResponse<Usuario>> consultarIngreso(@PathVariable String usu,
            @PathVariable String contra) {
        ResponseEntity<StandardResponse<Usuario>> respuesta = null;
        StandardResponse<Usuario> resultado = null;
        try {
            if (usu != null && !usu.isEmpty() && contra != null && !contra.isEmpty()) {
                Usuario objeto = usuario.consultarIngreso(usu, contra);
                resultado = new StandardResponse<Usuario>(EStatusReponse.SUCCESS.getNombre());
                resultado.setObjeto(objeto);
            } else {
                resultado = new StandardResponse<Usuario>(EStatusReponse.ERROR.getNombre(),
                        "Por favor verifique que esta llenando todos los datos");
            }
            respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            resultado = new StandardResponse<Usuario>(EStatusReponse.ERROR.getNombre(), e.getMessage());
            respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
        }
        return respuesta;
    }

}
