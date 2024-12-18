# CPU-GURU
Uma interface em .java que utiliza quatro parâmetros para recomendar a melhor configuração de CPU ao usuário.

## OBJETIVO
Criar uma interface para auxiliar o usuário na escolha de um novo computador, ela recebe quatro parâmetros onde o usuário seleciona a finalidade, desempenho, consumo de energia e orçamento. Utilizando essas combinações de entradas ele indica a melhor configuração de CPU conforme uma base de dados limitada com as principais combinações.

## Instalação
### Baixar e Instalar o JDK
* Acesse o site oficial do [JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
* Escolha o instalador adequado para o seu sistema operacional.
* Abra o instalador e siga as instruções.
### Escolha uma IDE de preferência
* IntelliJ IDEA.
* Eclipse.
* VsCode.

### Clone o repositório
    https://github.com/nandaabreugit/CPU-GURU.git

### Compile e execute o código .java
* Compile o arquivo:
```java
javac recomendacaoCPU.java
```

* Agora execute o codigo já compilado:
```java
java recomendacaoCPU
```

## Como utilizar o programa
#### Execute a aplicação.
* Selecione critérios de:
    * Finalidade: Ex.: Game, Internet.
    * Orçamento: Baixo, Médio, Alto.
    * Desempenho: Baixo, Médio, Alto.
    * Consumo de Energia: Baixo, Médio, Alto.
* Clique em Recomendar para obter uma sugestão de CPU.
* Use Limpar para apagar a recomendação ou Fechar para sair.

## Detalhamento do Código:
#### Lógica do código
Foi utilizado um dicionário para armazenar as principais combinações de entradas e funcionar como um banco de dados limitado.

Exemplo de um caso:

```java
private Map<String, CPU> BaseDeDados(){
    Map<String, CPU> cpuMap = new HashMap<>();
    cpuMap.put("game-baixo-baixo-baixo", new CPU("AMD Athlon 3000G", "CISC", "64KB L1, 512KB L2", "3.5GHz", "Econômica e suficiente para jogos leves."));
    return cpuMap;
}   
```
Após isso foi criada uma classe para representar os dados da cpu indicada, chamada `Class CPU`.

```java
class CPU{
    private String modelo;
    private String arquitetura;
    private String cache;
    private String frequencia;
    private String justificativa;
}
```

E dentro desta classe foi feita uma função que busca dentro do banco de dados as unformações correspondentes aos seus componentes e foi criada também uma outra função que imprime essa informação com a seguinte estrutua:

Estrutura que imprime a recomendação:
```java
public String toString() {
    return "Modelo: " + modelo + "\n" +
            "Arquitetura: " + arquitetura + "\n" +
            "Cache: " + cache + "\n" +
            "Frequência: " + frequencia + "\n" +
            "Justificativa: " + justificativa;
}
```

#### Interface
Para a interface que foi o foco do projeto eu utilizei uma biblioteca da própria linguagem chamada `swing`, dentro dela é possível criar uma interface para que receba os parâmetros e gere a resposta no campo de texto logo abaixo.

Foram utilizadas checkbox para o usuário selecionar os parâmetros.

Como uma das maneiras de fazer uma interface mais fluida e dinâmica é indicado que o usuário clique a menor quantidade de vezes possível para se fazer a ação desejada. Anteriormente foi feito usando select mas como necessitava de dois cliques para selecionar a resposta desejada foi feita a mudança para checkbox.

#### Interface Geral:

![Interface Geral](interfacegeral.png)

Nesta interface só é aceito uma resposta por categoria e ao clicar no botão recomendar aparece todas as informações da CPU recomendada. Também tem os botões limpar o campo de recomendação e também para fechar a janela.

### Futuras melhorias
Para uma futura atualização seria legal adicionar mais funcionalidades como integração com alguma API de interligencia artificial para uma melhor recomendação e uma base de dados mais abrangente.