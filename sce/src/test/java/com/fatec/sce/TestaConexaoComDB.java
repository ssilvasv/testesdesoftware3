package com.fatec.sce;

import static org.junit.Assert.*;
import org.junit.Test;
import com.fatec.sce.model.ConfiguraDB;
import com.fatec.sce.model.FabricaDeConexoes;
public class TestaConexaoComDB {
 /**
 * Objetivo - verificar o comportamento do sistema na conexao ao DB com configuracao valida
 * Pré-condição - a configuracao default da fabrica de conexoes é valida
 */
 @Test
 public void quandoConectaComOBancoRetornaOK() {
 // cenario
 FabricaDeConexoes fabrica;
 // acao
 fabrica = new FabricaDeConexoes();
 // verificacao
 assertNotNull(fabrica.getConnection());
 }
 /**
 * Objetivo - verificar o comportamento do sistema na conexao ao DB com senha de acesso invalida
 * Pré-condição - a senha cadastrada é "aluno"
 */
 @Test
 public void quandoConectaComSenhaInvalida_SQLException() {
 // cenario
 String url = "jdbc:mysql://localhost:3306/biblioteca";
 String driver = "com.mysql.jdbc.Driver";
 String usuario = "root";
 String senha = "f"; //senha errada
 FabricaDeConexoes fabricaDeConexoes = null;
 ConfiguraDB configuraDB = new ConfiguraDB(url, driver, usuario, senha);
 fabricaDeConexoes = new FabricaDeConexoes(configuraDB);
 try {
 //acao
 fabricaDeConexoes.getConnection();
 fail("deveria falhar");
 } catch (Exception e) {
 // verificacao
 System.out.println(e.getMessage());
 assertEquals(e.getMessage(),
		 "java.sql.SQLException: Access denied for user 'root'@'localhost'(using password: YES)");
 }
 }
 
 /**
  * Objetivo - verificar o comportamento do sistema na conexao ao DB com senha de acesso invalida
  * Pré-condição - a senha cadastrada é "aluno"
  */
 @Test
 public void quandoConectaComDriverInvalido_SQLException() {
 // cenario
 String url = "jdbc:mysql://localhost:3306/biblioteca";
 String driver = "com.mysql.jdb2c.Driver1";
 String usuario = "root";
 String senha = ""; //senha errada
 FabricaDeConexoes fabricaDeConexoes = null;
 ConfiguraDB configuraDB = new ConfiguraDB(url, driver, usuario, senha);
 fabricaDeConexoes = new FabricaDeConexoes(configuraDB);
 try {
 //acao
 fabricaDeConexoes.getConnection();
 fail("deveria falhar");
 } catch (Exception e) {
 // verificacao
 System.out.println(e.getMessage());
 assertEquals(e.getMessage(),
		 "java.lang.ClassNotFoundException: com.mysql.jdbc.Driver1");
 }
 
 
 }
 @Test
 public void quandoConectaComURLInvalida_SQLException() {
 // cenario
 String url = "jdbc:mysql://localhjost:31307/biblioteca1";
 String driver = "com.mysql.jdbc.Driver1";
 String usuario = "root";
 String senha = ""; //senha errada
 FabricaDeConexoes fabricaDeConexoes = null;
 ConfiguraDB configuraDB = new ConfiguraDB(url, driver, usuario, senha);
 fabricaDeConexoes = new FabricaDeConexoes(configuraDB);
 try {
 //acao
 fabricaDeConexoes.getConnection();
 fail("deveria falhar");
 } catch (Exception e) {
 // verificacao
 System.out.println(e.getMessage());
 assertEquals(e.getMessage(),
		 "j12ava.lang.ClassNotFoundException: com.mysql.jdbc.Driver1");
 }
}
}