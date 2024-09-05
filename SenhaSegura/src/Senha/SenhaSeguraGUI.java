package Senha;

import java.security.SecureRandom;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class SenhaSeguraGUI extends Application {

    @Override
    public void start(Stage palco) {
        palco.setTitle("Gerador de Senhas");

        // Label para o tamanho da senha
        Label labelTamanhoSenha = new Label("Tamanho Da Senha: ");
        labelTamanhoSenha.setStyle("-fx-font-size: 14px; -fx-text-fill: #f0f0f0;");

        // Campo de texto para o tamanho da senha
        TextField campoTamanhoSenha = new TextField();
        campoTamanhoSenha.setText("8");
        campoTamanhoSenha.setStyle("-fx-background-color: #333; -fx-text-fill: #fff; -fx-font-size: 12px;");

        // Rótulo e campo para exibir a senha gerada
        Label labelSenhaGerada = new Label("Senha Gerada");
        labelSenhaGerada.setStyle("-fx-font-size: 14px; -fx-text-fill: #f0f0f0;");
        TextField campoSenhaGerada = new TextField();
        campoSenhaGerada.setEditable(false);
        campoSenhaGerada.setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: #00ffcc; -fx-font-weight: bold;");

        // Botão para gerar a senha
        Button botaoGerar = new Button("Gerar Senha");
        botaoGerar.setStyle(
                "-fx-background-color: #00796b; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; " +
                "-fx-border-radius: 5px; -fx-background-radius: 5px;"
        );

        // Botão para copiar a senha
        Button botaoCopiar = new Button("Copiar Senha");
        botaoCopiar.setDisable(true);  // Desativado até que uma senha seja gerada
        botaoCopiar.setStyle(
                "-fx-background-color: #00796b; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand; " +
                "-fx-border-radius: 5px; -fx-background-radius: 5px;"
        );

        // Opções para selecionar a complexidade da senha
        CheckBox incluirNumeros = new CheckBox("Incluir Números");
        CheckBox incluirMaiusculas = new CheckBox("Incluir Letras Maiúsculas");
        CheckBox incluirEspeciais = new CheckBox("Incluir Caracteres Especiais");
        incluirNumeros.setSelected(true);  // Padrão: números inclusos
        incluirMaiusculas.setSelected(true);  // Padrão: letras maiúsculas inclusas
        incluirEspeciais.setSelected(true);  // Padrão: caracteres especiais inclusos

        // Evento de clique no botão "Gerar Senha" com validação de entrada e geração da senha
        botaoGerar.setOnAction(e -> {
            try {
                int tamanhoSenha = Integer.parseInt(campoTamanhoSenha.getText());
                if (tamanhoSenha <= 0 || tamanhoSenha > 100) {  // Limitar o tamanho da senha
                    campoSenhaGerada.setText("Insira um número entre 1 e 100!");
                } else {
                    String senha = GeradorDeSenha.gerarSenha(tamanhoSenha, incluirNumeros.isSelected(),
                            incluirMaiusculas.isSelected(), incluirEspeciais.isSelected());
                    campoSenhaGerada.setText(senha);
                    botaoCopiar.setDisable(false);  // Ativar botão de copiar quando a senha for gerada
                }
            } catch (NumberFormatException ex) {
                campoSenhaGerada.setText("Insira um número válido!");
            }
        });

        // Evento de clique no botão "Copiar Senha"
        botaoCopiar.setOnAction(e -> {
            String senha = campoSenhaGerada.getText();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(senha);
            clipboard.setContent(content);
        });

        // Layout GridPane para melhor distribuição dos componentes
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Componentes do layout
        grid.add(labelTamanhoSenha, 0, 0);
        grid.add(campoTamanhoSenha, 1, 0);
        grid.add(incluirNumeros, 0, 1);
        grid.add(incluirMaiusculas, 1, 1);
        grid.add(incluirEspeciais, 2, 1);
        grid.add(botaoGerar, 0, 2);
        grid.add(labelSenhaGerada, 0, 3);
        grid.add(campoSenhaGerada, 1, 3, 2, 1);
        grid.add(botaoCopiar, 1, 4);

        // Definindo a cena e aplicando o estilo do tema escuro
        Scene cena = new Scene(grid, 550, 250);
        grid.setStyle("-fx-background-color: #121212;");

        palco.setScene(cena);
        palco.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Classe para geração da senha, com base nas opções selecionadas
class GeradorDeSenha {

    // Conjunto de caracteres para as opções
    private static final String LETRAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String LETRAS_MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "1234567890";
    private static final String ESPECIAIS = "!@#$%^&*()_+[]{}|;:'\",.<>/?`";

    public static String gerarSenha(int comprimento, boolean incluirNumeros, boolean incluirMaiusculas, boolean incluirEspeciais) {
        StringBuilder caracteresPermitidos = new StringBuilder(LETRAS);  // Conjunto padrão: letras minúsculas
        if (incluirNumeros) caracteresPermitidos.append(NUMEROS);
        if (incluirMaiusculas) caracteresPermitidos.append(LETRAS_MAIUSCULAS);
        if (incluirEspeciais) caracteresPermitidos.append(ESPECIAIS);

        SecureRandom geradorDeNumeroAleatorio = new SecureRandom();
        StringBuilder senha = new StringBuilder(comprimento);

        for (int i = 0; i < comprimento; i++) {
            int indice = geradorDeNumeroAleatorio.nextInt(caracteresPermitidos.length());
            senha.append(caracteresPermitidos.charAt(indice));
        }

        return senha.toString();
    }
}
