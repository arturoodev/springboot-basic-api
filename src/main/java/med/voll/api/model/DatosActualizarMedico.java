package med.voll.api.model;

import jakarta.validation.constraints.NotNull;


public record DatosActualizarMedico(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
