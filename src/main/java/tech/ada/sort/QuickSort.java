package tech.ada.sort;

import tech.ada.Revenda;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    private long qtdeIteracoes = 0;
    public List<Revenda> quicksort(List<Revenda> vetor, int esquerda, int direita){

        List<Revenda> vetorOrdenado = new ArrayList<>(vetor);

        if (esquerda < direita){
            int p = particao(vetorOrdenado, esquerda, direita);
            quicksort(vetorOrdenado, esquerda, p);
            quicksort(vetorOrdenado, p + 1, direita);
        }

        return vetorOrdenado;
    }

    private int particao(List<Revenda> vetor, int esquerda, int direita){
        int meio = (int) (esquerda + direita) / 2;
        Revenda pivot = vetor.get(meio);
        int i = esquerda - 1;
        int j = direita + 1;
        while(true){
            do{
                i++;
            }while(vetor.get(i).compareTo(pivot) < 0);
            do{
                j--;
            }while(vetor.get(j).compareTo(pivot) > 0);
            if (i >= j){
                return j;
            }
            Revenda aux = vetor.get(i);
            vetor.set(i, vetor.get(j));
            vetor.set(j, aux);
            qtdeIteracoes++;
        }
    }

    public long getQtdeIteracoes() {
        return qtdeIteracoes;
    }

    public void zerarContador(){
        this.qtdeIteracoes = 0;
    }
}
