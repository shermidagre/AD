import java.io.Serializable;

public class InventarioTienda implements Serializable {

    int id;
    int idVehiculo;
    float PrezoMayorista;
    float PrezoVenta;
    int PorcentaxeOferta;

    public InventarioTienda( int id,  int idVehiculo, float PrezoMayorista, float PrezoVenta, int PorcentaxeOferta){

        this.id = id;
        this.idVehiculo = idVehiculo;
        this.PrezoMayorista = PrezoMayorista;
        this.PrezoVenta = PrezoVenta;
        this.PorcentaxeOferta = PorcentaxeOferta;

    }
    @Override
    public String toString () {
        return "Vehiculo"+
                "id = "+ id + "\n" +
                "idVehiculo = "+ idVehiculo + "\n" +
                "PrezoMayorista = "+ PrezoMayorista + "\n" +
                "PrezoVenta = "+ PrezoVenta +"\n" +
                "PorcentaxeOferta = "+ PorcentaxeOferta ;

    }

}
