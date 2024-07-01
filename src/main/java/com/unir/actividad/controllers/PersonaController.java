package com.unir.actividad.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.actividad.dtos.PersonaDTO;
import com.unir.actividad.entities.Persona;
import com.unir.actividad.entities.StandardResponse;
import com.unir.actividad.enums.EStatusReponse;
import com.unir.actividad.services.PersonaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/persona")
public class PersonaController {

	PersonaService servicio;

	/**
	 * Método que inyecta la dependencia de servicio persona
	 * @param PersonaService servicio
	 * */
	@Autowired
	public void setServicio(PersonaService servicio) {
		this.servicio = servicio;
	}

	/**
	 * Método que obtiene todos los registros de la clase persona
	 * @return ResponseEntity<StandardResponse<Persona>>
	 */
	@GetMapping("/obtenerTodos")
	public ResponseEntity<StandardResponse<Persona>> obtenerTodos() {
		ResponseEntity<StandardResponse<Persona>> respuesta = null;
		StandardResponse<Persona> resultado = null;
		try {
			List<Persona> objetos = servicio.findAllP();
			resultado = new StandardResponse<Persona>(EStatusReponse.SUCCESS.getNombre());
			resultado.setObjetos(objetos);
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	/**
	 * Método que obtiene un registro por el ID
	 * @param Integer pId
	 * @return ResponseEntity<StandardResponse<Persona>>
	 */
	@GetMapping(path = { "/obtenerPorId/{pId}" })
	public ResponseEntity<StandardResponse<Persona>> obtenerById(@PathVariable Integer pId) {
		ResponseEntity<StandardResponse<Persona>> respuesta = null;
		StandardResponse<Persona> resultado = null;
		try {
			if (pId != null) {
				Persona objeto = servicio.findByIdP(pId);
				resultado = new StandardResponse<Persona>(EStatusReponse.SUCCESS.getNombre());
				resultado.setObjeto(objeto);
			} else {
				resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar el ID del registro a consultar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	/**
	 * Método que obtiene un registro por el Nombre1
	 * @param String pNombre
	 * @return ResponseEntity<StandardResponse<Persona>>
	 */
	@GetMapping(path = { "/obtenerPorNombre/{pNombre}" })
	public ResponseEntity<StandardResponse<Persona>> obtenerByNombre(@PathVariable String pNombre) {
		ResponseEntity<StandardResponse<Persona>> respuesta = null;
		StandardResponse<Persona> resultado = null;
		try {
			if (pNombre != null && !pNombre.isEmpty()) {
				Persona objeto = servicio.findByNombreP(pNombre);
				resultado = new StandardResponse<Persona>(EStatusReponse.SUCCESS.getNombre());
				resultado.setObjeto(objeto);
			} else {
				resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar el Nombre1 del registro a consultar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}
	
	/**
	 * Método que obtiene un registro por la cédula
	 * @param Integer pCedula
	 * @return ResponseEntity<StandardResponse<Persona>>
	 */
	@GetMapping(path = { "/obtenerPorCedula/{pCedula}" })
	public ResponseEntity<StandardResponse<Persona>> obtenerByCedula(@PathVariable Integer pCedula) {
		ResponseEntity<StandardResponse<Persona>> respuesta = null;
		StandardResponse<Persona> resultado = null;
		try {
			if (pCedula != null ) {
				Persona objeto = servicio.findByCedulaP(pCedula);
				resultado = new StandardResponse<Persona>(EStatusReponse.SUCCESS.getNombre());
				resultado.setObjeto(objeto);
			} else {
				resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar la cédula del registro a consultar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	/**
	 * Método que crear un objeto de la clase persona
	 * @param PersonaDTO pObjeto
	 * @return ResponseEntity<StandardResponse<Persona>>
	 */
	@PostMapping("/crear")
	public ResponseEntity<StandardResponse<Persona>> crearRegitro(@RequestBody PersonaDTO pObjeto) {
		ResponseEntity<StandardResponse<Persona>> respuesta = null;
		StandardResponse<Persona> resultado = null;
		try {
			Persona objeto = convertirDTOEntidad(pObjeto, Persona.class);
			objeto = servicio.saveP(objeto);
			resultado = new StandardResponse<Persona>(EStatusReponse.SUCCESS.getNombre());
			resultado.setObjeto(objeto);
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	/**
	 * Método que actualiza un objeto de la clase persona
	 * @param PersonaDTO pObjeto
	 * @return ResponseEntity<StandardResponse<Persona>>
	 */
	@PutMapping("/actualizar")
	public ResponseEntity<StandardResponse<Persona>> actualizarRegistro(@RequestBody PersonaDTO pObjeto) {
		ResponseEntity<StandardResponse<Persona>> respuesta = null;
		StandardResponse<Persona> resultado = null;
		try {
			if (pObjeto != null && pObjeto.getId() != null) {
				if (servicio.existeId(pObjeto.getId())) {
					Persona objeto = convertirDTOEntidad(pObjeto, Persona.class);
					objeto = servicio.saveP(objeto);
					resultado = new StandardResponse<Persona>(EStatusReponse.SUCCESS.getNombre());
					resultado.setObjeto(objeto);
				} else {
					resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(),
							"El registro no existe");
				}
			} else {
				resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar el ID del registro a actualizar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}

		return respuesta;
	}

	/**
	 * Método que elimina un objeto de la clase persona
	 * @param Integer pId
	 * @return ResponseEntity<StandardResponse<Persona>>
	 */
	@DeleteMapping(path = { "/borrar/{pId}" })
	public ResponseEntity<StandardResponse<Persona>> borrarRegistro(@PathVariable Integer pId) {
		ResponseEntity<StandardResponse<Persona>> respuesta = null;
		StandardResponse<Persona> resultado = null;
		try {
			if (pId != null) {
				if (servicio.existeId(pId)) {
					servicio.deleteP(pId);
					resultado = new StandardResponse<Persona>(EStatusReponse.SUCCESS.getNombre(),
							"Registro eliminado satisfactoriamente");
				} else {
					resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(),
							"El ID diligenciado no existe");
				}
			} else {
				resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar el ID del registro a borrar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Persona>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	/**
	 * Método que convierte un objeto DTO a una clase Entidad
	 * @param Object dto, Class<T> clase
	 * @return <T> T
	 */
	private <T> T convertirDTOEntidad(Object dto, Class<T> clase) {
		ModelMapper modelmapper = new ModelMapper();
		return (T) modelmapper.map(dto, clase);
	}

}
