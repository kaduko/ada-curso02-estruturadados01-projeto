package tech.ada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CarregarDadosExternos {

    private static String CAMINHO_ARQUIVO = "src/main/resources/ca-2022-02-v2.csv";

    public List<PrecoCombustivel> carregaArquivoCSV(){

        List<PrecoCombustivel> precoCombustivels = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {

            br.readLine(); // Remover o cabeçalho

            String linha;
            int countLinha = 0;

            while ((linha = br.readLine()) != null) {
                countLinha++;
                System.out.println("Linha: " + countLinha);
                String[] dados = linha.split(";");

                String regiaoSigla = dados[0].trim();
                String estadoSigla = dados[1].trim();
                String municipio = dados[2].trim();
                String revenda = dados[3].trim();

                String cnpjDaRevenda = dados[4].trim()
                        .replace(".", "")
                        .replace("/", "")
                        .replace("-", "");

                String nomeDaRua = dados[5].trim();
                String numeroRua = dados[6].trim();
                String complemento = dados[7].trim();
                String bairro = dados[8].trim();
                String cep = dados[9].trim();
                String produto = dados[10].trim();
                String dataDado = dados[11].trim();

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataDaColeta = LocalDate.parse(dataDado, dateTimeFormatter);

                String valor = dados[12].trim().replace(",", ".");
                Double valorDeVenda = Double.parseDouble(valor);

                String unidadeDeMedida = dados[13].trim();
                String Bandeira = dados[14].trim();

                PrecoCombustivel precoCombustivel = new PrecoCombustivel(
                        Long.valueOf(countLinha),
                        regiaoSigla,
                        estadoSigla,
                        municipio,
                        revenda,
                        cnpjDaRevenda,
                        nomeDaRua,
                        numeroRua,
                        complemento,
                        bairro,
                        cep,
                        produto,
                        dataDaColeta,
                        valorDeVenda,
                        unidadeDeMedida,
                        Bandeira);
                precoCombustivels.add(precoCombustivel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return precoCombustivels;

    }

}
