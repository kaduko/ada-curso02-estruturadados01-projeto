package tech.ada.util;

public class FormatacaoUtil {
    public static void pularLinha(int numeroDeLinhas){
        for (int i = 1; i <= numeroDeLinhas; i++) {
            System.out.println();
        }
    }
}
