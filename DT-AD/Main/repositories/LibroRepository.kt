
interface LibroRepository {
    fun save(libro: Libro): Libro
    fun findById(id: Int): Optional<Libro>
    fun findAll(): List<Libro>
    fun existsById(id: Int): Boolean
    fun deleteById(id: Int)
}