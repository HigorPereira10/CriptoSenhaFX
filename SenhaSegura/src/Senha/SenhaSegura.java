package Senha;

import java.security.SecureRandom;
import java.util.Scanner;

public class SenhaSegura {

    private static final String CARACTERES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789ç"
            + "!@#$%^&*()_+[]{}|;:'\",.<>/?`";

    public static String gerarSenha(int comprimento) {
        SecureRandom geradorDeNumeroAleatorio = new SecureRandom();
        StringBuilder senha = new StringBuilder(comprimento);

        for (int i = 0; i < comprimento; i++) {
            int indice = geradorDeNumeroAleatorio.nextInt(CARACTERES.length());
            senha.append(CARACTERES.charAt(indice));
        }

        return senha.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o comprimento da senha que deseja: ");
        int comprimentoSenha = scanner.nextInt();

        String senha = gerarSenha(comprimentoSenha);
        System.out.println("Senha gerada: " + senha);
    }
}
