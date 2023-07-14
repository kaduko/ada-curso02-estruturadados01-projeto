package tech.ada;

import java.time.LocalDate;

public record PrecoCombustivel(
        Long id,
        String regiaoSigla,
        String estadoSigla,
        String municipio,
        String revenda,
        String cnpjDaRevenda,
        String nomeDaRua,
        String numeroRua,
        String complemento,
        String bairro,
        String cep,
        String produto,
        LocalDate dataDaColeta,
        Double valorDeVenda,
        String unidadeDeMedida,
        String Bandeira) {

}

