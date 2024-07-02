package com.unir.actividad.controllers;

import java.util.List;

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

import com.unir.actividad.dtos.MatriculaDTO;
import com.unir.actividad.entities.Matricula;
import com.unir.actividad.entities.Persona;
import com.unir.actividad.entities.StandardResponse;
import com.unir.actividad.enums.EStatusReponse;
import com.unir.actividad.services.MatriculaService;
import com.unir.actividad.services.PersonaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {

	MatriculaService matricula;
	PersonaService persona;

	@Autowired
	public void setMatricula(MatriculaService matricula) {
		this.matricula = matricula;
	}
	
	@Autowired
	public void setPersona(PersonaService persona) {
		this.persona = persona;
	}

	@GetMapping("/obtenerTodosM")
	public ResponseEntity<StandardResponse<Matricula>> obtenerTodos() {
		ResponseEntity<StandardResponse<Matricula>> respuesta = null;
		StandardResponse<Matricula> resultado = null;
		try {
			List<Matricula> objetos = matricula.findAllP();
			resultado = new StandardResponse<Matricula>(EStatusReponse.SUCCESS.getNombre());
			resultado.setObjetos(objetos);
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	@GetMapping(path = { "/obtenerId/{mId}" })
	public ResponseEntity<StandardResponse<Matricula>> obtenerById(@PathVariable Integer mId) {
		ResponseEntity<StandardResponse<Matricula>> respuesta = null;
		StandardResponse<Matricula> resultado = null;
		try {
			if (mId != null) {
				Matricula objeto = matricula.findByIdMatricula(mId);
				resultado = new StandardResponse<Matricula>(EStatusReponse.SUCCESS.getNombre());
				resultado.setObjeto(objeto);
			} else {
				resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar el ID del registro a consultar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	@PostMapping("/crear")
	public ResponseEntity<StandardResponse<Matricula>> crearRegitro(@RequestBody MatriculaDTO mObjeto) {
		ResponseEntity<StandardResponse<Matricula>> respuesta = null;
		StandardResponse<Matricula> resultado = null;
		try {
			if (mObjeto != null && mObjeto.getIdPersona() != null && mObjeto.getIdPersona().getId() != null) {
				if (mObjeto.getTipo() != null && (mObjeto.getTipo() == 1 || mObjeto.getTipo() == 2)) {
					Matricula objeto = convertirDTOEntidad(mObjeto, Matricula.class);
					objeto = matricula.saveMatricula(objeto);
					resultado = new StandardResponse<Matricula>(EStatusReponse.SUCCESS.getNombre());
					resultado.setObjeto(objeto);
				} else {
					resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
							"Tipo debe ser 1-Carro o 2-Moto");
				}
			} else {
				resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar el IdPersona");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	@PutMapping("/actualizar")
	public ResponseEntity<StandardResponse<Matricula>> actualizarRegistro(@RequestBody MatriculaDTO mObjeto) {
		ResponseEntity<StandardResponse<Matricula>> respuesta = null;
		StandardResponse<Matricula> resultado = null;
		try {
			if (mObjeto != null && mObjeto.getIdMatricula() != null) {
				if (matricula.existeIdMatricula(mObjeto.getIdMatricula())) {
					Matricula objeto = convertirDTOEntidad(mObjeto, Matricula.class);
					objeto = matricula.saveMatricula(objeto);
					resultado = new StandardResponse<Matricula>(EStatusReponse.SUCCESS.getNombre());
					resultado.setObjeto(objeto);
				} else {
					resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
							"El registro no existe");
				}
			} else {
				resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar el ID del registro a actualizar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}

		return respuesta;
	}

	@DeleteMapping(path = { "/borrar/{mId}" })
	public ResponseEntity<StandardResponse<Matricula>> borrarRegistro(@PathVariable Integer mId) {
		ResponseEntity<StandardResponse<Matricula>> respuesta = null;
		StandardResponse<Matricula> resultado = null;
		try {
			if (mId != null) {
				if (matricula.existeIdMatricula(mId)) {
					matricula.deleteMatricula(mId);
					resultado = new StandardResponse<Matricula>(EStatusReponse.SUCCESS.getNombre(),
							"Registro eliminado satisfactoriamente");
				} else {
					resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
							"El ID diligenciado no existe");
				}
			} else {
				resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar el ID del registro a borrar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	@GetMapping(path = { "/obtenerPlaca/{mPlaca}" })
	public ResponseEntity<StandardResponse<Matricula>> obtenerByPlaca(@PathVariable String mPlaca) {
		ResponseEntity<StandardResponse<Matricula>> respuesta = null;
		StandardResponse<Matricula> resultado = null;
		try {
			if (mPlaca != null && !mPlaca.isEmpty()) {
				Matricula objeto = matricula.findByPlaca(mPlaca);
				resultado = new StandardResponse<Matricula>(EStatusReponse.SUCCESS.getNombre());
				resultado.setObjeto(objeto);
			} else {
				resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar la Placa del registro a consultar");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}

	@GetMapping(path = { "/obtenerPorPersona/{idPersona}" })
	public ResponseEntity<StandardResponse<Matricula>> obtenerPorPersona(@PathVariable Integer idPersona) {
		ResponseEntity<StandardResponse<Matricula>> respuesta = null;
		StandardResponse<Matricula> resultado = null;
		try {
			if (idPersona != null) {
				Persona obj = persona.findByCedulaP(idPersona);
				if(obj != null && obj.getId() != null) {
					List<Matricula> objeto = matricula.findByPersona(obj.getId().toString());
					resultado = new StandardResponse<Matricula>(EStatusReponse.SUCCESS.getNombre());
					resultado.setObjetos(objeto);
				}else {
					resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
							"La cédula no esta registrada");
				}
			} else {
				resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(),
						"Se debe diligenciar la cédula de la persona a consultar registros");
			}
			respuesta = new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			resultado = new StandardResponse<Matricula>(EStatusReponse.ERROR.getNombre(), e.getMessage());
			respuesta = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
		}
		return respuesta;
	}



	private <T> T convertirDTOEntidad(Object dto, Class<T> clase) {
		ModelMapper modelmapper = new ModelMapper();
		return (T) modelmapper.map(dto, clase);
	}

}
