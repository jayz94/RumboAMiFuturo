package sv.edu.ues.fia.rumboamifuturo;


/**
 * Created by Serpas on 19/6/2016.
 */
public class Galeria {
    private String nombre;
    private int idDrawable;

    public Galeria(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }
    public static Galeria[] ItemsUes(){
        Galeria[] ITEMS = {
                new Galeria("Agrononia", R.drawable.agronomia_ues1),
                new Galeria("Economia", R.drawable.economia_ues),
                new Galeria("Ingeniería y Arquitectura", R.drawable.fia_ues),
                new Galeria("Medicina", R.drawable.medicina_ues),
                new Galeria("C.C.N.N. y Matemática", R.drawable.naturales_matematica_ues),
                new Galeria("Jurisprudencia y C.C.S.S.", R.drawable.jurisprudencia_ues),
                new Galeria("Química y Farmacia", R.drawable.quimica_farmacia_ues),
                new Galeria("Ciencias y Humanidades", R.drawable.humanidades_ues),
                new Galeria("Odontología", R.drawable.odonto_ues),

        };
        return ITEMS;
    }
    public static Galeria[] ItemsUca(){
        Galeria[] ITEMS = {
                new Galeria("", R.drawable.uca_sv_10),
                new Galeria("Capilla Jesuitas", R.drawable.uca_sv_11),
                new Galeria("", R.drawable.uca_sv_12),
                new Galeria("", R.drawable.uca_sv_13),
                new Galeria("", R.drawable.uca_sv_14),
                new Galeria("", R.drawable.uca_sv_4),
                new Galeria("Edificio Comunicaciones", R.drawable.uca_sv_6),
                new Galeria("Centro Monseñor Romero", R.drawable.uca_sv_8),
                new Galeria("", R.drawable.uca_sv_9),

        };
        return ITEMS;
    }
    public static Galeria[] ItemsUtec(){
        Galeria[] ITEMS = {
                new Galeria("Simón Bolívar", R.drawable.edi_01),
                new Galeria("Anastacio Aquino", R.drawable.edi_02),
                new Galeria("Benito Juarez", R.drawable.edi_03),
                new Galeria("Francisco Morazán", R.drawable.edi_04),
                new Galeria("Thomas Jefferson", R.drawable.edi_06),
                new Galeria("Giusseppe Garibaldi", R.drawable.edi_07),
                new Galeria("Claudia Lars", R.drawable.edi_08),
                new Galeria("Gabriela Mistral", R.drawable.edi_09),
                new Galeria("Jorge Luis Borges", R.drawable.edi_10),

        };
        return ITEMS;
    }


    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Coche
     */
    public static Galeria getItem(int id) {
        for (Galeria item : ItemsUes()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public static Galeria getItemUca(int id) {
        for (Galeria item : ItemsUca()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public static Galeria getItemUtec(int id) {
        for (Galeria item : ItemsUtec()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}