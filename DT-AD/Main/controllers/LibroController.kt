@RestController
@RequestMapping("/api/usuarios")
class LibroController(private val libroServie: LibroService


    @PostMapping("/crear")
    fun crearLibro(@RequestBody req: CrearLibroRequest): Libro {
        return libroServie.crearLibro(req.titulo, req.autor, req.precio)
    }


})
data class CrearLibroRequest(val titulo: String, val autor: String, val precio: java.math.BigDecimal)


