package br.edu.ifsul.bcc.lpoo.om.model.dao;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Cliente;
import br.edu.ifsul.bcc.lpoo.om.model.Curso;
import br.edu.ifsul.bcc.lpoo.om.model.Equipe;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.MaoObra;
import br.edu.ifsul.bcc.lpoo.om.model.Orcamento;
import br.edu.ifsul.bcc.lpoo.om.model.Pagamento;
import br.edu.ifsul.bcc.lpoo.om.model.Peca;
import br.edu.ifsul.bcc.lpoo.om.model.Servico;
import br.edu.ifsul.bcc.lpoo.om.model.Veiculo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author telmo
 */
public class PersistenciaJDBC implements InterfacePersistencia {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_om_lpoo_20232";
    private Connection con = null;

    public PersistenciaJDBC() throws ClassNotFoundException, SQLException {

        Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução
        System.out.println("Tentando estabelecer conexao JDBC com : " + URL + " ...");

        this.con = DriverManager.getConnection(URL, USER, SENHA);

    }

    @Override
    public Boolean conexaoAberta() {

        try {
            if (con != null) {
                return !con.isClosed();//verifica se a conexao está aberta
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException e) {
            e.printStackTrace();//gera uma pilha de erro na saida.
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if (c == Cargo.class) {
            PreparedStatement ps = this.con.prepareStatement(""
                    + "select id, descricao from tb_cargo where id = ?");

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cargo crg = new Cargo();
                crg.setId(rs.getInt("id"));
                crg.setDescricao(rs.getString("descricao"));
                return crg;
            }
            ps.close();
            rs.close();

        } else if (c == Curso.class) {

        } else if (c == Funcionario.class) {

            //select em tb_funcionario
            //select na tabela associativa.
        } else if (c == Veiculo.class) {

            PreparedStatement ps = this.con.prepareStatement(
                    "select placa, ano, data_ultimo_servico, modelo from tb_veiculo where placa = ?");

            ps.setString(1, id.toString());

            ResultSet rs = ps.executeQuery();
            Veiculo v = null;
            if (rs.next()) {
                v = new Veiculo();
                v.setPlaca(rs.getString("placa"));
                v.setAno(rs.getInt("ano"));

                if (rs.getDate("data_ultimo_servico") != null) {
                    Calendar cld = Calendar.getInstance();
                    cld.setTimeInMillis(rs.getDate("data_ultimo_servico").getTime());
                    v.setData_ultimo_servico(cld);
                }
                v.setModelo(rs.getString("modelo"));
            }
            ps.close();
            rs.close();
            return v;
        }

        return null;
    }

    @Override
    public void persist(Object o) throws Exception {
        if (o instanceof Cargo) {
            Cargo crg = (Cargo) o;
            if (crg.getId() == null) {
                //executar um insert
                PreparedStatement ps = this.con.prepareStatement("insert into "
                        + "tb_cargo (id, descricao) values (nextval('seq_cargo_id') , ? ) returning id");
                //definir os valores para os parametros
                ps.setString(1, crg.getDescricao());
                ResultSet rs = ps.executeQuery();
                //System.out.println(rs.getInt("id"));
                if (rs.next()) {
                    crg.setId(rs.getInt("id"));
                    rs.close();
                }
                ps.close();
            } else {
                //executar um update
                PreparedStatement ps = this.con.prepareStatement("update tb_cargo set descricao = ? "
                        + "where id = ?");

                //definir os valores para os parametros.
                ps.setString(1, crg.getDescricao());
                ps.setInt(2, crg.getId());
                ps.execute();
                ps.close();
            }

        } else if (o instanceof Cliente) {
            Cliente c = (Cliente) o;

            if (c.getTipo() == null) {
                // executa um insert

                // insert em tb_pessoa
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (tipo,cpf,data_nascimento,nome,senha) values "
                        + "('C',?,?,?,?)");

                ps.setString(1, c.getCpf());
                ps.setDate(2, null);
                ps.setString(3, c.getNome());
                ps.setString(4, c.getSenha());
                ps.execute();
                ps.close();

                // insert em tb_cliente
                PreparedStatement ps2 = this.con.prepareStatement("insert into tb_cliente (observacoes, cpf) values "
                        + "(?, ?) returning observacoes");

                ps2.setString(1, c.getObservacoes());
                ps2.setString(2, c.getCpf());

                ResultSet rs = ps2.executeQuery();

                if (rs.next()) {
                    c.setObservacoes(rs.getString("observacoes"));

                    if (!c.getVeiculo().isEmpty()) {

                        for (Veiculo v : c.getVeiculo()) {

                            PreparedStatement ps3 = this.con.prepareStatement("insert into tb_cliente_veiculo "
                                    + "(cliente_cpf, veiculo_id) "
                                    + "values "
                                    + "(?, ?)");
                            ps3.setString(1, c.getCpf());
                            ps3.setString(2, v.getPlaca());

                            ps3.execute();
                            ps3.close();
                        }
                    }
                    rs.close();
                }
                ps2.close();
            } else {
                PreparedStatement ps
                        = this.con.prepareStatement("update tb_pessoa set cep = ?, "
                                + "complemento = ?, "
                                + "data_nascimento = ?, "
                                + "nome = ?, "
                                + "numero = ?, "
                                + "senha = ?"
                                + " where cpf = ? ");
                //setar os demais campos e parametros.

                ps.setString(1, c.getCep());
                ps.setString(2, c.getComplemento());
                ps.setDate(3, new java.sql.Date(c.getData_nascimento().getTimeInMillis()));
                ps.setString(4, c.getNome());
                ps.setString(5, c.getNumero());
                ps.setString(6, c.getSenha());
                ps.setString(7, c.getCpf());

                ps.execute();
                ps.close();

                ps = this.con.prepareStatement("update tb_cliente set observacoes = ? "
                        + "where cpf = ?");
                ps.setString(1, c.getObservacoes());
                ps.setString(2, c.getCpf());

                ps.execute();
                ps.close();

                ps = this.con.prepareStatement("delete from tb_cliente_veiculo where cliente_cpf = ?");

                ps.setString(1, c.getCpf());
                ps.execute();
                ps.close();

                if (!c.getVeiculo().isEmpty()) {

                    for (Veiculo v : c.getVeiculo()) {

                        ps = this.con.prepareStatement("insert into tb_cliente_veiculo "
                                + "(cliente_cpf, veiculo_id) "
                                + "values "
                                + "(?, ?)");
                        ps.setString(1, c.getCpf());
                        ps.setString(2, v.getPlaca());

                        ps.execute();
                        ps.close();
                    }

                }

            }

        } else if (o instanceof Curso) {
            Curso c = (Curso) o;

            if (c.getId() == null) {
                // executar um insert
                PreparedStatement ps = this.con.prepareStatement("insert into tb_curso (id, descricao) values "
                        + "(nextval('seq_curso_id'), ?) returning id");

                ps.setString(1, c.getDescricao());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    c.setId(rs.getInt("id"));
                }
                ps.close();

            } else {
                // executar um update
                PreparedStatement ps = this.con.prepareStatement("update tb_curso set descricao = ? "
                        + "where id = ?");

                ps.setString(1, c.getDescricao());
                ps.setInt(2, c.getId());
                ps.execute();
                ps.close();

            }

        } else if (o instanceof Equipe) {
            Equipe e = (Equipe) o;

            if (e.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_equipe (id, especialidades, nome) "
                        + "values (nextval('seq_equipe_id'), ?, ?) returning id");

                ps.setString(1, e.getEspecialidades());
                ps.setString(2, e.getNome());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    e.setId(rs.getInt("id"));
                    System.out.println("Passou");
                    if (!e.getFuncionarios().isEmpty() || e.getFuncionarios() != null) {

                        for (Funcionario f : e.getFuncionarios()) {

                            PreparedStatement ps2 = this.con.prepareStatement("insert into tb_equipe_funcionario "
                                    + "(equipe_id, funcionario_cpf) "
                                    + "values "
                                    + "(?, ?)");
                            ps2.setInt(1, e.getId());
                            ps2.setString(2, f.getCpf());

                            ps2.execute();
                            ps2.close();
                        }
                    } else {
                        e.setFuncionarios(null);
                    }
                    ps.close();
                }

            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_equipe set "
                        + "especialidades = ?, "
                        + "nome = ? "
                        + "where id = ?");
                ps.setString(1, e.getEspecialidades());
                ps.setString(2, e.getNome());
                ps.setInt(3, e.getId());
                ps.execute();
                ps.close();

                // deleta todos os registros da tabela de uma determinada equipe
                ps = this.con.prepareStatement("delete from tb_equipe_funcionario "
                        + "where equipe_id = ?");
                ps.setInt(1, e.getId());
                ps.execute();
                ps.close();

                if (!e.getFuncionarios().isEmpty()) {

                    for (Funcionario f : e.getFuncionarios()) {
                        // reinsere os novos integrantes
                        PreparedStatement ps2 = this.con.prepareStatement("insert into tb_equipe_funcionario "
                                + "(equipe_id, funcionario_cpf) "
                                + "values "
                                + "(?, ?)");
                        ps2.setInt(1, e.getId());
                        ps2.setString(2, f.getCpf());
                        ps2.execute();
                        ps2.close();
                    }

                }
            }

        } else if (o instanceof Funcionario) {

            Funcionario func = (Funcionario) o;

            //verificar a acao: insert ou update.
            if (func.getData_admmissao() == null) {

                //insert tb_pessoa
                PreparedStatement ps
                        = this.con.prepareStatement("insert into tb_pessoa (tipo,cpf,data_nascimento,nome,senha) values "
                                + "('F',?,?,?,?)");

                ps.setString(1, func.getCpf());
                ps.setDate(2, new java.sql.Date(func.getData_nascimento().getTimeInMillis()));
                ps.setString(3, func.getNome());
                ps.setString(4, func.getSenha());

                //demais campos...
                ps.execute();

                ps.close();

                //insert em tb_funcionario
                PreparedStatement ps2
                        = this.con.prepareStatement("insert into tb_funcionario values "
                                + "(now(), null, ?, ?, ?) returning data_admmissao");
                System.out.println("passou2");
                ps2.setString(1, func.getNumero_ctps());
                ps2.setString(2, func.getCpf());
                ps2.setInt(3, func.getCargo().getId());
                //setar os parametros...

                ResultSet rs2 = ps2.executeQuery();

                if (rs2.next()) {

                    Calendar dt_adm = Calendar.getInstance();
                    dt_adm.setTimeInMillis(rs2.getDate("data_admmissao").getTime());
                    func.setData_admmissao(dt_adm);

                    //se necessário o insert em tb_funcionario_curso
                    if (func.getCursos() != null) {
                        if (!func.getCursos().isEmpty()) {

                            for (Curso crs : func.getCursos()) {

                                PreparedStatement ps3 = this.con.prepareStatement("insert into tb_funcionario_curso "
                                        + "(funcionario_cpf, curso_id) "
                                        + "values "
                                        + "(?, ?)");
                                ps3.setString(1, func.getCpf());
                                ps3.setInt(2, crs.getId());

                                ps3.execute();
                                ps3.close();
                            }

                        }
                    }
                }
                ps2.close();

            } else {

                //update tb_pessoa.
                PreparedStatement ps
                        = this.con.prepareStatement("update tb_pessoa set cep = ?, "
                                + "complemento = ?, "
                                + "data_nascimento = ?, "
                                + "nome = ?, "
                                + "numero = ?, "
                                + "senha = ?"
                                + " where cpf = ? ");
                //setar os demais campos e parametros.

                ps.setString(1, func.getCep());
                ps.setString(2, func.getComplemento());
                ps.setDate(3, new java.sql.Date(func.getData_nascimento().getTimeInMillis()));
                ps.setString(4, func.getNome());
                ps.setString(5, func.getNumero());
                ps.setString(6, func.getSenha());
                ps.setString(7, func.getCpf());

                ps.execute();
                ps.close();

                //update tb_funcionario.
                PreparedStatement ps2
                        = this.con.prepareStatement("update tb_funcionario set data_demissao = ?, "
                                + "numero_ctps = ?, "
                                + "cargo_id = ?"
                                + "where cpf = ? ");
                //setar os demais campos e parametros.

                ps2.setDate(1, new java.sql.Date(func.getData_demissao().getTimeInMillis()));
                ps2.setString(2, func.getNumero_ctps());
                ps2.setInt(3, func.getCargo().getId());
                ps2.setString(4, func.getCpf());

                ps2.execute();
                ps2.close();

                //atualizar os respectivos registros em tb_funcionario_curso para o func
                //passo 1 - remove todos os cursos do funcionario na tabela associativa 
                PreparedStatement ps3
                        = this.con.prepareStatement("delete from tb_funcionario_curso where funcionario_cpf = ?");

                ps3.setString(1, func.getCpf());
                ps3.execute();
                ps3.close();

                //passo 2: insere novamente, caso necessario. 
                if (func.getCursos() != null) {
                    if (!func.getCursos().isEmpty()) {

                        for (Curso crs : func.getCursos()) {

                            PreparedStatement ps4 = this.con.prepareStatement("insert into tb_funcionario_curso "
                                    + "(funcionario_cpf, curso_id) "
                                    + "values "
                                    + "(?, ?)");
                            ps4.setString(1, func.getCpf());
                            ps4.setInt(2, crs.getId());

                            ps4.execute();
                            ps4.close();
                        }

                    }
                }
            }

        } else if (o instanceof MaoObra) {
            MaoObra mao = (MaoObra) o;

            if (mao.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_maoobra (id, descricao, tempo_estimado_execucao, valor) "
                        + "values (nextval('seq_maoobra_id'), ?, ?, ?) returning id");
                ps.setString(1, mao.getDescricao());
                try {
                    Time tempoEstimadoExecucao = new Time(mao.getTempo_estimado_execucao().getTime());
                    ps.setTime(2, tempoEstimadoExecucao);
                } catch (Exception e) {
                    ps.setTime(2, null);
                }
                ps.setFloat(3, mao.getValor());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    mao.setId(rs.getString("id"));
                }
                ps.close();

            } else {
                // update em tb_maoobra
                PreparedStatement ps = this.con.prepareStatement("update tb_maoobra set descricao = ?, "
                        + "tempo_estimado_execucao = ?, "
                        + "valor = ? "
                        + "where id = ?");
                ps.setString(1, mao.getDescricao());
                try {
                    ps.setDate(2, new java.sql.Date(mao.getTempo_estimado_execucao().getTime()));
                } catch (Exception e) {
                    ps.setDate(2, null);
                }
                ps.setFloat(3, mao.getValor());
                ps.setString(4, mao.getId());
                ps.execute();
                ps.close();
            }
        } else if (o instanceof Orcamento) {
            Orcamento orc = (Orcamento) o;
            // insert na tb_orcamento
            if (orc.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_orcamento (id, data, observacoes, cliente_cpf, funcionario_cpf, veiculo_id) "
                        + "values (nextval('seq_orcamento_id'), now(), ?, ?, ?, ?) returning id");

                ps.setString(1, orc.getObservacoes());
                try {
                    ps.setString(2, orc.getCliente().getCpf());
                } catch (Exception e) {
                    ps.setString(2, null);
                }
                try {
                    ps.setString(3, orc.getFuncionario().getCpf());
                } catch (Exception e) {
                    ps.setString(3, null);
                }
                try {
                    ps.setString(4, orc.getVeiculo().getPlaca());
                } catch (Exception e) {
                    ps.setString(4, null);
                }

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    orc.setId(rs.getInt("id"));

                    // verifica se necessita inserir em tb_orcamento_maoobra
                    if (!orc.getMaoobras().isEmpty()) {

                        for (MaoObra mao : orc.getMaoobras()) {

                            PreparedStatement ps2 = this.con.prepareStatement("insert into tb_orcamento_maoobra "
                                    + "(orcamento_id, maoobra_id) "
                                    + "values "
                                    + "(?, ?)");
                            ps2.setInt(1, orc.getId());
                            ps2.setString(2, mao.getId());
                            ps2.execute();
                            ps2.close();
                        }
                    }
                    ps.close();
                    if (!orc.getPecas().isEmpty()) {

                        for (Peca p : orc.getPecas()) {
                            PreparedStatement ps2 = this.con.prepareStatement("insert into tb_orcamento_peca "
                                    + "(orcamento_id, peca_id) values "
                                    + "(?, ?)");
                            ps2.setInt(1, orc.getId());
                            ps2.setInt(2, p.getId());
                            ps2.execute();
                            ps2.close();
                        }
                    }

                }

            } else {
                // realizar update em tb_orcamento
                PreparedStatement ps = this.con.prepareStatement("update tb_orcamento set observacoes = ?, "
                        + "cliente_cpf = ?, "
                        + "funcionario_cpf = ?, "
                        + "veiculo_id = ? "
                        + "where id = ?)");
                ps.setString(1, orc.getObservacoes());
                ps.setString(2, orc.getCliente().getCpf());
                ps.setString(3, orc.getFuncionario().getCpf());
                ps.setString(4, orc.getVeiculo().getPlaca());
                ps.setInt(5, orc.getId());
                ps.execute();
                ps.close();

                //passo 1 - remove todas as maos de obra do orcamento na tabela associativa 
                PreparedStatement ps2
                        = this.con.prepareStatement("delete from tb_orcamento_maoobra where orcamento_id = ?");

                ps2.setInt(1, orc.getId());
                ps2.execute();
                ps2.close();

                //passo 2: insere novamente, caso necessario. 
                if (!orc.getMaoobras().isEmpty()) {

                    for (MaoObra mao : orc.getMaoobras()) {

                        PreparedStatement ps3 = this.con.prepareStatement("insert into tb_orcamento_maoobra "
                                + "(orcamento_id, maoobra_id) "
                                + "values "
                                + "(?, ?)");
                        ps3.setInt(1, orc.getId());
                        ps3.setString(2, mao.getId());

                        ps3.execute();
                        ps3.close();
                    }

                }

                //passo 1 - remove todas as peças do orcamento na tabela associativa 
                PreparedStatement ps4
                        = this.con.prepareStatement("delete from tb_orcamento_peca where orcamento_id = ?");

                ps4.setInt(1, orc.getId());
                ps4.execute();
                ps4.close();

                //passo 2: insere novamente, caso necessario. 
                if (!orc.getPecas().isEmpty()) {

                    for (Peca p : orc.getPecas()) {

                        PreparedStatement ps5 = this.con.prepareStatement("insert into tb_orcamento_peca "
                                + "(orcamento_id, peca_id) "
                                + "values "
                                + "(?, ?)");
                        ps5.setInt(1, orc.getId());
                        ps5.setInt(2, p.getId());

                        ps5.execute();
                        ps5.close();
                    }
                }

            }

        } else if (o instanceof Pagamento) {
            Pagamento p = (Pagamento) o;

            if (p.getId() == null) {
                // insert em tb_pagamento
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pagamento (id, data_pagamento, data_vencimento, numero_parcela, valor, servico_id) "
                        + "values (nextval('seq_pagamento_id'), ?, ?, ?, ?, ?) returning id");
                try {
                    ps.setDate(1, new java.sql.Date(p.getData_pagamento().getTimeInMillis()));
                } catch (Exception e) {
                    ps.setDate(1, null);
                }
                try {
                    ps.setDate(2, new java.sql.Date(p.getData_vencimento().getTimeInMillis()));
                } catch (Exception e) {
                    Calendar dt = Calendar.getInstance();
                    ps.setDate(2, new java.sql.Date(dt.getTimeInMillis()));
                }
                ps.setInt(3, p.getNumero_parcela());
                ps.setFloat(4, p.getValor());
                ps.setInt(5, p.getServico().getId());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    p.setId(rs.getInt("id"));
                    p.setData_pagamento(Calendar.getInstance());   // no dia da execução do persist
                }
                ps.close();
            } else {
                // update em tb_pagamento
                PreparedStatement ps = this.con.prepareStatement("update tb_pagamento set data_pagamento = ? "
                        + "where id = ?");
                try{
                    ps.setDate(1, new java.sql.Date(p.getData_pagamento().getTimeInMillis()));
                }catch(Exception e){
                    Calendar dt = Calendar.getInstance();
                    ps.setDate(1, new java.sql.Date(dt.getTimeInMillis()));
                }
                ps.setInt(2, p.getId());
                ps.execute();
                ps.close();
            }

        } else if (o instanceof Peca) {
            Peca p = (Peca) o;

            if (p.getId() == null) {
                // insert em tb_peca
                PreparedStatement ps = this.con.prepareStatement("insert into tb_peca (id, fornecedor, nome, valor) "
                        + "values (nextval('seq_peca_id'), ?, ?, ?) returning id");
                ps.setString(1, p.getFornecedor());
                ps.setString(2, p.getNome());
                ps.setFloat(3, p.getValor());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    p.setId(rs.getInt("id"));
                }
                ps.close();
            } else {
                // update em tb_peca
                PreparedStatement ps = this.con.prepareStatement("update tb_peca set fornecedor = ?, "
                        + "nome = ?, "
                        + "valor = ? "
                        + "where id = ?");
                ps.setString(1, p.getFornecedor());
                ps.setString(2, p.getNome());
                ps.setFloat(3, p.getValor());
                ps.setInt(4, p.getId());
                ps.execute();
                ps.close();
            }

        } else if (o instanceof Servico) {
            Servico s = (Servico) o;

            if (s.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_servico (id, data_fim, data_inicio, status, valor, equipe_id, orcamento_id) "
                        + "values (nextval('seq_servicos_id'), ?, now(), ?, ?, ?, ?) returning id");
                try {
                    ps.setDate(1, new java.sql.Date(s.getData_fim().getTimeInMillis()));
                } catch (Exception e) {
                    ps.setDate(1, null);
                }

                ps.setString(2, s.getStatus().toString());
                ps.setFloat(3, s.getValor());
                ps.setInt(4, s.getEquipe().getId());
                ps.setInt(5, s.getOrcamento().getId());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    s.setId(rs.getInt("id"));
                }
                ps.close();

            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_servico set data_fim = ?, "
                        + "status = ?, "
                        + "valor = ?, "
                        + "equipe_id = ?, "
                        + "orcamento_id = ? where id = ?");
                ps.setDate(1, new java.sql.Date(s.getData_fim().getTimeInMillis()));
                ps.setString(2, s.getStatus().toString());
                ps.setFloat(3, s.getValor());
                ps.setInt(4, s.getEquipe().getId());
                ps.setInt(5, s.getOrcamento().getId());
                ps.setInt(6, s.getId());
                ps.execute();
                ps.close();
            }

        } else if (o instanceof Veiculo) {
            Veiculo v = (Veiculo) o;

            if (v.getData_ultimo_servico() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_veiculo (placa, ano, data_ultimo_servico, modelo) "
                        + "values (?, ?, now(), ?) returning data_ultimo_servico");
                ps.setString(1, v.getPlaca());
                ps.setInt(2, v.getAno());
                // ps.setDate(3, new java.sql.Date(v.getData_ultimo_servico().getTimeInMillis()));
                ps.setString(3, v.getModelo());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Calendar dt_us = Calendar.getInstance();
                    dt_us.setTimeInMillis(rs.getDate("data_ultimo_servico").getTime());
                    v.setData_ultimo_servico(dt_us);
                }
                ps.close();

            } else {
                // update em tb_veiculo
                PreparedStatement ps = this.con.prepareStatement("update tb_veiculo set data_ultimo_servico = ? where "
                        + "placa = ?");
                ps.setDate(1, new java.sql.Date(v.getData_ultimo_servico().getTimeInMillis()));
                ps.setString(2, v.getPlaca());
                ps.execute();
                ps.close();
            }
        }

    }

    @Override
    public void remover(Object o) throws Exception {

        if (o instanceof Cargo) {
            Cargo crg = (Cargo) o;

            PreparedStatement ps = this.con.prepareStatement("delete from "
                    + "tb_cargo where id = ?;");
            //definir os valores para os parametros
            ps.setInt(1, crg.getId());
            ps.execute();
            ps.close();

        } else if (o instanceof Funcionario) {

            //remove os respectivos registro na tabela associativa
            //remover em tb_funcionario
        } else {

        }

    }

    @Override
    public Collection<Cargo> listCargos() throws Exception {
        Collection<Cargo> colecaoRetorno = null;

        PreparedStatement ps = this.con.prepareStatement("select id, descricao "
                + "from tb_cargo "
                + "order by id asc");

        ResultSet rs = ps.executeQuery();//executa o sql e retorna

        colecaoRetorno = new ArrayList();//inicializa a collecao

        while (rs.next()) {//percorre o ResultSet

            Cargo crg = new Cargo();//inicializa o Cargo

            // recuperar as informações da célula e setar no crg
            crg.setId(rs.getInt("id"));
            crg.setDescricao(rs.getString("descricao"));

            colecaoRetorno.add(crg);//adiciona na colecao
        }

        ps.close();//fecha o cursor

        return colecaoRetorno; //retorna a colecao.
    }

    @Override
    public Collection<Funcionario> listFuncionarios() throws Exception {
        Collection<Funcionario> colecaoRetorno = null;

        PreparedStatement ps = this.con.prepareStatement("");
        ResultSet rs = ps.executeQuery();//executa o sql e retorna

        colecaoRetorno = new ArrayList();//inicializa a collecao

        while (rs.next()) {//percorre o ResultSet

            Funcionario func = new Funcionario();//inicializa o Cargo
            //seta as informações do rs
            PreparedStatement ps2 = this.con.prepareStatement("selecte ... from tb_funcionario_cur");
            ResultSet rs2 = ps.executeQuery();//executa o sql e retorna
            Collection<Curso> colecaoCursos = new ArrayList();
            while (rs2.next()) {
                Curso crs = new Curso();

                colecaoCursos.add(crs);
            }
            rs2.close();

            func.setCursos(colecaoCursos);

            colecaoRetorno.add(func);//adiciona na colecao
        }

        ps.close();//fecha o cursor

        return colecaoRetorno; //retorna a colecao.
    }

    @Override
    public Collection<Cliente> listClientes() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
