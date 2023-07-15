package tech.ada;

import tech.ada.util.FormatacaoUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MenuPrincipal {
    private List<PrecoCombustivel> linkedListPrecoCombustiveis = new LinkedList<>();

    private List<PrecoCombustivel> arrayPrecoCombustiveis = new ArrayList<>();
    private List<Revenda> arrayRevendas = new ArrayList<>();
    private List<Revenda> arrayRevendasListaOrdenada;

    private final EntradaDeDados leitor;
    private final String DIGITE_OPCAO_DESEJADA = "Digite a opção desejada: ";
    private final String OPCAO_SAIR = "x";
    private final String OPCAO_CARREGAR_EM_LOTE = "1";
    private final String OPCAO_LISTAR_PRECO_COMBUSTIVEL = "2";
    private final String OPCAO_ORDENACAO = "3";
    private final String OPCAO_VOLTAR = "v";
    private final String OPCAO_ORDENACAO_POR_NOME = "1";
    private final String OPCAO_LISTA_REVENDA_ORDENADA = "2";
    private String subMenu = "";




    public MenuPrincipal(EntradaDeDados leitor){
        this.leitor = leitor;
        iniciaApp();
    }

    public void processar(){

            String opcaoDigitada = obterEntradaDoUsuario(leitor);

            while(!escolheuSair(opcaoDigitada)){
                switch (subMenu){
                    case "":
                        tratarOpcaoSelecionada(opcaoDigitada);
                        break;
                    case OPCAO_ORDENACAO:
                        tratarOpcaoSelecionadaSubmenuOrdenacao(opcaoDigitada);
                        break;
                }
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
            case OPCAO_ORDENACAO:
                subMenu = OPCAO_ORDENACAO;
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void tratarOpcaoSelecionadaSubmenuOrdenacao(String opcaoDigitada) {
        switch (opcaoDigitada){
            case OPCAO_VOLTAR:
                subMenu = "";
                break;
            case OPCAO_ORDENACAO_POR_NOME:
                System.out.println("==== Ordenação por Nome da Revenda ====");
                System.out.println("... Eliminando Revendas Duplicadas ...");
                eliminaRevendasDuplicadas();
                System.out.println("... Eliminação Feita");
                System.out.println("=== Ordenando Revendas por BubleSort ===");
                ordenaRevendasPorBubleSort();
                System.out.println("=== Ordenação Feita");
                break;
            case OPCAO_LISTA_REVENDA_ORDENADA:
                listarRevendasOrdenadas();
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void ordenaRevendasPorBubleSort() {
        long inicio = System.currentTimeMillis();
        long fim;
        long qtdeIteracoes = 0;

        arrayRevendasListaOrdenada = arrayRevendas;

        //BUBBLE SORT O(N^2)
        Revenda auxRevenda;
        for(int i = 0; i < arrayRevendasListaOrdenada.size(); i++){ // O(N)
            for(int j = i + 1; j < arrayRevendasListaOrdenada.size(); j++){ //O(N)
                if(arrayRevendasListaOrdenada.get(i).revenda()
                        .compareTo(arrayRevendasListaOrdenada.get(j).revenda()) > 0)//i is greater j
                {
                    auxRevenda = arrayRevendasListaOrdenada.get(i);
                    arrayRevendasListaOrdenada.set(i, arrayRevendasListaOrdenada.get(j));
                    arrayRevendasListaOrdenada.set(j, auxRevenda);
                    qtdeIteracoes++;
                }
            }
        }
        fim = System.currentTimeMillis();
        System.out.println(".. Algoritmo Bublesort ..");
        System.out.println(".. Tempo em ms: " + (fim-inicio) + " ..");
        System.out.println(".. Quantidade Iterações: " + qtdeIteracoes);
    }

    private void eliminaRevendasDuplicadas() {
        System.out.println("... Quantidade de revendas antes da eliminação: " + arrayRevendas.size());
        List<Revenda> revendasSemDuplicidade = new ArrayList<>(new HashSet<>(arrayRevendas));
        arrayRevendas = revendasSemDuplicidade;
        System.out.println("... Quantidade de revendas após eliminação: " + arrayRevendas.size());
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
        this.linkedListPrecoCombustiveis.add(precoCombustivel);
//        try {
//            this.arrayPrecoCombustiveis.add(Integer.parseInt(precoCombustivel.id().toString()), precoCombustivel);
//        } catch (IndexOutOfBoundsException e){
//            System.out.println("Erro no registro: " + precoCombustivel);
//        }
        this.arrayPrecoCombustiveis.add(precoCombustivel);
        this.arrayRevendas.add(new Revenda(precoCombustivel));
    }


    private void listarPrecoCombustivel(){
        StringBuilder sb = new StringBuilder();

        FormatacaoUtil.pularLinha(1);
        if (linkedListPrecoCombustiveis.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[\n");
            for (PrecoCombustivel precoCombustivel : linkedListPrecoCombustiveis) {
                sb.append("\t").append(precoCombustivel).append(",\n");
            }
            sb.setLength(sb.length() - 2); // Remover a vírgula extra após o último funcionário
            sb.append("\n]");
        }

        System.out.println(sb);

        FormatacaoUtil.pularLinha(2);
    }

    private void listarRevendasOrdenadas(){
        StringBuilder sb = new StringBuilder();

        FormatacaoUtil.pularLinha(1);
        if (arrayRevendasListaOrdenada.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[\n");
            for (Revenda revenda : arrayRevendasListaOrdenada) {
                sb.append("\t").append(revenda).append(",\n");
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
        switch (subMenu){
            case "":
                carregaMenu();
                subMenu = "";
                break;
            case OPCAO_ORDENACAO:
                carregaSubMenuOrdenacao();
        }

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
        System.out.println("3 - ORDENAÇÃO DA LISTA DE PREÇOS");
        System.out.println("X - SAIR");
    }

    private void carregaSubMenuOrdenacao() {
        System.out.println("********  DIGITE A OPÇÃO DESEJADA - SUBMENU ORDENAÇÃO   ******");
        System.out.println("1 - ORDERNAR POR NOME (Revenda)");
        System.out.println("2 - LISTAR REVENDAS ORDENADAS");
//        System.out.println("3 - LISTAR REVENDAS SEM DUPLICIDADE");
        System.out.println("V - VOLTAR MENU PRINCIPAL");
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
