@Entity
@Table(name = "libros")
data class Libro(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(nullable = false)
    val titulo: String,
    @Column(nullable = false)
    val autor: String,
    @Column(nullable = false)
    val precio: java.math.BigDecimal
)