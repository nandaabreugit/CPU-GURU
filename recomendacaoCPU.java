import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class recomendacaoCPU extends JFrame{

    private Map<String, CPU> cpuMap;

    public recomendacaoCPU(){
        setTitle("CPU Guru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        cpuMap = BaseDeDados();

        JMenuBar menu_bar =new JMenuBar();
        JMenu menu_arquivo =new JMenu("Home");
        JMenuItem item_salvar= new JMenuItem("Salvar");
        JMenuItem item_sair = new JMenuItem("Sair");

        menu_arquivo.add(item_salvar);
        menu_arquivo.add(item_sair);
        menu_bar.add(menu_arquivo);

        setJMenuBar(menu_bar);

        JPanel painel =new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel label_titulo =new JLabel("CPU Guru", JLabel.CENTER);
        label_titulo.setForeground(Color.BLUE);
        label_titulo.setFont(new Font("Arial", Font.BOLD, 18));
        label_titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(label_titulo);

        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painel_finalidade =new JPanel();
        painel_finalidade.setBorder(BorderFactory.createTitledBorder("Finalidade"));
        JCheckBox game_check = new JCheckBox("Game");
        JCheckBox internet_check= new JCheckBox("Internet");
        JCheckBox edicao_video_check= new JCheckBox("Edição de Vídeo");
        ButtonGroup group_finalidade =new ButtonGroup();
        group_finalidade.add(game_check);
        group_finalidade.add(internet_check);
        group_finalidade.add(edicao_video_check);
        painel_finalidade.add(game_check);
        painel_finalidade.add(internet_check);
        painel_finalidade.add(edicao_video_check);
        painel.add(painel_finalidade);

        JPanel painel_desempenho= new JPanel();
        painel_desempenho.setBorder(BorderFactory.createTitledBorder("Desempenho"));
        JCheckBox desempenho_baixo_check= new JCheckBox("Baixo");
        JCheckBox desempenho_medio_check= new JCheckBox("Médio");
        JCheckBox desempenho_alto_check= new JCheckBox("Alto");
        ButtonGroup group_desempenho= new ButtonGroup();
        group_desempenho.add(desempenho_baixo_check);
        group_desempenho.add(desempenho_medio_check);
        group_desempenho.add(desempenho_alto_check);
        painel_desempenho.add(desempenho_baixo_check);
        painel_desempenho.add(desempenho_medio_check);
        painel_desempenho.add(desempenho_alto_check);
        painel.add(painel_desempenho);

        JPanel painel_consumo = new JPanel();
        painel_consumo.setBorder(BorderFactory.createTitledBorder("Consumo de Energia"));
        JCheckBox consumo_baixo_check = new JCheckBox("Baixo");
        JCheckBox consumo_medio_check= new JCheckBox("Médio");
        JCheckBox consumo_alto_check= new JCheckBox("Alto");
        ButtonGroup groupConsumo= new ButtonGroup();
        groupConsumo.add(consumo_baixo_check);
        groupConsumo.add(consumo_medio_check);
        groupConsumo.add(consumo_alto_check);
        painel_consumo.add(consumo_baixo_check);
        painel_consumo.add(consumo_medio_check);
        painel_consumo.add(consumo_alto_check);
        painel.add(painel_consumo);

        JPanel painel_orcamento= new JPanel();
        painel_orcamento.setBorder(BorderFactory.createTitledBorder("Orçamento"));
        JCheckBox orcamento_baixo_check= new JCheckBox("Baixo");
        JCheckBox orcamento_medio_check= new JCheckBox("Médio");
        JCheckBox orcamento_alto_check = new JCheckBox("Alto");
        ButtonGroup group_orcamento = new ButtonGroup();
        group_orcamento.add(orcamento_baixo_check);
        group_orcamento.add(orcamento_medio_check);
        group_orcamento.add(orcamento_alto_check);
        painel_orcamento.add(orcamento_baixo_check);
        painel_orcamento.add(orcamento_medio_check);
        painel_orcamento.add(orcamento_alto_check);
        painel.add(painel_orcamento);

        JLabel labelResultado = new JLabel("Recomendação de CPU:");
        JTextArea textAreaResultado = new JTextArea(5, 20);
        textAreaResultado.setEditable(false);
        JScrollPane scrollPaneResultado = new JScrollPane(textAreaResultado);

        painel.add(labelResultado);
        painel.add(scrollPaneResultado);

        JPanel painel_botoes =new JPanel(new FlowLayout());
        JButton buttonRecomendar= new JButton("Recomendar");
        JButton buttonLimpar= new JButton("Limpar");
        JButton buttonFechar= new JButton("Fechar");

        painel_botoes.add(buttonRecomendar);
        painel_botoes.add(buttonLimpar);
        painel_botoes.add(buttonFechar);

        painel.add(painel_botoes);

        buttonRecomendar.addActionListener(_ ->{
            StringBuilder chave = new StringBuilder();

            if(game_check.isSelected()){
                chave.append("game-");
            }else if(internet_check.isSelected()){
                chave.append("internet-");
            }else if(edicao_video_check.isSelected()){
                chave.append("edição de vídeo-");
            }

            if(orcamento_baixo_check.isSelected()){
                chave.append("baixo-");
            }else if(orcamento_medio_check.isSelected()){
                chave.append("médio-");
            }else if(orcamento_alto_check.isSelected()){
                chave.append("alto-");
            } 

            if(desempenho_baixo_check.isSelected()){
                chave.append("baixo-");
            }else if(desempenho_medio_check.isSelected()){
                chave.append("médio-");
            }else if(desempenho_alto_check.isSelected()){
                chave.append("alto-");
            }

            if (consumo_baixo_check.isSelected()){
                chave.append("baixo");
            }else if(consumo_medio_check.isSelected()){
                chave.append("médio");
            }else if(consumo_alto_check.isSelected()){
                chave.append("alto");
            }

            CPU recomendacao= cpuMap.getOrDefault(chave.toString(), null);

            if(recomendacao != null){
                textAreaResultado.setText(recomendacao.toString());
            }else{
                textAreaResultado.setText("Nenhuma recomendação encontrada para os critérios selecionados.");
            }
        });

        buttonLimpar.addActionListener(_ ->textAreaResultado.setText(""));

        buttonFechar.addActionListener(_ ->System.exit(0));

        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }

    private Map<String, CPU> BaseDeDados(){
        Map<String, CPU> cpuMap= new HashMap<>();
        cpuMap.put("game-baixo-baixo-baixo", new CPU("AMD Athlon 3000G", "CISC", "64KB L1, 512KB L2", "3.5GHz", "Econômica e suficiente para jogos leves."));
        cpuMap.put("game-baixo-médio-baixo", new CPU("AMD Ryzen 3 3200G", "CISC", "64KB L1, 512KB L2", "3.6GHz", "Boa performance para jogos leves com custo reduzido."));
        cpuMap.put("game-médio-médio-baixo", new CPU("AMD Ryzen 5 4600G", "CISC", "128KB L1, 2MB L2", "3.7GHz", "Versátil para jogos intermediários e multitarefas."));
        cpuMap.put("game-médio-alto-médio", new CPU("Intel Core i5-12400", "CISC", "192KB L1, 3MB L2", "2.5GHz", "Desempenho equilibrado para jogos e consumo de energia moderado."));
        cpuMap.put("game-alto-alto-alto", new CPU("Intel Core i7-12700K", "CISC", "256KB L1, 12MB L2", "3.6GHz", "Altíssimo desempenho para jogos exigentes."));
        cpuMap.put("game-alto-médio-médio", new CPU("AMD Ryzen 7 5800X", "CISC", "256KB L1, 4MB L2", "3.8GHz", "Ótima escolha para jogos exigentes com preço competitivo."));
        cpuMap.put("game-alto-baixo-alto", new CPU("Intel Core i9-12900K", "CISC", "512KB L1, 14MB L2", "3.9GHz", "Máxima performance com eficiência energética reduzida."));
        cpuMap.put("trabalho-baixo-baixo-baixo", new CPU("Intel Celeron G5905", "CISC", "32KB L1, 256KB L2", "3.5GHz", "Adequado para trabalho leve e navegação básica."));
        cpuMap.put("trabalho-baixo-médio-baixo", new CPU("Intel Pentium Gold G6400", "CISC", "64KB L1, 512KB L2", "4.0GHz", "Suficiente para trabalho e tarefas simples."));
        cpuMap.put("trabalho-médio-médio-médio", new CPU("AMD Ryzen 5 5500", "CISC", "128KB L1, 2MB L2", "3.6GHz", "Ideal para multitarefas e produtividade geral."));
        cpuMap.put("trabalho-médio-alto-médio", new CPU("Intel Core i5-11400F", "CISC", "192KB L1, 3MB L2", "2.6GHz", "Excelente para produtividade intermediária."));
        cpuMap.put("trabalho-alto-alto-alto", new CPU("AMD Ryzen 9 5950X", "CISC", "512KB L1, 8MB L2", "4.1GHz", "Ótimo para tarefas avançadas e cargas de trabalho intensas."));
        cpuMap.put("trabalho-alto-médio-alto", new CPU("Intel Core i7-12700", "CISC", "256KB L1, 12MB L2", "3.6GHz", "Alta eficiência e desempenho."));
        cpuMap.put("trabalho-alto-baixo-médio", new CPU("Intel Xeon W-1290", "CISC", "1MB L1, 12MB L2", "3.2GHz", "Voltado para profissionais exigentes."));
        cpuMap.put("internet-baixo-baixo-baixo", new CPU("Intel Pentium Gold G6400", "CISC", "64KB L1, 512KB L2", "4.0GHz", "Ideal para navegação e tarefas simples."));
        cpuMap.put("internet-baixo-médio-baixo", new CPU("AMD Athlon Silver 3050U", "CISC", "64KB L1, 512KB L2", "3.2GHz", "Suficiente para navegação básica e streaming."));
        cpuMap.put("internet-médio-médio-médio", new CPU("Intel Core i3-10100", "CISC", "64KB L1, 1MB L2", "3.6GHz", "Bom para navegação intermediária."));
        cpuMap.put("internet-médio-alto-médio", new CPU("AMD Ryzen 3 4100", "CISC", "128KB L1, 2MB L2", "3.8GHz", "Ótima escolha para navegação e multitarefas."));
        cpuMap.put("internet-alto-alto-alto", new CPU("Intel Core i5-12500", "CISC", "192KB L1, 3MB L2", "4.1GHz", "Perfeito para uso intensivo e multitarefas avançadas."));
        cpuMap.put("internet-alto-médio-alto", new CPU("AMD Ryzen 5 5600X", "CISC", "256KB L1, 3MB L2", "4.0GHz", "Rápido e eficiente para todas as necessidades online."));
        cpuMap.put("internet-alto-baixo-médio", new CPU("Intel Core i7-10700", "CISC", "256KB L1, 2MB L2", "2.9GHz", "Bom desempenho com economia de energia."));
        cpuMap.put("game-médio-baixo-baixo", new CPU("Intel Core i3-10105", "CISC", "64KB L1, 1MB L2", "3.7GHz", "Básico para jogos e tarefas secundárias."));
        cpuMap.put("game-médio-alto-alto", new CPU("AMD Ryzen 5 7600", "CISC", "256KB L1, 6MB L2", "4.2GHz", "Capaz de lidar com gráficos modernos."));
        cpuMap.put("trabalho-baixo-alto-baixo", new CPU("AMD Ryzen 3 5300G", "CISC", "64KB L1, 512KB L2", "3.9GHz", "Equilibrado para produtividade leve."));
        cpuMap.put("internet-baixo-baixo-médio", new CPU("AMD Athlon 300GE", "CISC", "64KB L1, 512KB L2", "3.2GHz", "Opção econômica para tarefas diárias."));
        cpuMap.put("trabalho-médio-baixo-alto", new CPU("Intel Core i5-11500", "CISC", "256KB L1, 6MB L2", "3.4GHz", "Excelente para tarefas multitarefa."));
        cpuMap.put("game-baixo-baixo-médio", new CPU("Intel Pentium G5600", "CISC", "64KB L1, 1MB L2", "3.9GHz", "Adequado para gráficos simples."));
        cpuMap.put("trabalho-alto-médio-baixo", new CPU("Intel Xeon E-2276G", "CISC", "1MB L1, 6MB L2", "3.8GHz", "Voltado para profissionais em escritórios."));
        cpuMap.put("internet-médio-baixo-baixo", new CPU("Intel Celeron N5105", "CISC", "32KB L1, 256KB L2", "2.0GHz", "Suficiente para e-mails e tarefas online básicas."));
        cpuMap.put("internet-alto-alto-baixo", new CPU("AMD Ryzen 7 7700", "CISC", "512KB L1, 8MB L2", "4.5GHz", "Ultraeficiente para multitarefas pesadas."));
        cpuMap.put("game-baixo-médio-médio", new CPU("AMD Ryzen 3 PRO 4350G", "CISC", "128KB L1, 2MB L2", "3.8GHz", "Boa performance para jogos leves e multitarefas."));
        cpuMap.put("game-médio-baixo-médio", new CPU("Intel Core i3-12100F", "CISC", "128KB L1, 2MB L2", "3.3GHz", "Adequado para jogos intermediários com orçamento controlado."));
        cpuMap.put("game-alto-baixo-baixo", new CPU("Intel Core i5-12400F", "CISC", "192KB L1, 3MB L2", "2.5GHz", "Opção econômica para jogos de alto desempenho."));
        cpuMap.put("trabalho-baixo-baixo-médio", new CPU("Intel Celeron J4125", "CISC", "32KB L1, 512KB L2", "2.7GHz", "Opção simples para trabalho e consumo eficiente de energia."));
        cpuMap.put("trabalho-médio-médio-alto", new CPU("AMD Ryzen 5 5600", "CISC", "256KB L1, 3MB L2", "3.7GHz", "Desempenho sólido para produtividade e multitarefas."));
        cpuMap.put("trabalho-alto-baixo-baixo", new CPU("Intel Core i7-10700F", "CISC", "256KB L1, 8MB L2", "2.9GHz", "Bom desempenho para profissionais com menor custo."));
        cpuMap.put("internet-baixo-médio-médio", new CPU("AMD Ryzen 3 2200G", "CISC", "64KB L1, 512KB L2", "3.5GHz", "Excelente para streaming e navegação leve."));
        cpuMap.put("internet-médio-alto-alto", new CPU("Intel Core i5-12600K", "CISC", "256KB L1, 9MB L2", "4.2GHz", "Ótimo para multitarefas e consumo eficiente."));
        cpuMap.put("internet-alto-baixo-baixo", new CPU("AMD Ryzen 5 4500", "CISC", "256KB L1, 3MB L2", "3.6GHz", "Ideal para usuários avançados com orçamento limitado."));
        cpuMap.put("game-alto-alto-médio", new CPU("AMD Ryzen 7 7700X", "CISC", "512KB L1, 6MB L2", "4.5GHz", "Desempenho excepcional para jogos de última geração."));
        cpuMap.put("game-médio-médio-alto", new CPU("Intel Core i5-11600KF", "CISC", "256KB L1, 12MB L2", "4.1GHz", "Ótimo para jogos de gráficos avançados e multitarefas."));
        cpuMap.put("trabalho-médio-baixo-médio", new CPU("Intel Core i5-10400", "CISC", "128KB L1, 2MB L2", "2.9GHz", "Equilibrado para produtividade básica e eficiência energética."));
        cpuMap.put("internet-baixo-alto-baixo", new CPU("Intel Pentium G5420", "CISC", "64KB L1, 512KB L2", "3.8GHz", "Opção econômica para tarefas simples com bom desempenho."));
        cpuMap.put("trabalho-alto-alto-médio", new CPU("AMD Ryzen 9 7900", "CISC", "512KB L1, 8MB L2", "4.7GHz", "Excelente para projetos profissionais e carga de trabalho pesada."));
        cpuMap.put("game-médio-baixo-alto", new CPU("AMD Ryzen 5 5600G", "CISC", "256KB L1, 3MB L2", "3.9GHz", "Desempenho intermediário para jogos e multitarefas."));
        cpuMap.put("internet-médio-médio-baixo", new CPU("Intel Core i3-10100F", "CISC", "64KB L1, 1MB L2", "3.6GHz", "Ideal para navegação e uso geral com orçamento limitado."));
        cpuMap.put("trabalho-alto-médio-médio", new CPU("Intel Xeon W-1250", "CISC", "1MB L1, 8MB L2", "3.3GHz", "Ótima escolha para tarefas de escritório e multitarefas."));
        cpuMap.put("game-alto-médio-alto", new CPU("AMD Ryzen 7 5800X3D", "CISC", "512KB L1, 6MB L2", "4.4GHz", "Excelente para jogos e streaming com desempenho extremo."));
        cpuMap.put("internet-alto-médio-médio", new CPU("Intel Core i5-11400H", "CISC", "192KB L1, 2MB L2", "3.2GHz", "Versátil para navegação, streaming e tarefas de escritório."));
        cpuMap.put("game-baixo-alto-alto", new CPU("AMD Ryzen 5 3600", "CISC", "192KB L1, 3MB L2", "4.2GHz", "Boa performance para jogos leves e orçamento ajustado."));

        return cpuMap;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(recomendacaoCPU::new);
    }

    class CPU{
        private String modelo;
        private String arquitetura;
        private String cache;
        private String frequencia;
        private String justificativa;

        public CPU(String modelo, String arquitetura, String cache, String frequencia, String justificativa){
            this.modelo = modelo;
            this.arquitetura = arquitetura;
            this.cache = cache;
            this.frequencia = frequencia;
            this.justificativa = justificativa;
        }

        @Override
        public String toString(){
            return "Modelo: " + modelo + "\n" + "Arquitetura: " + arquitetura + "\n" + "Cache: " + cache + "\n" + "Frequência: " + frequencia + "\n" + "Justificativa: " + justificativa;
        }
    }
}
