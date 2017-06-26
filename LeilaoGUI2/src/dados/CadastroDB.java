package dados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import negocio.Usuario;

public class CadastroDB { //implements facade

    private static void createDB() throws CadastroException {
        try {
        	Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Gustavo_Zudrewer\\workspace\\TesteSQLite\\databases\\tf.db"); //precisa ser um caminho já existente, alterar.
			Statement statement = c.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS"
					+ " usuarios(nome TEXT, cpf TEXT, email TEXT)"); // cnpj
			c.close();
        } catch (SQLException ex) {
            throw new CadastroException(ex.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Gustavo_Zudrewer\\workspace\\TesteSQLite\\databases\\tf.db");
    }

    public boolean adicionar(Usuario u) throws CadastroException {
        try(Connection c = getConnection();){
        	createDB();
        		if(!checarSeExiste(u)){
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

    public List<Usuario> getTodos() throws CadastroException {
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

    public boolean checarSeExiste(Usuario u) throws CadastroException {
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
