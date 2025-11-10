package Moldeado
import scala.util.Random
trait TableroJuego:
  // --- Devuelve las posiciones accesibles desde una posición ---
  def movimientosDesde(p: Posicion): Set[Posicion]

  // --- Posiciones iniciales de la liebre y sabuesos ---
  def posicionInicialLiebre: Posicion

  def posicionesInicialesSabuesos: Set[Posicion]

  def posicionMetaLiebre: Posicion

  // --- Pinta el tablero para un estado dado
  def pintarTablero(estado: Estado): Unit

  // --- Comprueba si ha terminado la partida ---
  def esFinPartida(estado: Estado): Option[Jugador]




enum Fila(val y: Int):
    case A extends Fila(1)
    case M extends Fila(0)
    case B extends Fila(-1)

enum Columna(val x: Int):
    case I2 extends Columna(-2)
    case I1 extends Columna(-1)
    case M extends Columna(0)
    case D1 extends Columna(1)
    case D2 extends Columna(2)

case class Posicion(col: Columna, fila: Fila):

    def x: Int = col.x

    def y: Int = fila.y

  def manhattan(other: Posicion):
  math.abs(this.x - other.x) + math.abs(this.y - other.y)


  object TableroClasicoLyS extends TableroJuego:
    // --- Pintado ---
    private def pintarNodo(p: Posicion, estado: Estado): String =

      val RESET = "\u001B[0m"
      val ROJO = "\u001B[31m"
      val AZUL = "\u001B[34m"
      val BLANCO = "\u001B[37m"
      if (estado.liebre == p) s"${ROJO}L${RESET}"
      else if (estado.sabuesos.contains(p)) s"${AZUL}S${RESET}"
      else s"${BLANCO}o${RESET}"

    override def pintarTablero(estado: Estado): Unit =

      val s = pintarNodo(_, estado)
      println(s" ${s(I1A)}-----${s(MA)}-----${s(D1A)}")
      println(" ╱ | \\ | / | \\")
      println(s" ${s(I2M)}---${s(I1M)}-----${s(MM)}-----${s(D1M)}---${s(D2M)}")
      println(" \\ | / | \\ | /")
      println(s" ${s(I1B)}-----${s(MB)}-----${s(D1B)}")



    val I1A= Posicion(Columna.I1, Fila.A)
    val MA = Posicion(Columna.M, Fila.A)
    val D1A = Posicion(Columna.D1, Fila.A)
    val I2M = Posicion(Columna.I2, Fila.M)
    val I1M = Posicion(Columna.I1, Fila.M)
    val MM = Posicion(Columna.M, Fila.M)
    val D1M = Posicion(Columna.D1, Fila.M)
    val D2M = Posicion(Columna.D2, Fila.M)
    val I1B = Posicion(Columna.I1, Fila.B)
    val MB = Posicion(Columna.M, Fila.B)
    val D1B = Posicion(Columna.D1, Fila.B)
//Nodos adyacentes
    val Destino: Map[Posicion, Set[Posicion]] = Map(
      I1A -> Set(I2M, I1M, MA, MM),
      MA -> Set(I1A, D1A, MM),
      D1A -> Set(D2M, D1M, MA, MM),
      I2M -> Set(I1A, I1M, I1B),
      I1M -> Set(I2M, I1A, I1B, MM),
      MM -> Set(I1M, MA, D1A, D1M, I1B,MB, D1B),
      D1M -> Set(D2M, D1B, D1A, MM),
      D2M -> Set(D1A, D1B, D1M),
      I1B -> Set(I2M, I1M, MM, MB),
      MB -> Set(I1B, MM, D1B),
      D1B -> Set(MM, D1M, MB, D2M)

    )

    override def movimientosDesde(p: Posicion): Set[Posicion] =
      Destino(p)

    override def posicionInicialLiebre: Posicion =
      D2M
    override def posicionesInicialesSabuesos: Set[Posicion]=
      Set(I1A,I2M,I1B)

    override def posicionMetaLiebre: Posicion =
      I2M

    override def esFinPartida(estado: Estado): Option[Jugador] =
      if estado.liebre == posicionMetaLiebre then
        Some(Jugador.liebre)
      else if MovimientoLiebre.movimientosPosibles(TableroClasicoLyS, estado).isEmpty then
        Some(Jugador.sabuesos)
      else
        None
