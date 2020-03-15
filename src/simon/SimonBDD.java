package simon;

import java.sql.*;

public class SimonBDD implements SimonParameters {

    private static SimonBDD thisBDD = null;
    private static Statement stmt = null;
    private static Connection c = null;

    /**
     * Patron Singleton
     * @return instancia unica de SimonBDD
     */
    public static SimonBDD getInstance() {
        if(thisBDD == null)
            thisBDD = new SimonBDD();
        return thisBDD;
    }

    /**
     * Constructor de clase.
     */
    private SimonBDD() {
        createTables();
        restorePuntajesFromBDD();
    }

    /**
     * Creacion de tablas correspondientes a la base de
     * datos, solamente si no están ya creadas
     */
    private void createTables() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + bddPath);
            c.setAutoCommit(true);
            stmt = c.createStatement();

            if(!tableExists("PUNTAJES")) {
                String sql = 	"CREATE TABLE PUNTAJES " +
                        " (JUGADOR   	 TEXT	 NOT NULL, " +
                        " RONDA          INT    NOT NULL) ";
                stmt.executeUpdate(sql);
                stmt.close();
            }
            c.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Chequea si la tabla existe en la base de datos
     * @param tableName nombre de tabla
     * @return true si la tabla existe, de lo contrario false
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean tableExists(String tableName) {
        boolean bool = false;
        try {
            DatabaseMetaData md = c.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);

            if(rs.next())
                bool = true;

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * Insercion de puntaje en base de datos
     * @param jugador jugador dueño del puntaje
     * @param ronda ronda a la cual llego el jugador
     */
    public void insertarPuntaje(String jugador, int ronda) {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + bddPath);
            stmt = c.createStatement();
            String sql = "INSERT INTO PUNTAJES (JUGADOR, RONDA) " +
                    "VALUES ('" + jugador + "', " + ronda + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lectura de la base de datos y creacion de objetos java correspondiente
     * a los puntajes
     */
    public void restorePuntajesFromBDD() {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + bddPath);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM PUNTAJES;");

            while (rs.next()) {
                String nombre = rs.getString("JUGADOR");
                int ronda = rs.getInt("RONDA");

                Controller.addPuntaje(nombre + "-" + ronda);
            }
            c.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restablecer la base de datos
     * Borrado de todos los datos
     */
    public void restablecerBDD() {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + bddPath);
            stmt = c.createStatement();

            String sql = "DROP TABLE PUNTAJES;";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
