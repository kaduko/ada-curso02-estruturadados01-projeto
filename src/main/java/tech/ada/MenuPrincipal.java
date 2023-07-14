package tech.ada;

import tech.ada.util.FormatacaoUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MenuPrincipal {
    private int ultimoIdFuncionario = 0;
    private List<PrecoCombustivel> listaDePrecoCombustiveis = new LinkedList<>();

    private final EntradaDeDados leitor;
    private final String DIGITE_OPCAO_DESEJADA = "Digite a opção desejada: ";
    private final String OPCAO_SAIR = "x";
    private final String OPCAO_CARREGAR_EM_LOTE = "1";
    private final String OPCAO_LISTAR_PRECO_COMBUSTIVEL = "2";

    public MenuPrincipal(EntradaDeDados leitor){
        this.leitor = leitor;
        iniciaApp();
    }

    public void processar(){

            String opcaoDigitada = obterEntradaDoUsuario(leitor);

            while(!escolheuSair(opcaoDigitada)){
                tratarOpcaoSelecionada(opcaoDigitada);
                opcaoDigitada = obterEntradaDoUsuario(leitor);
            }

        finalizaApp();

    }

    private void tratarOpcaoSelecionada(String opcaoDigitada) {
        switch (opcaoDigitada){
            case OPCAO_SAIR:
                break;
            case OPCAO_LISTAR_PRECO_COMBUSTIVEL:
                listarPrecoCombustivel();
                break;
            case OPCAO_CARREGAR_EM_LOTE:
                carregarFuncionariosEmLote();
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void carregarFuncionariosEmLote(){
        List<PrecoCombustivel> novosPrecoCombustivels =
                new CarregarDadosExternos().carregaArquivoCSV();

        this.inserirPrecoCombustivel(novosPrecoCombustivels);

        FormatacaoUtil.pularLinha(2);

    }

    private void inserirPrecoCombustivel(List<PrecoCombustivel> precoCombustivels){
        for (PrecoCombustivel precoCombustivel : precoCombustivels){
            inserirPrecoCombustivel(precoCombustivel);
        }
    }

    private void inserirPrecoCombustivel(PrecoCombustivel precoCombustivel){
        this.listaDePrecoCombustiveis.add(precoCombustivel);
    }


    private void listarPrecoCombustivel(){
        StringBuilder sb = new StringBuilder();

        FormatacaoUtil.pularLinha(1);
        if (listaDePrecoCombustiveis.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[\n");
            for (PrecoCombustivel precoCombustivel : listaDePrecoCombustiveis) {
                sb.append("\t").append(precoCombustivel).append(",\n");
            }
            sb.setLength(sb.length() - 2); // Remover a vírgula extra após o último funcionário
            sb.append("\n]");
        }

        System.out.println(sb);

        FormatacaoUtil.pularLinha(2);
    }

    private boolean escolheuSair(String opcaoDigitada){
        return opcaoDigitada.equals(OPCAO_SAIR);
    }

    private String obterEntradaDoUsuario(EntradaDeDados leitor){
        carregaMenu();
        System.out.print(DIGITE_OPCAO_DESEJADA);
        return leitor.obterEntrada().toLowerCase();
    }

    private void finalizaApp(){
        FormatacaoUtil.pularLinha(1);
        System.out.println("BYE, BYE!!");
    }

    private void opcaoInvalida(){
        FormatacaoUtil.pularLinha(1);
        System.out.println("Opção INVÁLIDA. Tente novamente.");
        FormatacaoUtil.pularLinha(2);
    }

    private void iniciaApp(){
        carregaNomeApp();
    }

    private void carregaMenu() {
        System.out.println("********  DIGITE A OPÇÃO DESEJADA   ******");
        System.out.println("1 - CARREGAR EM LOTE (CSV)");
        System.out.println("2 - LISTAR PREÇOS DE COMBUSTÍVEIS");
        System.out.println("X - SAIR");
    }

    private void carregaNomeApp(){
        System.out.println("*********************************************************");
        System.out.println("*******  PROJETO DO CURSO ESTRUTURA DE DADOS 1  *********");
        System.out.println("******* PREÇO DE COMBUSTÍVEL - 2o SEMESTRE 2022 *********");
        System.out.println("*********************************************************");
        FormatacaoUtil.pularLinha(1);
    }

}
