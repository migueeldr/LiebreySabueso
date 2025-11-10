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
MovimientoLiebre.movimientosPosibles(tablero, estado)
else
MovimientoSabueso.movimientosPosibles(tablero, estado)

if !modoIA{
if (estado.turno == Jugador.liebre) then
println("Liebre, tus movimientos son:")
else
println("Sabueso, tus movimientos son:")

val movsIndexables = movimientos.toVector
movsIndexables.zipWithIndex.foreach { case (pos, i) => println(s"${i + 1}) $pos") }

val eleccion = scala.io.StdIn.readLine().toInt
val seleccionIndex = eleccion - 1
val destino = movsIndexables(seleccionIndex)}
if modo IA {
  if (estado.turno == Jugador.sabueso) then
  println("Sabueso, tus movimientos son:")
  else (

    val puntuacionmovimiento = movimientos.evaluarmovimiento
 movimientos.zip(puntuacionmovimiento).zipWithIndex.foreach {
   case ((mov, puntuacion), i) =>
     println(s"${i + 1}) $mov — Valoración: $puntuacion")}

  val movelegido = puntuacionmovimiento
      .groupBy(_._1).maxBy(_._1)._2
      .groupBy(_._2).maxBy(_._1)._2
      .apply(Random.nextInt(_ .size))
  val destino = movelegido.toVector


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
    
    
  