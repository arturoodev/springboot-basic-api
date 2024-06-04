package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.DatosActualizarMedico;
import med.voll.api.model.DatosListadoMedicos;
import med.voll.api.model.DatosRegistroMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void registerDoctor(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico) {
        System.out.println("El doctor se acaba de registrar");
        medicoRepository.save(new Medico(datosRegistroMedico));

    }

    /*@GetMapping
    public List<DatosListadoMedicos> listarMedicos() {
        return medicoRepository.findAll().stream().map(DatosListadoMedicos::new).toList();
    }*/

    @GetMapping
    public Page<DatosListadoMedicos> listarMedicos(@PageableDefault(size = 10) Pageable paginacion) {
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedicos::new);
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedicos::new);
    }

    @PutMapping
    @Transactional
    public void actulizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarInformacion(datosActualizarMedico);
    }

   /* @DeleteMapping("/{id}")
    @Transactional
    public void elminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }

    @GetMapping("/inactivos")
    public Page<DatosListadoMedicos> listarMedicosInactivos(@PageableDefault(size = 10) Pageable paginacion) {
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedicos::new);
        return medicoRepository.findByActivoFalse(paginacion).map(DatosListadoMedicos::new);
    }
}
