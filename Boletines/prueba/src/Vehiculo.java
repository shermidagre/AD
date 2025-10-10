import java.io.Serializable;

public class Vehiculo implements Serializable {

    int id;
    String modelo;
    String marca;
    int ano;

    public Vehiculo( int id, String modelo, String marca, int ano){

        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano ;

    }
    @Override
    public String toString () {
        return "Vehiculo"+
                "id = "+ id + "\n" +
                "modelo = "+ modelo + "\n" +
                "marca = "+ marca + "\n" +
                "ano = "+ano ;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
