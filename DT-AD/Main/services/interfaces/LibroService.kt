
interface LibroService{
    fun obtenerLibroPorId(id: Int): Libro?
    fun obtenerTodosLosLibros(): List<Libro>
    fun agregarLibro(titulo: String, autor: String, anioPublicacion: Int): Libro
    fun actualizarLibro(id: Int, titulo: String?, autor: String?, anioPublicacion: Int?): Libro?
    fun eliminarLibro(id: Int): Boolean
}