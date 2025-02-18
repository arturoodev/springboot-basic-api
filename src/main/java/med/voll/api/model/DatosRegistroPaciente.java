package med.voll.api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,

        @NotBlank @Email
        String email,

        @NotBlank
        String telefono,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String documentoIdentidad,

        @NotNull
        @Valid
        DatosDireccion direccion
) {
}
