package dados;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import negocio.Leilao;
import negocio.Usuario;

public class CadastroDB { //implements facade

    private static void createDB() throws CadastroException {
        try {
        	Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Gustavo_Zudrewer\\workspace\\TesteSQLite\\databases\\tf.db"); //precisa ser um caminho já existente, alterar.
			Statement statement = c.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS"
					+ " usuarios(nome TEXT, cpf TEXT, email TEXT)"); // cnpj
			statement.execute("CREATE TABLE IF NOT EXISTS"
					+ " leilao(natureza INTEGER, cpf TEXT, tipoLances INTEGER, dataInicio DATE, dataFim DATE, horaInicio TEXT, horaFim TEXT)");
			c.close();
        } catch (SQLException ex) {
            throw new CadastroException(ex.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Gustavo_Zudrewer\\workspace\\TesteSQLite\\databases\\tf.db");
    }

    public boolean adicionarUsuario(Usuario u) throws CadastroException {
        try(Connection c = getConnection();){
        	createDB();
        		if(!checarSeExisteUsuario(u)){
        		PreparedStatement stmt = c.prepareStatement(
                        "INSERT INTO usuarios (nome, cpf, email) VALUES (?,?,?)"
                        );
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getCpf());
                stmt.setString(3, u.getEmail());
                stmt.executeUpdate();
                stmt.close();
                return true;
        		}
        		else{
        			return false;
        		}
        } catch (SQLException ex) {
            throw new CadastroException("Falha ao adicionar.", ex);
        }
    }

    public boolean cadastrarLeilao(Leilao l) throws CadastroException {
        try(Connection c = getConnection();){
        	createDB();
        		PreparedStatement stmt = c.prepareStatement(
                        "INSERT INTO leiloes (natureza, cpf, tipoLances, dataInicio, dataFim, horaInicio, horaFim) VALUES (?,?,?,?,?,?,?)"
                        );
                stmt.setInt(1, l.getNatureza());
                stmt.setString(2, l.getCpf().toString());
                stmt.setInt(3, l.getTipoLances());
                stmt.setDate(4, l.getDataInicio());
                stmt.setDate(5, l.getDataFim());
                stmt.setString(6, l.getHoraInicio().toString());
                stmt.setString(7, l.getHoraFim().toString());
                stmt.executeUpdate();
                stmt.close();
                return true;
        } catch (SQLException ex) {
            throw new CadastroException("Falha ao adicionar.", ex);
        }
    }

    public List<Usuario> getTodosUsuarios() throws CadastroException {
        try(Connection c = getConnection();) {
            Statement stmt = c.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM USUARIOS");
            List<Usuario> lista = new ArrayList<Usuario>();
            while(resultado.next()) {
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                String email = resultado.getString("email");
                Usuario u = new Usuario(nome, cpf, email);
                lista.add(u);
            }
            return lista;
        } catch (SQLException ex) {
            throw new CadastroException("Falha ao buscar.", ex);
        }    }

    public void queryqualquer(String query) throws CadastroException {
    	try(Connection c = getConnection();) {
			Statement stmt = c.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}


    public boolean checarSeExisteUsuario(Usuario u) throws CadastroException {
		try(Connection c = getConnection();){ // fecha o recurso automaticamente
			String queryCheck = "SELECT * from usuarios WHERE cpf = "+u.getCpf().toString();
			Statement stmt = c.createStatement();
			stmt.executeQuery(queryCheck);
        	final ResultSet resultSet = stmt.executeQuery(queryCheck);

        	if(resultSet.next()==false){
        		return false;
        	}
        	else{
        		return true;
        	}
		} catch(SQLException e){
			throw new CadastroException("Falha.", e);
		}
    }
}
