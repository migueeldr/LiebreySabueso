package Moldeado



/*def bucleJuego(tablero: TableroJuego, estado: Estado): Jugador=
  TableroClasicoLyS.pintarTablero(estado)

  val movimientos=
  if (estado.turno == Jugador.liebre) then
    MovimientoLiebre.movimientosPosibles(tablero, estado)
  else
    MovimientoSabueso.movimientosPosibles(tablero, estado)


  if (estado.turno == Jugador.liebre) then
    println("Liebre, tus movimientos son:")
  else
    println("Sabueso, tus movimientos son:")

  val movsIndexables = movimientos.toVector
  movsIndexables.zipWithIndex.foreach { case (pos, i) => println(s"${i + 1}) $pos") }

  val eleccion = scala.io.StdIn.readLine().toInt
  val seleccionIndex = eleccion - 1
  val destino = movsIndexables(seleccionIndex)


  val nuevoEstado =
    if (estado.turno == Jugador.liebre) then
      estado.copy(liebre = destino, turno = Jugador.sabuesos)
    else
      val origen = estado.sabuesos.head
      val nuevosSabuesos = estado.sabuesos - origen + destino
      estado.copy(sabuesos = nuevosSabuesos, turno = Jugador.liebre)
  TableroClasicoLyS.esFinPartida(nuevoEstado) match
    case Some(ganador) =>
      println("Ha ganado " + ganador)
      ganador
    case None =>
      bucleJuego(tablero, nuevoEstado)*/

def bucleJuego(tablero: TableroJuego, estado: Estado,  modoIA: Boolean): Jugador=
  TableroClasicoLyS.pintarTablero(estado)

val movimientos=
  if (estado.turno == Jugador.liebre) then
MovimientoLiebre.movimientosPosibles(tablero, estado).toVector
else
MovimientoSabueso.movimientosPosibles(tablero, estado).toVector

if !modoIA{
if (estado.turno == Jugador.liebre) then
println("Liebre, tus movimientos son:")
  if (estado.turno == Jugador.Sabueso) then
println("Sabueso, tus movimientos son:")

movimientos.zipWithIndex.foreach { case (pos, i) => println(s"${i + 1}) $pos") }

val eleccion = scala.io.StdIn.readLine().toInt
val seleccionIndex = eleccion - 1
movimientos(seleccionIndex)}
if modo IA {
  if (estado.turno == Jugador.sabueso) then
  println("Sabueso, tus movimientos son:")
  movimientos.zipWithIndex.foreach { case (pos, i) => println(s"${i + 1}) $pos") }

  val eleccion = scala.io.StdIn.readLine().toInt
  val seleccionIndex = eleccion - 1
  movimientos(seleccionIndex)}

  if (estado.turno == Jugador.Liebre) then
  println("Movimientos IA liebre:")
  val puntuaciones = movimientos.map { destino =>
    val h = MovimientoLiebre.evaluarMovimiento(tablero, estado, destino)
    (destino, h)}
  puntuaciones.zipWithIndex.foreach { case ((mov, (a,b)), i) =>
    println(s"${i + 1}) $mov — Valoración: ($a, $b)")
  }
  puntuaciones.maxBy { case (_, (a,b)) => (a,b) }._1



  )

}


val nuevoEstado =
  if (estado.turno == Jugador.liebre) then
estado.copy(liebre = destino, turno = Jugador.sabuesos)
else
val origen = estado.sabuesos.head
val nuevosSabuesos = estado.sabuesos - origen + destino
estado.copy(sabuesos = nuevosSabuesos, turno = Jugador.liebre)
TableroClasicoLyS.esFinPartida(nuevoEstado) match
case Some(ganador) =>
println("Ha ganado " + ganador)
ganador
case None =>
  bucleJuego(tablero, nuevoEstado)
    
    
  