# Gerador de Senhas

Este é um gerador de senhas seguro baseado em Java com uma interface gráfica (GUI) construída usando JavaFX. Ele permite que os usuários gerem senhas com opções para incluir números, letras maiúsculas e caracteres especiais.

## Funcionalidades
- Geração de senhas com comprimento personalizado.
- Opção de incluir/excluir números, letras maiúsculas e caracteres especiais.
- GUI desenvolvida com JavaFX.
- Copiar a senha gerada para a área de transferência.

## Configuração

### Pré-requisitos
- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versão 8 ou superior)
- [JavaFX](https://openjfx.io/) (necessário para a GUI)

### Como Executar

1. Clone o repositório:
    ```bash
    git clone https://github.com/HigorPereira10/GeradorDeSenhas.git
    cd GeradorDeSenhas
    ```

2. Compile e execute o programa:
    ```bash
    javac -cp "C:\Java\javafx-sdk-22.0.1\lib\*" src/Senha/*.java
    java -cp "C:\Java\javafx-sdk-22.0.1\lib\*;src" Senha.SenhaSeguraGUI
    ```

## Imagem da Interface

Veja a interface gráfica do programa:

![Interface](https://github.com/HigorPereira10/GeradorDeSenhas/blob/main/Interface.png)
