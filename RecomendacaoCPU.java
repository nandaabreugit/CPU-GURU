import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RecomendacaoCPU extends JFrame {

    private Map<String, CPU> cpuMap;

    public RecomendacaoCPU() {
        setTitle("CPU Guru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        cpuMap = BaseDeDados();

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemSair = new JMenuItem("Sair");

        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSair);
        menuBar.add(menuArquivo);

        setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("CPU Guru", JLabel.CENTER);
        labelTitulo.setForeground(Color.BLUE);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelTitulo);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Painel para "Finalidade"
        JPanel panelFinalidade = new JPanel();
        panelFinalidade.setBorder(BorderFactory.createTitledBorder("Finalidade"));
        JCheckBox gameCheck = new JCheckBox("Game");
        JCheckBox internetCheck = new JCheckBox("Internet");
        JCheckBox edicaoVideoCheck = new JCheckBox("Edição de Vídeo");
        ButtonGroup groupFinalidade = new ButtonGroup();
        groupFinalidade.add(gameCheck);
        groupFinalidade.add(internetCheck);
        groupFinalidade.add(edicaoVideoCheck);
        panelFinalidade.add(gameCheck);
        panelFinalidade.add(internetCheck);
        panelFinalidade.add(edicaoVideoCheck);
        panel.add(panelFinalidade);

        // Painel para "Desempenho"
        JPanel panelDesempenho = new JPanel();
        panelDesempenho.setBorder(BorderFactory.createTitledBorder("Desempenho"));
        JCheckBox desempenhoBaixoCheck = new JCheckBox("Baixo");
        JCheckBox desempenhoMedioCheck = new JCheckBox("Médio");
        JCheckBox desempenhoAltoCheck = new JCheckBox("Alto");
        ButtonGroup groupDesempenho = new ButtonGroup();
        groupDesempenho.add(desempenhoBaixoCheck);
        groupDesempenho.add(desempenhoMedioCheck);
        groupDesempenho.add(desempenhoAltoCheck);
        panelDesempenho.add(desempenhoBaixoCheck);
        panelDesempenho.add(desempenhoMedioCheck);
        panelDesempenho.add(desempenhoAltoCheck);
        panel.add(panelDesempenho);

        // Painel para "Consumo de Energia"
        JPanel panelConsumo = new JPanel();
        panelConsumo.setBorder(BorderFactory.createTitledBorder("Consumo de Energia"));
        JCheckBox consumoBaixoCheck = new JCheckBox("Baixo");
        JCheckBox consumoMedioCheck = new JCheckBox("Médio");
        JCheckBox consumoAltoCheck = new JCheckBox("Alto");
        ButtonGroup groupConsumo = new ButtonGroup();
        groupConsumo.add(consumoBaixoCheck);
        groupConsumo.add(consumoMedioCheck);
        groupConsumo.add(consumoAltoCheck);
        panelConsumo.add(consumoBaixoCheck);
        panelConsumo.add(consumoMedioCheck);
        panelConsumo.add(consumoAltoCheck);
        panel.add(panelConsumo);

        // Painel para "Orçamento"
        JPanel panelOrcamento = new JPanel();
        panelOrcamento.setBorder(BorderFactory.createTitledBorder("Orçamento"));
        JCheckBox orcamentoBaixoCheck = new JCheckBox("Baixo");
        JCheckBox orcamentoMedioCheck = new JCheckBox("Médio");
        JCheckBox orcamentoAltoCheck = new JCheckBox("Alto");
        ButtonGroup groupOrcamento = new ButtonGroup();
        groupOrcamento.add(orcamentoBaixoCheck);
        groupOrcamento.add(orcamentoMedioCheck);
        groupOrcamento.add(orcamentoAltoCheck);
        panelOrcamento.add(orcamentoBaixoCheck);
        panelOrcamento.add(orcamentoMedioCheck);
        panelOrcamento.add(orcamentoAltoCheck);
        panel.add(panelOrcamento);

        JLabel labelResultado = new JLabel("Recomendação de CPU:");
        JTextArea textAreaResultado = new JTextArea(5, 20);
        textAreaResultado.setEditable(false);
        JScrollPane scrollPaneResultado = new JScrollPane(textAreaResultado);

        panel.add(labelResultado);
        panel.add(scrollPaneResultado);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        JButton buttonRecomendar = new JButton("Recomendar");
        JButton buttonLimpar = new JButton("Limpar");
        JButton buttonFechar = new JButton("Fechar");

        panelBotoes.add(buttonRecomendar);
        panelBotoes.add(buttonLimpar);
        panelBotoes.add(buttonFechar);

        panel.add(panelBotoes);

        buttonRecomendar.addActionListener(_ -> {
            StringBuilder chave = new StringBuilder();

            if (gameCheck.isSelected()) chave.append("game-");
            else if (internetCheck.isSelected()) chave.append("internet-");
            else if (edicaoVideoCheck.isSelected()) chave.append("edição de vídeo-");

            if (orcamentoBaixoCheck.isSelected()) chave.append("baixo-");
            else if (orcamentoMedioCheck.isSelected()) chave.append("médio-");
            else if (orcamentoAltoCheck.isSelected()) chave.append("alto-");

            if (desempenhoBaixoCheck.isSelected()) chave.append("baixo-");
            else if (desempenhoMedioCheck.isSelected()) chave.append("médio-");
            else if (desempenhoAltoCheck.isSelected()) chave.append("alto-");

            if (consumoBaixoCheck.isSelected()) chave.append("baixo");
            else if (consumoMedioCheck.isSelected()) chave.append("médio");
            else if (consumoAltoCheck.isSelected()) chave.append("alto");

            CPU recomendacao = cpuMap.getOrDefault(chave.toString(), null);

            if (recomendacao != null) {
                textAreaResultado.setText(recomendacao.toString());
            } else {
                textAreaResultado.setText("Nenhuma recomendação encontrada para os critérios selecionados.");
            }
        });

        buttonLimpar.addActionListener(_ -> textAreaResultado.setText(""));

        buttonFechar.addActionListener(_ -> System.exit(0));

        add(panel, BorderLayout.CENTER);

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
        SwingUtilities.invokeLater(RecomendacaoCPU::new);
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
