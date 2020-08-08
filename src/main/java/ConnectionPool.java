import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
    public static ComboPooledDataSource getDataSource(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/schedule");
        comboPooledDataSource.setUser("postgres");
        comboPooledDataSource.setPassword("postgres");

        comboPooledDataSource.setMinPoolSize(1);
        comboPooledDataSource.setAcquireIncrement(15);
        comboPooledDataSource.setMaxPoolSize(40);

        return comboPooledDataSource;
    }
}
