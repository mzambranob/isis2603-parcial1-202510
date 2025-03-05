package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;



@Data
@Entity
public class HistoriaClinicaEntity extends BaseEntity{
    
    private String fechaDeCreacion;
    private String diagnostico;
    private String tratamiento;

    @PodamExclude
    @ManyToOne
    PacienteEntity paciente;
    
}
