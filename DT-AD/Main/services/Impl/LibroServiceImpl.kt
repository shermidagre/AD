@Service

open class LibroServiceImpl (private val libroRepository: LibroRepository) : LibroService {

    override fun crearLibro(titulo: String, autor: String, anioPublicacion: Int): Libro {
        val nuevoLibro = Libro(titulo = titulo, autor = autor, anioPublicacion = anioPublicacion)
        return libroRepository.save(nuevoLibro)
    }

    override fun findById(id: Int): Libro? {
        return libroRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<Libro> {
        return libroRepository.findAll()
    }

    override fun updateLibro(id: Int, titulo: String, autor: String, anioPublicacion: Int) {
        val libroExistente = libroRepository.findById(id).orElseThrow {
            NoSuchElementException("Libro con ID $id no encontrado.")
        }
        val libroActualizado = libroExistente.copy(titulo = titulo, autor = autor, anioPublicacion = anioPublicacion)
        libroRepository.save(libroActualizado)
    }

    override fun deleteById(id: Int) {
        if (!libroRepository.existsById(id)) {
            throw NoSuchElementException("Libro con ID $id no encontrado.")
        }
        libroRepository.deleteById(id)
    }
}