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
    }

