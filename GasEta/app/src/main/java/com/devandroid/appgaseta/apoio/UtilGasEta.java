package com.devandroid.appgaseta.apoio;

public class UtilGasEta {

    public void metdodoNaoEstatico() {
    }
    public static void metodoEstatico(){
    }
    public static String mesagem() {
        return "QualquerMesagem";
    }

    public static String calcularMelhorOpcao(double gasolina, double etanol){
        // preco da gasolina: 5,12
        // preco do etanol: 3,99

        // preco ideal gasolina = gasolina * 0.70 = R$3,548

        double precoIdeal = gasolina * 0.70;

        return etanol<=precoIdeal ? "Abastecer com Etanol": "Abastecer com Gasolina";
    }

}
