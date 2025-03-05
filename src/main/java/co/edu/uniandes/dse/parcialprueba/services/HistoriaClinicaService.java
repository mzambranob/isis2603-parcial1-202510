package co.edu.uniandes.dse.parcialprueba.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoriaClinicaService {


    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    PacienteRepository pacienteRepository;


    @Transactional
    public void createHistoriaClinica(HistoriaClinicaEntity historiaClinica, Long idPaciente) throws EntityNotFoundException{

        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(idPaciente);
        if (pacienteEntity.isEmpty()) {
            throw new EntityNotFoundException("El paciente no fue encontrado");
        }
        Long acudiente = pacienteEntity.get().getId_acudienteAsociado();
        if (acudiente != null){
            String diagnostico = historiaClinica.getDiagnostico();
            String compartida = "HistoriaCompartida";
            String nuevoDiagnostico = compartida + diagnostico;
            historiaClinica.setDiagnostico(nuevoDiagnostico);

        }
        pacienteEntity.get().getHistoriasClinicas().add(historiaClinica);
        
    }
    
}
