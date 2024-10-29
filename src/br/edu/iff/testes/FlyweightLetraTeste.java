package br.edu.iff.testes;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.texto.LetraTextoFactory;

public class FlyweightLetraTeste {

    private static final int NUM_INSTANCIAS = 1000000;
    private static final long BYTES_PARA_MB = 1024 * 1024;

    public static void main(String[] args) {
        System.out.println("Teste sem Flyweight:");
        long memoriaInicialSemFlyweight = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Letra[] letrasSemFlyweight = new Letra[NUM_INSTANCIAS];

        for (int i = 0; i < NUM_INSTANCIAS; i++) {
            letrasSemFlyweight[i] = new LetraSimples((char) ('a' + (i % 26)));
        }

        long memoriaFinalSemFlyweight = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        double usoMemoriaSemFlyweightMB = (memoriaFinalSemFlyweight - memoriaInicialSemFlyweight) / (double) BYTES_PARA_MB;
        System.out.printf("Uso de memória sem Flyweight: %.2f MB%n", usoMemoriaSemFlyweightMB);

        System.out.println("\nTeste com Flyweight:");
        long memoriaInicialComFlyweight = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Letra[] letrasComFlyweight = new Letra[NUM_INSTANCIAS];
        LetraTextoFactory factory = LetraTextoFactory.getSoleInstance();

        for (int i = 0; i < NUM_INSTANCIAS; i++) {
            letrasComFlyweight[i] = factory.getLetra((char) ('a' + (i % 26)));
        }

        long memoriaFinalComFlyweight = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        double usoMemoriaComFlyweightMB = (memoriaFinalComFlyweight - memoriaInicialComFlyweight) / (double) BYTES_PARA_MB;
        System.out.printf("Uso de memória com Flyweight: %.2f MB%n", usoMemoriaComFlyweightMB);

        double economiaDeMemoriaMB = usoMemoriaSemFlyweightMB - usoMemoriaComFlyweightMB;
        System.out.printf("\nEconomia de memória usando Flyweight: %.2f MB%n", economiaDeMemoriaMB);
    }
}
