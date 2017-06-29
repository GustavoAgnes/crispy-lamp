package dados;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
					+ " leiloes(natureza TEXT, cpf TEXT, tipoLances TEXT, dataInicio DATE, dataFim DATE, horaInicio INTEGER, horaFim INTEGER, minutoInicio INTEGER, minutoFim INTEGER)");
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
                        "INSERT INTO leiloes (natureza, cpf, tipoLances, dataInicio, dataFim, horaInicio, horaFim, minutoInicio, minutoFim) VALUES (?,?,?,?,?,?,?,?,?)"
                        );
                stmt.setString(1, l.getNatureza());
                stmt.setString(2, l.getCpf().toString());
                stmt.setString(3, l.getTipoLances());
                stmt.setDate(4, l.getDataInicio());
                stmt.setDate(5, l.getDataFim());
                stmt.setInt(6, l.getHoraInicio());
                stmt.setInt(7, l.getHoraFim());
                stmt.setInt(8, l.getMinutoInicio());
                stmt.setInt(9, l.getMinutoFim());
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

    public List<Leilao> getTodosLeiloes() throws CadastroException {
        try(Connection c = getConnection();) {
            Statement stmt = c.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILOES");
            List<Leilao> lista = new ArrayList<Leilao>();
            while(resultado.next()) {
                String natureza = resultado.getString("natureza");
                String cpf = resultado.getString("cpf");
                String tipoLances = resultado.getString("tipoLances");
               // LocalDate dataInicio = resultado.getDate("dataInicio").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             //  LocalDate dataFim = resultado.getDate("dataFim").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               Date dataInicio = resultado.getDate("dataInicio");
               Date dataFim = resultado.getDate("dataFim");
                //String dataInicio = resultado.getString("dataInicio");
                //String dataFim = resultado.getString("dataFim");
                int horaInicio = resultado.getInt("horaInicio");
                int horaFim = resultado.getInt("horaFim");
                int minutosInicio = resultado.getInt("minutoInicio");
                int minutosFim = resultado.getInt("minutoFim");
                Leilao l = new Leilao(natureza,cpf,tipoLances,dataInicio,dataFim,horaInicio,horaFim,minutosInicio,minutosFim);
                lista.add(l);
//String natureza, String cpf, String tipoLances, LocalDate dataInicio, LocalDate dataFim, int horaInicio, int horaFim, int minutoInicio, int minutoFim)
            //minutoInicio, minutoFim
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
