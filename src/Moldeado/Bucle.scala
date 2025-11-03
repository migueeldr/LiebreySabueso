package Moldeado



def bucleJuego(tablero: TableroJuego, estado: Estado): Jugador=
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
      bucleJuego(tablero, nuevoEstado)
    
    
  