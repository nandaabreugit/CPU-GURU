import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class recomendacaoCPU extends JFrame {

    private Map<String, CPU> cpuMap;

    public recomendacaoCPU() {
        setTitle("CPU Guru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        cpuMap = BaseDeDados();

        JMenuBar menu_bar = new JMenuBar();
        JMenu menu_arquivo = new JMenu("Arquivo");
        JMenuItem item_salvar = new JMenuItem("Salvar");
        JMenuItem item_sair = new JMenuItem("Sair");

        menu_arquivo.add(item_salvar);
        menu_arquivo.add(item_sair);
        menu_bar.add(menu_arquivo);

        setJMenuBar(menu_bar);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel label_titulo = new JLabel("CPU Guru", JLabel.CENTER);
        label_titulo.setForeground(Color.BLUE);
        label_titulo.setFont(new Font("Arial", Font.BOLD, 18));
        label_titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(label_titulo);

        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painel_finalidade = new JPanel();
        painel_finalidade.setBorder(BorderFactory.createTitledBorder("Finalidade"));
        JCheckBox game_check = new JCheckBox("Game");
        JCheckBox internet_check = new JCheckBox("Internet");
        JCheckBox edicao_video_check = new JCheckBox("Edição de Vídeo");
        ButtonGroup group_finalidade = new ButtonGroup();
        group_finalidade.add(game_check);
        group_finalidade.add(internet_check);
        group_finalidade.add(edicao_video_check);
        painel_finalidade.add(game_check);
        painel_finalidade.add(internet_check);
        painel_finalidade.add(edicao_video_check);
        painel.add(painel_finalidade);

        JPanel painel_desempenho = new JPanel();
        painel_desempenho.setBorder(BorderFactory.createTitledBorder("Desempenho"));
        JCheckBox desempenho_baixo_check = new JCheckBox("Baixo");
        JCheckBox desempenho_medio_check = new JCheckBox("Médio");
        JCheckBox desempenho_alto_check = new JCheckBox("Alto");
        ButtonGroup group_desempenho = new ButtonGroup();
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
        JCheckBox consumo_medio_check = new JCheckBox("Médio");
        JCheckBox consumo_alto_check = new JCheckBox("Alto");
        ButtonGroup groupConsumo = new ButtonGroup();
        groupConsumo.add(consumo_baixo_check);
        groupConsumo.add(consumo_medio_check);
        groupConsumo.add(consumo_alto_check);
        painel_consumo.add(consumo_baixo_check);
        painel_consumo.add(consumo_medio_check);
        painel_consumo.add(consumo_alto_check);
        painel.add(painel_consumo);

        JPanel painel_orcamento = new JPanel();
        painel_orcamento.setBorder(BorderFactory.createTitledBorder("Orçamento"));
        JCheckBox orcamento_baixo_check = new JCheckBox("Baixo");
        JCheckBox orcamento_medio_check = new JCheckBox("Médio");
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

        JPanel painel_botoes = new JPanel(new FlowLayout());
        JButton buttonRecomendar = new JButton("Recomendar");
        JButton buttonLimpar = new JButton("Limpar");
        JButton buttonFechar = new JButton("Fechar");

        painel_botoes.add(buttonRecomendar);
        painel_botoes.add(buttonLimpar);
        painel_botoes.add(buttonFechar);

        painel.add(painel_botoes);

        buttonRecomendar.addActionListener(_ -> {
            StringBuilder chave = new StringBuilder();

            if (game_check.isSelected()) chave.append("game-");
            else if (internet_check.isSelected()) chave.append("internet-");
            else if (edicao_video_check.isSelected()) chave.append("edição de vídeo-");

            if (orcamento_baixo_check.isSelected()) chave.append("baixo-");
            else if (orcamento_medio_check.isSelected()) chave.append("médio-");
            else if (orcamento_alto_check.isSelected()) chave.append("alto-");

            if (desempenho_baixo_check.isSelected()) chave.append("baixo-");
            else if (desempenho_medio_check.isSelected()) chave.append("médio-");
            else if (desempenho_alto_check.isSelected()) chave.append("alto-");

            if (consumo_baixo_check.isSelected()) chave.append("baixo");
            else if (consumo_medio_check.isSelected()) chave.append("médio");
            else if (consumo_alto_check.isSelected()) chave.append("alto");

            CPU recomendacao = cpuMap.getOrDefault(chave.toString(), null);

            if (recomendacao != null) {
                textAreaResultado.setText(recomendacao.toString());
            } else {
                textAreaResultado.setText("Nenhuma recomendação encontrada para os critérios selecionados.");
            }
        });

        buttonLimpar.addActionListener(_ -> textAreaResultado.setText(""));

        buttonFechar.addActionListener(_ -> System.exit(0));

        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }

    private Map<String, CPU> BaseDeDados() {
        Map<String, CPU> cpuMap = new HashMap<>();
        cpuMap.put("game-baixo-baixo-baixo", new CPU("AMD Athlon 3000G", "CISC", "64KB L1, 512KB L2", "3.5GHz", "Econômica e suficiente para jogos leves."));
        cpuMap.put("game-baixo-médio-baixo", new CPU("AMD Ryzen 3 3200G", "CISC", "64KB L1, 512KB L2", "3.6GHz", "Boa performance para jogos leves com custo reduzido."));
        cpuMap.put("game-médio-médio-baixo", new CPU("AMD Ryzen 5 4600G", "CISC", "128KB L1, 2MB L2", "3.7GHz", "Versátil para jogos intermediários e multitarefas."));
        cpuMap.put("game-alto-alto-alto", new CPU("Intel Core i7-12700K", "CISC", "256KB L1, 12MB L2", "3.6GHz", "Altíssimo desempenho para jogos exigentes."));
        cpuMap.put("edição de vídeo-baixo-médio-médio", new CPU("Intel Core i3-10100", "CISC", "64KB L1, 1MB L2", "3.6GHz", "Adequado para tarefas básicas de edição."));
        cpuMap.put("edição de vídeo-médio-médio-médio", new CPU("Intel Core i5-11400", "CISC", "128KB L1, 2MB L2", "2.6GHz", "Bom para edição intermediária."));
        cpuMap.put("edição de vídeo-alto-alto-alto", new CPU("AMD Ryzen 9 5900X", "CISC", "256KB L1, 6MB L2", "3.7GHz", "Ideal para edição profissional."));
        cpuMap.put("internet-baixo-baixo-baixo", new CPU("Intel Pentium Gold G6400", "CISC", "64KB L1, 512KB L2", "4.0GHz", "Ideal para navegação e tarefas simples."));
        return cpuMap;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(recomendacaoCPU::new);
    }

    class CPU {
        private String modelo;
        private String arquitetura;
        private String cache;
        private String frequencia;
        private String justificativa;

        public CPU(String modelo, String arquitetura, String cache, String frequencia, String justificativa) {
            this.modelo = modelo;
            this.arquitetura = arquitetura;
            this.cache = cache;
            this.frequencia = frequencia;
            this.justificativa = justificativa;
        }

        @Override
        public String toString() {
            return "Modelo: " + modelo + "\n" +
                    "Arquitetura: " + arquitetura + "\n" +
                    "Cache: " + cache + "\n" +
                    "Frequência: " + frequencia + "\n" +
                    "Justificativa: " + justificativa;
        }
    }
}
