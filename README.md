# CPU-GURU
Uma interface em .java que utiliza quatro parâmetros para recomendar a melhor configuração de CPU ao usuário.

## OBJETIVO
Criar uma interface para auxiliar o usuário na escolha de um novo computador, ela recebe quatro parâmetros onde o usuário seleciona a finalidade, desempenho, consumo de energia e orçamento. Utilizando essas combinações de entradas ele indica a melhor configuração de CPU conforme uma base de dados limitada com as principais combinações.

## Detalhamento do Código:
#### Lógica do código
Foi utilizado um dicionário para armazenar as principais combinações de entradas e funcionar como um banco de dados limitado.

```java
    private Map<String, CPU> BaseDeDados(){
        Map<String, CPU> cpuMap = new HashMap<>();
        cpuMap.put("game-baixo-baixo-baixo", new CPU("AMD Athlon 3000G", "CISC", "64KB L1, 512KB L2", "3.5GHz", "Econômica e suficiente para jogos leves."));
        return cpuMap;
    }
