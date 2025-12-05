package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.Alumno;
import org.example.model.Titor;
import org.example.repository.AlumnoRepository;
import org.example.repository.TitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final TitorRepository titorRepository;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository, TitorRepository titorRepository) {
        this.alumnoRepository = alumnoRepository;
        this.titorRepository = titorRepository;
    }

    @Transactional
    public Alumno crearOactualizarAlumno(Alumno alumno) {
        String idTitor = alumno.getIdTitor();

        if (idTitor == null) {
            throw new IllegalArgumentException("El alumno debe tener un id_titor asociado.");
        }

        Titor titor = titorRepository.findById(idTitor)
                .orElseThrow(() -> new EntityNotFoundException("Titor con ID " + idTitor + " no encontrado."));

        alumno.setTitor(titor);

        return alumnoRepository.save(alumno);
    }

    @Transactional
    public Alumno actualizarAlumnoExistente(Long id, Alumno nuevoAlumno) {
        Alumno alumnoExistente = alumnoRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Alumno no encontrado"));

        alumnoExistente.setNome(nuevoAlumno.getNome());
        alumnoExistente.setApelidos(nuevoAlumno.getApelidos());

        // Si se quiere cambiar de titor también
        if(nuevoAlumno.getIdTitor() != null) {
            Titor nuevoTitor = titorRepository.findById(nuevoAlumno.getIdTitor())
                    .orElseThrow(() -> new EntityNotFoundException("Nuevo Titor no encontrado"));
            alumnoExistente.setTitor(nuevoTitor);
        }

        return alumnoRepository.save(alumnoExistente);
    }

    public List<Alumno> obtenerTodosOsAlumnos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(String.valueOf(id));
    }

    public boolean eliminarAlumno(Long id) {
        if (alumnoRepository.existsById(String.valueOf(id))) {
            alumnoRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}