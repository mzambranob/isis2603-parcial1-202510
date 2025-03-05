package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;


@Data
@Entity
public class PacienteEntity extends BaseEntity{


    @PodamExclude
    @OneToMany(mappedBy = "Paciente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<HistoriaClinicaEntity>historiasClinicas = new ArrayList<>();

    private String nombre;
    private String correo;
    private String telefono;
    private Long id_acudienteAsociado;
}
