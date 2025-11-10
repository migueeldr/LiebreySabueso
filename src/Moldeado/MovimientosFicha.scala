package Moldeado

sealed trait MovimientoFicha:
  def movimientosPosibles(tablero: TableroJuego, estado: Estado): Set[Posicion]

case object MovimientoLiebre extends MovimientoFicha:

  override def movimientosPosibles(tablero: TableroJuego, estado: Estado): Set[Posicion] =
    tablero.movimientosDesde(estado.liebre).filterNot(estado.ocupadas.contains)

  def aplicarOpt(tablero: TableroJuego, estado: Estado, dest: Posicion): Option[Estado] =
    if movimientosPosibles(tablero, estado).contains(dest) then
      val nuevaLiebre: Posicion = dest
      val nuevoEstado: Estado = Estado(
        liebre = nuevaLiebre,
        sabuesos = estado.sabuesos,
        turno = Jugador.sabuesos
      )
      Some(nuevoEstado)
    else None
  def evaluarMovimiento(tablero: TableroJuego, estado: Estado, destino: Posicion): (Int, Int) =
  val sabuesos = estado.sabuesos.toList
  val rebasados = sabuesos.count(s => destino.x < s.x)
  val sumaDist = sabuesos.map(s => destino.manhattan(s)).sum
  val haRebasadoAntes = sabuesos.exists(s => estado.liebre.x < s.x)
  val primer =
    if !haRebasadoAntes then rebasados
    else
  val maxDistEst = 10
  maxDistEst - destino.manhattan(tablero.posicionMetaLiebre)
  (primer, sumaDist)


  case object MovimientoSabueso extends MovimientoFicha:
  override def movimientosPosibles(tablero: TableroJuego, estado: Estado): Set[Posicion] =
    estado.sabuesos.flatMap { s =>
      tablero.movimientosDesde(s).filterNot(p =>p.x < s.x).filterNot(estado.ocupadas.contains)
    }

  def movimientosPosiblesPorSabueso(tab: TableroJuego, est: Estado): Set[(Posicion, Posicion)] =
    est.sabuesos.flatMap { origen =>
      tab.movimientosDesde(origen)
        .filter(dest => dest.x >= origen.x && !est.ocupadas.contains(dest))
        .map(dest => (origen, dest))
  def evaluarMovimiento(tablero: TableroJuego, estado: Estado, origen: Posicion, destino: Posicion): (Int, Int) =
  val nuevosSabuesos = estado.sabuesos - origen + destino
  val distLiebre =
      val antes = origen.manhattan(estado.liebre)
      val despues = destino.manhattan(estado.liebre)
      (antes - despues)
    val huecoMax= huecoMaximoEnX(nuevosSabuesos)

  ( huecoMax, distLiebre)
    }

