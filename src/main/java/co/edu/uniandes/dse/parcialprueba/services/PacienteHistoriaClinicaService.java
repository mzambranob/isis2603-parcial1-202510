package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class PacienteHistoriaClinicaService {

@Autowired
PacienteRepository pacienteRepository;

    @Transactional
    public void PacienteAcudienteAsociation(Long idPaciente, Long idAcudiente)throws IllegalOperationException, EntityNotFoundException{
        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(idPaciente);
        Optional<PacienteEntity> acudiente = pacienteRepository.findById(idAcudiente);
        if(pacienteEntity.isEmpty() || acudiente.isEmpty()){
            throw new EntityNotFoundException("No se encontró el paciente o el acudiente");
        }
        else{
            if (pacienteEntity.get().getId_acudienteAsociado() != null && acudiente.get().getId_acudienteAsociado() != null){
                throw new IllegalOperationException("El Paciente ya tiene un Acudiente asociado o el Acudiente ya tiene un Paciente asociado");
            }
        }
        if (acudiente.get().getHistoriasClinicas().isEmpty()){
            throw new IllegalOperationException("El acudiente no tiene ninguna historia clinica a su nombre");
        }
        pacienteEntity.get().setId_acudienteAsociado(idAcudiente);
    }
}
